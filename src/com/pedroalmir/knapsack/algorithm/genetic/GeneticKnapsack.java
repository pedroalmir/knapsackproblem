package com.pedroalmir.knapsack.algorithm.genetic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.pedroalmir.knapsack.algorithm.base.AbstractKnapsackProblemSolver;
import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * GeneticKnapsack
 * @Author: Matthew Mayo
 * @Changes: Pedro Almir
 */
public class GeneticKnapsack extends AbstractKnapsackProblemSolver{

	/** Auxiliary variables */
	private boolean debug = false;
	private boolean mutation = false;
	private int crossoverCount = 0;
	private int cloneCount = 0;
	private int generationCounter = 1;
	private double totalFitnessOfGeneration = 0;
	
	/** Genetic variables */
	private int numberOfItems = 0;
	private int populationSize = 0;
	private int maximumGenerations = 0;
	private double probCrossover = 0;
	private double probMutation = 0;
	
	/** Auxiliary lists */
	private ArrayList<Double> value_of_items;
	private ArrayList<Double> weight_of_items;
	private ArrayList<Double> fitness;
	
	private ArrayList<Double> best_fitness_of_generation;
	private ArrayList<Double> mean_fitness_of_generation;
	
	private ArrayList<String> population;
	private ArrayList<String> breed_population;
	private ArrayList<String> best_solution_of_generation;
	
	/**
	 * @param problem
	 * @param algorithmSettings
	 */
	public GeneticKnapsack(KnapsackProblem problem, HashMap<String, Object> algorithmSettings) {
		super(problem, algorithmSettings);
		
		this.value_of_items = new ArrayList<Double>();
		this.weight_of_items = new ArrayList<Double>();
		this.fitness = new ArrayList<Double>();
		this.best_fitness_of_generation = new ArrayList<Double>();
		this.mean_fitness_of_generation = new ArrayList<Double>();
		this.population = new ArrayList<String>();
		this.breed_population = new ArrayList<String>();
		this.best_solution_of_generation = new ArrayList<String>();
	}
	
	@Override
	public void initAlgorithmSettings(Map<String, Object> settings) {
		/* Population size */
		this.populationSize = (Integer) settings.get("populationSize");
		/* Maximum number of generations */
		this.maximumGenerations = (Integer) settings.get("maxEvaluations");
		/* Crossover probability */
		this.probCrossover = (Double) settings.get("crossover");
		/* Mutation probability */
		this.probMutation = (Double) settings.get("mutation");
	}

	@Override
	public void parseTheProblemToSpecificRepresentation() {
		/* Collects user input to be used as parameters for knapsack problem */
		this.numberOfItems = this.problem.getItems().size();
		for(Item item : this.problem.getItems()){
			/* Value and weight of each item */
			this.weight_of_items.add(item.getWeight());
			this.value_of_items.add(item.calculateItemFitness());
		}
	}

	@Override
	public KnapsackSolution solveKnapsackProblem() {
		/* Step 1: Generate initial random population (first generation) */
		this.makePopulation();
		/* Step 2: Evaluate fitness of initial population members */
		this.evalPopulation();
		/* Step 3: Find best solution of generation */
		this.best_solution_of_generation.add(this.population.get(this.getBestSolution()));
		/* Step 4: Find mean solution of generation */
		this.mean_fitness_of_generation.add(this.getMeanFitness());
		/* Step 5: Compute fitness of best solution of generation */
		this.best_fitness_of_generation.add(this.evalGene(this.population.get(this.getBestSolution())));
		/* Step 6: If maximum_generations > 1, breed further generations */
		if (this.maximumGenerations > 1) {
			makeFurtherGenerations();
		}
		
		/* Step 7: Find best solution out of generations */
		double best_fitness = 0; int best_gen = 0;
		for (int z = 0; z < this.maximumGenerations - 1; z++) {
			if (this.best_fitness_of_generation.get(z) > best_fitness) {
				best_fitness = this.best_fitness_of_generation.get(z);
				best_gen = z;
			}
		}

		/* Step 8: Then, go through that's generation's best solution and output items */
		String optimal_list = this.best_solution_of_generation.get(best_gen);
		Iterator<Item> iterator = this.problem.getItems().iterator();
		
		/* Step 9: Return the best solution */
		KnapsackSolution solution = new KnapsackSolution();
		solution.setFitness(this.best_fitness_of_generation.get(best_gen));
		/* System.out.println("Fitness score of best solution of generation: " + this.best_fitness_of_generation.get(best_gen)); */
		
		for (int y = 0; y < this.numberOfItems; y++) {
			Item item = iterator.next();
			if (optimal_list.substring(y, y + 1).equals("1")) {
				solution.addItem(item);
			}
		}
		return solution;
	}
	
