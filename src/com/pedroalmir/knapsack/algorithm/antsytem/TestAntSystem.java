/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.antsytem;

import java.util.HashMap;

import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;

/**
 * @author Pedro Almir
 *
 */
public class TestAntSystem {
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
		settings.put("alpha", 1.0);
		settings.put("beta", 2.0);
		settings.put("q", 1.0);
		settings.put("pheromonePersistence", 0.5);
		settings.put("initialPheromone", 0.1);
		settings.put("numAgents", 5);
		settings.put("maxIterations", 10);
		
		AntSystem antSystem = new AntSystem(problem, settings);
		KnapsackSolution knapsackSolution = antSystem.run();
		
		if(knapsackSolution.getFitness() == 130){
			System.out.println(knapsackSolution.toString());
		}else{
			System.err.println(knapsackSolution.toString());
		}
	}
}
