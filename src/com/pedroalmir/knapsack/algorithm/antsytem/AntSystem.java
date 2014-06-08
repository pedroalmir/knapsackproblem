/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.antsytem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.pedroalmir.knapsack.algorithm.antsytem.enun.StrategyAS;
import com.pedroalmir.knapsack.algorithm.antsytem.graph.Edge;
import com.pedroalmir.knapsack.algorithm.antsytem.graph.Graph;
import com.pedroalmir.knapsack.algorithm.antsytem.graph.Node;
import com.pedroalmir.knapsack.algorithm.antsytem.model.Ant;
import com.pedroalmir.knapsack.algorithm.antsytem.model.AntSystemConfiguration;
import com.pedroalmir.knapsack.algorithm.base.AbstractKnapsackProblemSolver;
import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * Ant system to solve knapsack problem
 * @author Pedro Almir
 */
public class AntSystem extends AbstractKnapsackProblemSolver{
	/** Graph */
	private Graph graph;
	/** Random */
	private Random random;
	/** Configuration */
	private AntSystemConfiguration configuration;
	/** Ants */
	private ArrayList<Ant> ants;

	/**
	 * @param problem
	 * @param algorithmSettings
	 */
	public AntSystem(KnapsackProblem problem, HashMap<String, Object> algorithmSettings) {
		super(problem, algorithmSettings);
		this.random = new Random();
		this.ants = new ArrayList<Ant>();
	}
	
	@Override
	public KnapsackSolution run() {
		/* Parse the problem to specific algorithm representation */
		parseTheProblemToSpecificRepresentation();
		/* Initialize the algorithm settings */
		initAlgorithmSettings(algorithmSettings);
		/* Solve the knapsack problem */
		return solveKnapsackProblem();
	}

	@Override
	public void initAlgorithmSettings(Map<String, Object> settings) {
		this.configuration = new AntSystemConfiguration(settings);
		int antNumber = 1;
		/* If it is a knapsack problem ants are placed randomly by the graph. */
		if(this.configuration.getNumAgents() >= this.graph.getNodes().size()){
			for(int i = 0; i < configuration.getNumAgents()/this.graph.getNodes().size(); i++){
				for(int j = 0; j < this.graph.getNodes().size(); j++){
					ants.add(new Ant("Ant number: " + (antNumber++), this.graph.getNodes().get(j)));
				}
			}
			int mod = this.configuration.getNumAgents() % this.graph.getNodes().size();
			if(mod != 0){
				for(int i = 0; i < mod; i++){
					ants.add(new Ant("Ant number: " + (antNumber++), this.graph.getNodes().get(this.random.nextInt(this.graph.getNodes().size()))));
				}
			}
		}else{
			for(int i = 0; i < this.configuration.getNumAgents(); i++){
				ants.add(new Ant("Ant number: " + (antNumber++), this.graph.getNodes().get(this.random.nextInt(this.graph.getNodes().size()))));
			}
		}
		/* Pheromone initialization */
		resetSystem();
	}

	@Override
	public void parseTheProblemToSpecificRepresentation() {
		/* Nodes */
		LinkedList<Node> nodes = new LinkedList<Node>();
		Iterator<Item> iterator = this.problem.getItems().iterator();
		
		for(int i = 0; (i < this.problem.getItems().size()) && iterator.hasNext(); i++){
			Item item = iterator.next();
			nodes.add(new Node((i+1l), item));
		}
		
		/* Edges */
		long globalCount = 1;
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for(int i = 0; i < this.problem.getItems().size(); i++){
			for(int j = 0; j < this.problem.getItems().size(); j++){
				if(i < j){
					edges.add(new Edge(globalCount++, nodes.get(i), nodes.get(j)));
					edges.add(new Edge(globalCount++, nodes.get(j), nodes.get(i)));
				}
			}
		}
		/* Graph */
		this.graph = new Graph(1l, "Knapsack Ant System Graph", nodes, edges);
	}

