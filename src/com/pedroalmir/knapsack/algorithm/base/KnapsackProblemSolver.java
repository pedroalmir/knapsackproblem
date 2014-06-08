/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.base;

import java.util.Map;

import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * Knapsack problem solver interface
 * @author Pedro Almir
 */
interface KnapsackProblemSolver {
	/**
	 * Initialize the algorithm settings.
	 * @param settings
	 */
	void initAlgorithmSettings(Map<String, Object> settings);
	/**
	 * Parse the problem to specific representation.
	 * For example, ACO use graph representation.
	 */
	void parseTheProblemToSpecificRepresentation();
	/**
	 * Solve the knapsack problem
	 * @param problem
	 * @return the solution to the problem
	 */
	KnapsackSolution solveKnapsackProblem();
}
