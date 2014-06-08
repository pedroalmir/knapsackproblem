package com.pedroalmir.knapsack.algorithm.brute;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.pedroalmir.knapsack.algorithm.base.AbstractKnapsackProblemSolver;
import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * Brute force algorithm to solve knapsack problem
 * @author Pedro Almir
 */
public class BruteForce extends AbstractKnapsackProblemSolver{

	/**
	 * @param problem
	 * @param algorithmSettings
	 */
	public BruteForce(KnapsackProblem problem, HashMap<String, Object> algorithmSettings) {
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
		/* Creating all the possibilities */
		BigDecimal total = calculateTheNumberOfAllPossibilities(this.problem.getItems().size());
		BigDecimal counter = new BigDecimal(0);
		
		/* Initialize auxiliary variables */
		double accumulatedWeight = 0.0, accumulatedFitness = 0.0, maxFitness = 0.0; boolean explode = false;
		String probableSolution = null; Iterator<Item> iterator = null; Item actualItem = null;
		KnapsackSolution solution = new KnapsackSolution(), auxSolution = new KnapsackSolution();
		
		/* Reverse the list of itens */
		LinkedList<Item> itemsInReverseOrder = this.problem.getItemsInReverseOrder();
		
		/* Execute the analisys in all possibilities */
		while(!counter.equals(total)){
			/* Probable solution */
			probableSolution = counter.toBigInteger().toString(2);
			
			/* Verify the knapsack restriction */
			accumulatedWeight = 0.0; accumulatedFitness = 0.0; explode = false;
			iterator = itemsInReverseOrder.iterator(); auxSolution.clear();
			
			for(int i = probableSolution.length()-1; i >= 0 && iterator.hasNext(); i--){
				actualItem = iterator.next();
				if(accumulatedWeight > this.problem.getKnapsackRestriction()){
					explode = true; 
					break;
				}else if(probableSolution.charAt(i) == '1'){
					if((accumulatedWeight + actualItem.getWeight()) > this.problem.getKnapsackRestriction()){
						explode = true; 
						break;
					}else{
						accumulatedFitness += actualItem.calculateItemFitness();
						accumulatedWeight += actualItem.getWeight();
						/* Add to list of items selected to create the problem solution */
						auxSolution.addItem(actualItem);
					}
				}
			}
			/* Update the best solution until now */
			if(!explode){
				if(accumulatedFitness > maxFitness){
					maxFitness = accumulatedFitness;
					auxSolution.setFitness(accumulatedFitness);
					solution.copy(auxSolution);
				}
			}
			/* Update the counter */
			counter = counter.add(new BigDecimal(1));
		}
		
		return solution;
	}
	
	/**
	 * This method calculate the number of all possibilities
	 * @param numberOfElements
	 */
	private static BigDecimal calculateTheNumberOfAllPossibilities(int numberOfElements){
		BigDecimal total = new BigDecimal(1l);
		for(int i = 0; i < numberOfElements/30; i++){
			total = total.multiply(new BigDecimal(1 << 30));
		}
		
		if(numberOfElements % 30 != 0){
			total =  total.multiply(new BigDecimal(1 << (numberOfElements % 30)));
		}
		return total;
	}

}