	/* ######################################################################################## */
	/* ######################################################################################## */
	/* ######################################################################################## */

	/**
	 * Controls knapsack problem logic and creates first generation
	 */
	@SuppressWarnings("unused")
	private void buildKnapsackProblem() {
		/* Generate initial random population (first generation) */
		this.makePopulation();
		
		if(debug){
			/* Start printing out summary */
			System.out.println("\nInitial Generation:");
			System.out.println("===================");
			System.out.println("Population:");
			for (int i = 0; i < this.populationSize; i++) {
				System.out.println((i + 1) + " - " + this.population.get(i));
			}
		}

		/* Evaluate fitness of initial population members */
		this.evalPopulation();
		
		if(debug){
			/* Output fitness summary */
			System.out.println("\nFitness:");
			for (int i = 0; i < this.populationSize; i++) {
				System.out.println((i + 1) + " - " + this.fitness.get(i));
			}
		}

		/* Find best solution of generation */
		this.best_solution_of_generation.add(this.population.get(this.getBestSolution()));
		
		if(debug){
			/* Output best solution of generation */
			System.out.println("\nBest solution of initial generation: " + this.best_solution_of_generation.get(0));
		}

		/* Find mean solution of generation */
		this.mean_fitness_of_generation.add(this.getMeanFitness());
		
		if(debug){
			/* Output mean solution of generation */
			System.out.println("Mean fitness of initial generation: " + this.mean_fitness_of_generation.get(0));
		}

		/* Compute fitness of best solution of generation */
		this.best_fitness_of_generation.add(this.evalGene(this.population.get(this.getBestSolution())));
		
		if(debug){
			/* Output best fitness of generation */
			System.out.println("Fitness score of best solution of initial generation: " + this.best_fitness_of_generation.get(0));
		}

		/* If maximum_generations > 1, breed further generations */
		if (this.maximumGenerations > 1) {
			makeFurtherGenerations();
		}
	}

