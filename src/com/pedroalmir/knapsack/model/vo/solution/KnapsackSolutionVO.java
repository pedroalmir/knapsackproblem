/**
 * 
 */
package com.pedroalmir.knapsack.model.vo.solution;

import java.util.ArrayList;

import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;
import com.pedroalmir.knapsack.model.vo.item.ItemVO;

/**
 * @author Pedro Almir
 *
 */
public class KnapsackSolutionVO {
	
	private final ArrayList<ItemVO> selectedItems;
	private final double fitness;
	private final double accumulatedWeight;
	
	/**
	 * @param selectedItems
	 * @param fitness
	 * @param accumulatedWeight
	 */
	public KnapsackSolutionVO(KnapsackSolution solution) {
		super();
		this.selectedItems = new ArrayList<ItemVO>();
		for(Item item : solution.getSelectedItems()){
			this.selectedItems.add(new ItemVO(item));
		}
		this.fitness = solution.getFitness();
		this.accumulatedWeight = solution.getAccumulatedWeight();
	}
	/**
	 * @return the selectedItems
	 */
	public ArrayList<ItemVO> getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @return the fitness
	 */
	public double getFitness() {
		return fitness;
	}
	/**
	 * @return the accumulatedWeight
	 */
	public double getAccumulatedWeight() {
		return accumulatedWeight;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KnapsackSolutionVO [selectedItems=" + selectedItems + ", fitness=" + fitness + ", accumulatedWeight=" + accumulatedWeight + "]";
	}
}