	@Override
	public KnapsackSolution solveKnapsackProblem() {
		/* Initialize auxiliary variables */
		int execution = 0, iteration = 0; double maxFitness = 0.0;
		KnapsackSolution solution = new KnapsackSolution();
		
		while(execution < this.configuration.getMaxExecutions()){
			iteration = 0;
			while(iteration < this.configuration.getMaxIterations()){
				/* Step 1: Construction */
				construction();
				/* Step 2: Make evaporation of pheromone*/
				makeEvaporation();
				/* Step 3: Make actualization of pheromone */
				makeActualization(this.configuration.getStrategy());
				/* Step 4: Get the solution of this iteration */
				for(Ant ant : this.ants){
					/* Step 4.1: Get quality of solution */
					double antSolutionFitness = ant.getQualityOfSolution() != null ? ant.getQualityOfSolution() : calcQualityOfResult(ant);
					/* Step 4.2: Update the best solution until now */
					if(antSolutionFitness > maxFitness){
						solution.clear();
						solution.setFitness(antSolutionFitness);
						maxFitness = antSolutionFitness;
						
						for(Node node : ant.getTabuList()){
							/* Add to list of items selected to create the problem solution */
							solution.addItem((Item) node.getInformations().get("item"));
						}
					}
					/* Step 4.3: Reset all ants */
					ant.reset();
				}
				iteration++;
			}
			execution++;
		}
		return solution;
	}
	
	/* ######################################################################## */
	/* ########################	     Construction      ######################## */
	/* ######################################################################## */
	
	/** Construction */
	private void construction(){
		for(Ant ant : this.ants){
			Edge nextEdge = chooseNextNode(ant);
			if(nextEdge != null){
				Node node = nextEdge.getEnd();
			
				while(node != null){
					ant.setActualNode(node);
					ant.getTabuList().add(node);
					ant.getEdgeList().add(nextEdge);
					
					nextEdge = chooseNextNode(ant);
					if(nextEdge != null){
						node = nextEdge.getEnd();
					}else{
						node = null;
					}
				}
			}
		}
	}
	
	/** Choose Next Node */
	@SuppressWarnings("unchecked")
	private Edge chooseNextNode(Ant ant) {
		LinkedList<Edge> edges = (LinkedList<Edge>) ant.getActualNode().getAdjacentEdges().clone();
		Iterator<Edge> iterator = edges.iterator();
		/* Try remove loops */
		while(iterator.hasNext()){
			Edge e = iterator.next();
			if((ant.getTabuList().contains(e.getBegin()) && ant.getTabuList().contains(e.getEnd()))){
				iterator.remove();
				continue;
			}
			if(!e.getBegin().equals(ant.getActualNode())){
				iterator.remove();
				continue;
			}
			if(explodeKnapsackRestriction(ant, e.getEnd())){
				iterator.remove();
				continue;
			}
		}
		
		if(edges.isEmpty()){
			return null;
		}
		
		if(edges.size() == 1){
			return edges.get(0);
		}
		
		/* Calculando o acumulado da formula de probabilidade */
		List<Auxiliar> values = new LinkedList<Auxiliar>();
		double amount = 0L;
		for(Edge e : edges){
			amount += Math.pow(e.getPheromone(), this.configuration.getAlpha()) * Math.pow(getHeuristicValue(e), this.configuration.getBeta());
		}
		
		/* Valores individuais da formula */
		double sum = 0L;
		for(Edge e : edges){
			double pheromoneNode = Math.pow(e.getPheromone(), this.configuration.getAlpha());
			double heuristic = Math.pow(getHeuristicValue(e), this.configuration.getBeta());
			
			/* */
			values.add(new Auxiliar(e.getEnd(), e, ((pheromoneNode * heuristic)/amount)));
			sum += ((pheromoneNode * heuristic)/amount);
		}
		
		/* Ordenando os valores */
		Collections.sort(values, new Comparator<Auxiliar>() {
			@Override
			public int compare(Auxiliar o1, Auxiliar o2) {
				return o1.getProbabilidade().compareTo(o2.getProbabilidade());
			}
		});
		
		/* Aqui estou normalizando os valores. Agora a soma de todos é igual a 1. 
		 * Depois eu crio a lista com os valores acumulados. */
		int count = 0;
		for(Auxiliar d : values){
			d.setProbabilidade(d.getProbabilidade()/sum);
			if(count > 0){
				d.setProbabilidade(values.get(count-1).getProbabilidade() + d.getProbabilidade());
			}
			count++;
		}
		
		/* Valor randomico escolhido */
		double randomValue = random.nextDouble();
		for(Auxiliar d : values){
			if(randomValue <= d.getProbabilidade().doubleValue()){
				return d.getAresta();
			}
		}
		
		return null;
	}
	