	/**
	 * Makes further generations beyond first, if necessary
	 */
	public void makeFurtherGenerations() {
		/* Breeding loops maximum_generation number of times at most */
		for (int i = 1; i < this.maximumGenerations; i++) {
			/* Check for stopping criterion */
			if ((this.maximumGenerations > 4) && (i > 4)) {

				/* Previous three generation fitness values */
				double a = this.mean_fitness_of_generation.get(i - 1);
				double b = this.mean_fitness_of_generation.get(i - 2);
				double c = this.mean_fitness_of_generation.get(i - 3);

				/* If all are 3 equal, stop */
				if (a == b && b == c) {
					System.out.println("\nStop criterion met");
					this.maximumGenerations = i;
					break;
				}
			}

			/* Reset some counters */
			this.crossoverCount = 0;
			this.cloneCount = 0;
			this.mutation = false;

			/* Breed population */
			for (int j = 0; j < this.populationSize/2; j++) {
				this.breedPopulation();
			}

			/* Clear fitness values of previous generation */
			this.fitness.clear();

			/* Evaluate fitness of breed population members */
			this.evalBreedPopulation();

			/* Copy breed_population to population */
			for (int k = 0; k < this.populationSize; k++) {
				this.population.set(k, this.breed_population.get(k));
			}
			
			if(debug){
				/* Output the population */
				System.out.println("\nGeneration " + (i + 1) + ":");
				if ((i + 1) < 10) {
					System.out.println("=============");
				}
				if ((i + 1) >= 10) {
					System.out.println("==============");
				}
				if ((i + 1) >= 100) {
					System.out.println("===============");
				}
				System.out.println("Population:");
				for (int l = 0; l < this.populationSize; l++) {
					System.out.println((l + 1) + " - " + this.population.get(l));
				}
				
				/* Output fitness summary */
				System.out.println("\nFitness:");
				for (int m = 0; m < this.populationSize; m++) {
					System.out.println((m + 1) + " - " + this.fitness.get(m));
				}
			}

			/* Clear breed_population */
			this.breed_population.clear();
			/* Find best solution of generation */
			this.best_solution_of_generation.add(this.population.get(this.getBestSolution()));
			
			if(debug){
				/* Output best solution of generation */
				System.out.println("\nBest solution of generation " + (i + 1) + ": " + this.best_solution_of_generation.get(i));
			}

			/* Find mean solution of generation */
			this.mean_fitness_of_generation.add(this.getMeanFitness());
			
			if(debug){
				/* Output mean solution of generation */
				System.out.println("Mean fitness of generation: " + this.mean_fitness_of_generation.get(i));
			}
			
			/* Compute fitness of best solution of generation */
			this.best_fitness_of_generation.add(this.evalGene(this.population.get(this.getBestSolution())));
			
			if(debug){
				/* Output best fitness of generation */
				System.out.println("Fitness score of best solution of generation " + (i + 1) + ": " + this.best_fitness_of_generation.get(i));
				
				/* Output crossover/cloning summary */
				System.out.println("Crossover occurred " + this.crossoverCount + " times");
				System.out.println("Cloning occurred " + this.cloneCount + " times");
				if (this.mutation == false) {
					System.out.println("Mutation did not occur");
				}
				if (this.mutation == true) {
					System.out.println("Mutation did occur");
				}
			}
		}
	}

	/**
	 * Output KnapsackProblem summary
	 */
	@SuppressWarnings("unused")
	private void showOptimalList() {
		// Output optimal list of items
		System.out.println("\nOptimal list of items to include in knapsack: ");

		double best_fitness = 0;
		int best_gen = 0;

		// First, find best solution out of generational bests
		for (int z = 0; z < this.maximumGenerations - 1; z++) {
			if (this.best_fitness_of_generation.get(z) > best_fitness) {
				best_fitness = this.best_fitness_of_generation.get(z);
				best_gen = z;
			}
		}

		// Then, go through that's generation's best solution and output items
		String optimal_list = this.best_solution_of_generation.get(best_gen);
		for (int y = 0; y < this.numberOfItems; y++) {
			if (optimal_list.substring(y, y + 1).equals("1")) {
				System.out.print((y + 1) + " ");
			}
		}
	}

	/**
	 * Breeds current population to create a new generation's population
	 */
	private void breedPopulation() {
		/* 2 genes for breeding */
		int gene_1, gene_2;
		/* Increase generation_counter */
		this.generationCounter = this.generationCounter + 1;

		/* If population_size is odd #, use elitism to clone best solution of previous generation */
		if (this.populationSize % 2 == 1) {
			this.breed_population.add(this.best_solution_of_generation.get(this.generationCounter - 1));
		}

		/* Get positions of pair of genes for breeding */
		gene_1 = selectGene();
		gene_2 = selectGene();

		/* Crossover or cloning */
		crossoverGenes(gene_1, gene_2);
	}

