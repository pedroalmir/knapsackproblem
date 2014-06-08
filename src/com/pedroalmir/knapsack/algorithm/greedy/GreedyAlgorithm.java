/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.greedy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.pedroalmir.knapsack.algorithm.base.AbstractKnapsackProblemSolver;
import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * Greedy algorithm to solve the knapsack problem
 * @author Pedro Almir
 */
public class GreedyAlgorithm extends AbstractKnapsackProblemSolver {

	/**
	 * @param problem
	 * @param algorithmSettings
	 */
	public GreedyAlgorithm(KnapsackProblem problem, HashMap<String, Object> algorithmSettings) {
		super(problem, algorithmSettings);
	}

	@Override
	public void initAlgorithmSettings(Map<String, Object> settings) {
		// Do nothing
	}

	@Override
	public void parseTheProblemToSpecificRepresentation() {
		// Do nothing
	}

	@Override
	public KnapsackSolution solveKnapsackProblem() {
		/* Get list of items ordered by weight */
		LinkedList<Item> items = this.problem.getItemsOrderByWeight();
		
		/* Initialize auxiliary variables */
		int counter = 0; double accumulatedFitness = 0.0;
		double actualProblemRestriction = this.problem.getKnapsackRestriction();
		KnapsackSolution solution = new KnapsackSolution();
		
		/* Find a greedy solution to the knapsack problem */
		while(actualProblemRestriction > 0 && counter < items.size()){
			Item actualItem = items.get(counter);
			if(actualItem.getWeight() <= actualProblemRestriction){
				actualProblemRestriction -= actualItem.getWeight();
				accumulatedFitness += actualItem.calculateItemFitness();
				solution.addItem(actualItem);
			}
			counter++;
		}
		/* Update the solution fitness */
		solution.setFitness(accumulatedFitness);
		return solution;
	}
}