	/**
	 * Explode knapsack restriction
	 * @param ant
	 * @param end
	 * @return
	 */
	private boolean explodeKnapsackRestriction(Ant ant, Node end) {
		double accumulatedWeight = 0.0;
		for(Node node : ant.getTabuList()){
			accumulatedWeight += (Double) node.getInformations().get("weight");
		}
		double nextValue = (Double) end.getInformations().get("weight");
		if(accumulatedWeight + nextValue > this.problem.getKnapsackRestriction()){
			return true;
		}
		return false;
	}
	
	/**
	 * Reset System
	 */
	private void resetSystem() {
		/* Pheromone initialization */
		double t0 = this.configuration.getInitialPheromone();
		for(Edge edge : this.graph.getEdges()){
			if(t0 > 0){
				double nextDouble = this.random.nextDouble();
				double pheromone = (nextDouble > 0d) ? (nextDouble * t0) : t0;
				edge.setPheromone(pheromone);
			}else{
				/* Valor randomicos entre 0.0 e 1.0 */
				double nextDouble = this.random.nextDouble();
				double pheromone = (nextDouble > 0d) ? (nextDouble) : 0.1;
				edge.setPheromone(pheromone);
			}
		}
		for(Ant ant : this.ants){
			/* Reset Ant */
			ant.reset();
		}
	}
	
	/* ######################################################################## */
	/* ########################	     Evaporation       ######################## */
	/* ######################################################################## */
	
	/** Evaporation */
	private void makeEvaporation(){
		for(Edge e : this.graph.getEdges()){
			e.setPheromone((1 - this.configuration.getPheromonePersistence()) * e.getPheromone());
		}
	}
	
	/* ######################################################################## */
	/* ########################	     Actualization     ######################## */
	/* ######################################################################## */
	
	/** Actualization */
	private void makeActualization(StrategyAS strategy){
		for(Ant ant : this.ants){
			for(Edge e : ant.getEdgeList()){
				if(strategy.equals(StrategyAS.ANT_CYCLE)){
					e.setPheromone(e.getPheromone() + this.configuration.getQ()/calcQualityOfResult(ant));
				}else if(strategy.equals(StrategyAS.ANT_DENSITY)){
					e.setPheromone(e.getPheromone() + this.configuration.getQ());
				}else if(strategy.equals(StrategyAS.ANT_QUANTITY)){
					e.setPheromone(e.getPheromone() + this.configuration.getQ()/e.getDistance());
				}
			}
		}
	}
	
	/* ######################################################################## */
	/* ########################	       Heuristic  	   ######################## */
	/* ######################################################################## */
	
	/** Get Heuristic Value */
	private double getHeuristicValue(Edge edge) {
		//TODO: Refatorar para permitir a utilização de uma função heurística.
		return 1;
	}
	
	/* ######################################################################## */
	/* ##################        Quality of Result  	   #################### */
	/* ######################################################################## */
	
	/** Calc Quality of Result */
	private double calcQualityOfResult(Ant ant) {
		double quality = 0.0;
		for(Node node : ant.getTabuList()){
			quality += (Double) node.getInformations().get("gain");
		}
		ant.setQualityOfSolution(quality);
		return quality;
	}
	
	/* ######################################################################## */
	/* ########################	    Auxiliary Class    ######################## */
	/* ######################################################################## */
	
	/**
	 * Classe auxiliar
	 * 
	 * @author Pedro Almir
	 */
	class Auxiliar{
		private Node no;
		private Edge aresta;
		private Double probabilidade;
		
		/**
		 * @param no
		 * @param probabilidade
		 */
		public Auxiliar(Node no, Edge aresta, Double probabilidade) {
			super();
			this.no = no;
			this.aresta = aresta;
			this.probabilidade = probabilidade;
		}
		/**
		 * @return the no
		 */
		public Node getNo() {
			return no;
		}
		/**
		 * @param no the no to set
		 */
		public void setNo(Node no) {
			this.no = no;
		}
		/**
		 * @return the probabilidade
		 */
		public Double getProbabilidade() {
			return probabilidade;
		}
		/**
		 * @param probabilidade the probabilidade to set
		 */
		public void setProbabilidade(Double probabilidade) {
			this.probabilidade = probabilidade;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Auxiliar [probabilidade=" + probabilidade + "]";
		}
		/**
		 * @return the aresta
		 */
		public Edge getAresta() {
			return aresta;
		}
		/**
		 * @param aresta the aresta to set
		 */
		public void setAresta(Edge aresta) {
			this.aresta = aresta;
		}
	}
}