	/**
	 * Performs mutation, if necessary
	 */
	private void mutateGene() {
		/* Decide if mutation is to be used */
		double rand_mutation = Math.random();
		if (rand_mutation <= this.probMutation) {
			/* If so, perform mutation */
			this.mutation = true;
			String mut_gene, new_mut_gene;
			
			int mut_point = 0;
			Random generator = new Random();
			double which_gene = Math.random() * 100;

			/* Mutate gene */
			if (which_gene <= 50) {
				mut_gene = this.breed_population.get(this.breed_population.size() - 1);
				mut_point = generator.nextInt(this.numberOfItems);
				if (mut_gene.substring(mut_point, mut_point + 1).equals("1")) {
					new_mut_gene = mut_gene.substring(0, mut_point) + "0" + mut_gene.substring(mut_point);
					this.breed_population.set(this.breed_population.size() - 1, new_mut_gene);
				}
				if (mut_gene.substring(mut_point, mut_point + 1).equals("0")) {
					new_mut_gene = mut_gene.substring(0, mut_point) + "1" + mut_gene.substring(mut_point);
					this.breed_population.set(this.breed_population.size() - 1, new_mut_gene);
				}
			}
			if (which_gene > 50) {
				mut_gene = this.breed_population.get(this.breed_population.size() - 2);
				mut_point = generator.nextInt(this.numberOfItems);
				if (mut_gene.substring(mut_point, mut_point + 1).equals("1")) {
					new_mut_gene = mut_gene.substring(0, mut_point) + "0" + mut_gene.substring(mut_point);
					this.breed_population.set(this.breed_population.size() - 1, new_mut_gene);
				}
				if (mut_gene.substring(mut_point, mut_point + 1).equals("0")) {
					new_mut_gene = mut_gene.substring(0, mut_point) + "1" + mut_gene.substring(mut_point);
					this.breed_population.set(this.breed_population.size() - 2, new_mut_gene);
				}
			}
		}
	}

	/**
	 * Selects a gene for breeding
	 * @return int - position of gene in population ArrayList to use for breeding
	 */
	private int selectGene() {
		/* Generate random number between 0 and total_fitness_of_generation */
		double rand = Math.random() * totalFitnessOfGeneration;
		/* Use random number to select gene based on fitness level */
		for (int i = 0; i < populationSize; i++) {
			if (rand <= fitness.get(i)) {
				return i;
			}
			rand = rand - fitness.get(i);
		}
		/* Not reachable; default return value */
		return 0;
	}

	/**
	 * Performs either crossover or cloning
	 */
	private void crossoverGenes(int gene_1, int gene_2) {
		/* Strings to hold new genes */
		String new_gene_1, new_gene_2;

		/* Decide if crossover is to be used */
		double rand_crossover = Math.random();
		if (rand_crossover <= probCrossover) {
			/* Perform crossover */
			crossoverCount = crossoverCount + 1;
			Random generator = new Random();
			int cross_point = generator.nextInt(numberOfItems) + 1;

			/* Cross genes at random spot in strings */
			new_gene_1 = population.get(gene_1).substring(0, cross_point) + population.get(gene_2).substring(cross_point);
			new_gene_2 = population.get(gene_2).substring(0, cross_point) + population.get(gene_1).substring(cross_point);

			/* Add new genes to breed_population */
			breed_population.add(new_gene_1);
			breed_population.add(new_gene_2);
		} else {
			/* Otherwise, perform cloning */
			cloneCount = cloneCount + 1;
			breed_population.add(population.get(gene_1));
			breed_population.add(population.get(gene_2));
		}

		/* Check if mutation is to be performed */
		mutateGene();
	}

	/**
	 * Gets best solution in population
	 * @return int - position of best solution in population
	 */
	private int getBestSolution() {
		int best_position = 0;
		double this_fitness = 0;
		double best_fitness = 0;
		for (int i = 0; i < this.populationSize; i++) {
			this_fitness = evalGene(this.population.get(i));
			if (this_fitness > best_fitness) {
				best_fitness = this_fitness;
				best_position = i;
			}
		}
		return best_position;
	}

