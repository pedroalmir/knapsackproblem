/**
 * 
 */
package com.pedroalmir.knapsack.model.vo.problem;

import java.util.ArrayList;

import com.pedroalmir.knapsack.model.vo.item.ItemVO;

/**
 * @author Pedro Almir
 */
public class KnapsackProblemVO {
	private String algorithm;
	private double restriction;
	private ArrayList<ItemVO> items;
	
	/** Ant Colony Optimization Settings */
	private double alpha;
	private double beta;
	private double initialPheromone;
	private double pheromonePersistence;
	private double q;
	
	private int numAgents;
	private int maxIterations;
	private String strategy;
	
	/** Genetic algorithm settings */
	private int populationSize;
	private int maxEvaluations;
	private double crossover;
	private double mutation;
	
	/**
	 * Default constructor
	 */
	public KnapsackProblemVO() {
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KnapsackProblemVO [algorithm=" + algorithm + ", restriction=" + restriction + ", items=" + items + "]";
	}



	/**
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}



	/**
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}



	/**
	 * @return the restriction
	 */
	public double getRestriction() {
		return restriction;
	}



	/**
	 * @param restriction the restriction to set
	 */
	public void setRestriction(double restriction) {
		this.restriction = restriction;
	}



	/**
	 * @return the items
	 */
	public ArrayList<ItemVO> getItems() {
		return items;
	}



	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<ItemVO> items) {
		this.items = items;
	}



	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}



	/**
	 * @param alpha the alpha to set
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}



	/**
	 * @return the beta
	 */
	public double getBeta() {
		return beta;
	}



	/**
	 * @param beta the beta to set
	 */
	public void setBeta(double beta) {
		this.beta = beta;
	}



	/**
	 * @return the initialPheromone
	 */
	public double getInitialPheromone() {
		return initialPheromone;
	}



	/**
	 * @param initialPheromone the initialPheromone to set
	 */
	public void setInitialPheromone(double initialPheromone) {
		this.initialPheromone = initialPheromone;
	}



	/**
	 * @return the pheromonePersistence
	 */
	public double getPheromonePersistence() {
		return pheromonePersistence;
	}



	/**
	 * @param pheromonePersistence the pheromonePersistence to set
	 */
	public void setPheromonePersistence(double pheromonePersistence) {
		this.pheromonePersistence = pheromonePersistence;
	}



	/**
	 * @return the q
	 */
	public double getQ() {
		return q;
	}



	/**
	 * @param q the q to set
	 */
	public void setQ(double q) {
		this.q = q;
	}



	/**
	 * @return the numAgents
	 */
	public int getNumAgents() {
		return numAgents;
	}



	/**
	 * @param numAgents the numAgents to set
	 */
	public void setNumAgents(int numAgents) {
		this.numAgents = numAgents;
	}



	/**
	 * @return the maxIterations
	 */
	public int getMaxIterations() {
		return maxIterations;
	}



	/**
	 * @param maxIterations the maxIterations to set
	 */
	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}



	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}



	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}



	/**
	 * @return the populationSize
	 */
	public int getPopulationSize() {
		return populationSize;
	}



	/**
	 * @param populationSize the populationSize to set
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}



	/**
	 * @return the maxEvaluations
	 */
	public int getMaxEvaluations() {
		return maxEvaluations;
	}



	/**
	 * @param maxEvaluations the maxEvaluations to set
	 */
	public void setMaxEvaluations(int maxEvaluations) {
		this.maxEvaluations = maxEvaluations;
	}



	/**
	 * @return the crossover
	 */
	public double getCrossover() {
		return crossover;
	}



	/**
	 * @param crossover the crossover to set
	 */
	public void setCrossover(double crossover) {
		this.crossover = crossover;
	}



	/**
	 * @return the mutation
	 */
	public double getMutation() {
		return mutation;
	}



	/**
	 * @param mutation the mutation to set
	 */
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}
}
