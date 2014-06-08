/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.genetic;

import java.util.HashMap;

import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * @author Pedro Almir
 *
 */
public class TestGeneticKnapsack {
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
		
		HashMap<String, Object> settings = new HashMap<String, Object>();
		settings.put("populationSize", 100);
		settings.put("maxEvaluations", 1000);
		settings.put("crossover", 0.9);
		settings.put("mutation", 0.1);
		
		GeneticKnapsack geneticKnapsack = new GeneticKnapsack(problem, settings);
		KnapsackSolution knapsackSolution = geneticKnapsack.run();
		if(knapsackSolution.getFitness() == 130){
			System.out.println(knapsackSolution.toString());
		}else{
			System.err.println(knapsackSolution.toString());
		}
	}
}