	/**
	 * Gets mean fitness of generation
	 */
	private double getMeanFitness() {
		double total_fitness = 0;
		double mean_fitness = 0;
		for (int i = 0; i < this.populationSize; i++) {
			total_fitness = total_fitness + this.fitness.get(i);
		}
		mean_fitness = total_fitness / this.populationSize;
		return mean_fitness;
	}

	/**
	 * Evaluates entire population's fitness, by filling fitness ArrayList
	 * with fitness value of each corresponding population member gene
	 */
	private void evalPopulation() {
		this.totalFitnessOfGeneration = 0;
		for (int i = 0; i < this.populationSize; i++) {
			double temp_fitness = evalGene(this.population.get(i));
			this.fitness.add(temp_fitness);
			this.totalFitnessOfGeneration = this.totalFitnessOfGeneration + temp_fitness;
		}
	}

	/**
	 * Evaluates entire breed_population's fitness, by filling breed_fitness ArrayList
	 * with fitness value of each corresponding breed_population member gene
	 */
	private void evalBreedPopulation() {
		this.totalFitnessOfGeneration = 0;
		for (int i = 0; i < this.populationSize; i++) {
			double temp_fitness = evalGene(this.breed_population.get(i));
			this.fitness.add(temp_fitness);
			this.totalFitnessOfGeneration = this.totalFitnessOfGeneration + temp_fitness;
		}
	}

	/**
	 * Evaluates a single gene's fitness, by calculating the 
	 * total_weight of items selected by the gene.
	 * 
	 * @return double - gene's total fitness value
	 */
	private double evalGene(String gene) {
		double total_weight = 0,  total_value = 0; 
		double fitness_value = 0, difference = 0;
		char c = '0';

		/* Get total_weight associated with items selected by this gene */
		for (int j = 0; j < this.numberOfItems; j++) {
			c = gene.charAt(j);
			/* If chromosome is a '1', add corresponding item position's weight to total weight */
			if (c == '1') {
				total_weight = total_weight + this.weight_of_items.get(j);
				total_value = total_value + this.value_of_items.get(j);
			}
		}
		
		/* Check if gene's total weight is less than knapsack capacity */
		difference = this.problem.getKnapsackRestriction() - total_weight;
		if (difference >= 0) {
			/* This is acceptable; calculate a fitness_value. Otherwise, 
			 * fitness_value remains 0 (default), since knapsack cannot 
			 * hold all items selected by gene Fitness value is simply 
			 * total value of acceptable permutation, and for unacceptable 
			 * permutation is set to '0' */
			fitness_value = total_value;
		}

		/* Return fitness value */
		return fitness_value;
	}

	/**
	 * Makes a population by filling population ArrayList with strings of
	 * length number_of_items, each element a gene of randomly generated
	 * chromosomes (1s and 0s)
	 */
	private void makePopulation() {
		for (int i = 0; i < this.populationSize; i++) {
			/* Calls makeGene() once for each element position */
			this.population.add(makeGene());
		}
	}

	/**
	 * Generates a single gene, a random String of 1s and 0s.
	 * @return String - a randomly generated gene
	 */
	private String makeGene() {
		/* StringBuilder builds gene, one chromosome (1 or 0) at a time */
		StringBuilder gene = new StringBuilder(this.numberOfItems);
		/* Each Chromosome */
		char c;

		/* Loop creating gene */
		for (int i = 0; i < this.numberOfItems; i++) {
			c = '0';
			double rnd = Math.random();
			/* If random number is greater than 0.5, chromosome is '1' */
			/* If random number is less than 0.5, chromosome is '0' */
			if (rnd > 0.5) {
				c = '1';
			}
			/* Append chromosome to gene */
			gene.append(c);
		}
		
		/* StringBuilder object to string; return */
		return gene.toString();
	}

	/**
	 * Determines if input string can be converted to integer
	 * 
	 * @param String - string to be checked
	 * @return boolean - whether or not string can be converted
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Determines if input string can be converted to double
	 * 
	 * @param String - string to be checked
	 * @return boolean - whether or not string can be converted
	 */
	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
