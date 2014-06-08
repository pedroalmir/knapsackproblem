/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.base;

import java.util.HashMap;

import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;


/**
 * Abstract knapsack problem solver
 * @author Pedro Almir
 */
public abstract class AbstractKnapsackProblemSolver implements KnapsackProblemSolver {
	/** Knapsack problem */
	protected KnapsackProblem problem;
	/** Algorithm settings */
	protected HashMap<String, Object> algorithmSettings;
	
	/**
	 * @param problem
	 * @param algorithmSettings
	 */
	public AbstractKnapsackProblemSolver(KnapsackProblem problem, HashMap<String, Object> algorithmSettings) {
		super();
		this.problem = problem;
		this.algorithmSettings = algorithmSettings;
	}

	/**
	 * Run the algorithm to solve the knapsack problem
	 * @return knapsack solution
	 */
	public KnapsackSolution run(){
		/* Initialize the algorithm settings */
		initAlgorithmSettings(algorithmSettings);
		/* Parse the problem to specific algorithm representation */
		parseTheProblemToSpecificRepresentation();
		/* Solve the knapsack problem */
		return solveKnapsackProblem();
	}

	/**
	 * @return the problem
	 */
	public KnapsackProblem getProblem() {
		return problem;
	}

	/**
	 * @param problem the problem to set
	 */
	public void setProblem(KnapsackProblem problem) {
		this.problem = problem;
	}

	/**
	 * @return the algorithmSettings
	 */
	public HashMap<String, Object> getAlgorithmSettings() {
		return algorithmSettings;
	}

	/**
	 * @param algorithmSettings the algorithmSettings to set
	 */
	public void setAlgorithmSettings(HashMap<String, Object> algorithmSettings) {
		this.algorithmSettings = algorithmSettings;
	}
}
