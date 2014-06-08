/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.brute;

import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * @author Pedro Almir
 *
 */
public class TestBruteForce {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KnapsackProblem problem = new KnapsackProblem(60);
		problem.addItem(new Item(1l, 52, 100));
		problem.addItem(new Item(2l, 23, 60));
		problem.addItem(new Item(3l, 35, 70));
		problem.addItem(new Item(4l, 15, 15));
		problem.addItem(new Item(5l, 7, 15));
		
		BruteForce bruteForce = new BruteForce(problem, null);
		KnapsackSolution knapsackSolution = bruteForce.run();
		if(knapsackSolution.getFitness() == 130){
			System.out.println(knapsackSolution.toString());
		}else{
			System.err.println(knapsackSolution.toString());
		}
	}
}
