/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.antsytem.model;

import java.util.Map;

import com.pedroalmir.knapsack.algorithm.antsytem.enun.StrategyAS;

/**
 * @author Pedro Almir
 *
 */
public class AntSystemConfiguration {
	
	private final String algorithmName;
	private final double alpha;
	private final double beta;
	private final double q;
	private final double pheromonePersistence;
	private final double initialPheromone;
	private final int numAgents;
	private final int maxIterations;
	private final int maxExecutions;
	private final StrategyAS strategy;
	
	/**
	 * Default constructor
	 */
	public AntSystemConfiguration(Map<String, Object> settings) {
		this.algorithmName = "Ant System - Knapsack Problem";
		this.alpha = (Double) settings.get("alpha");
		this.beta = (Double) settings.get("beta");
		this.q = (Double) settings.get("q");
		this.pheromonePersistence = (Double) settings.get("pheromonePersistence");
		this.initialPheromone = (Double) settings.get("initialPheromone");
		this.numAgents = (Integer) settings.get("numAgents");
		this.maxIterations = (Integer) settings.get("maxIterations");
		
		this.strategy = StrategyAS.ANT_CYCLE;
		this.maxExecutions = 1;
	}
	
	/**
	 * @param algorithmName
	 * @param alpha
	 * @param beta
	 * @param q
	 * @param pheromonePersistence
	 * @param initialPheromone
	 * @param numAgents
	 * @param maxIterations
	 * @param maxExecutions
	 * @param strategy
	 */
	public AntSystemConfiguration(String algorithmName, double alpha, double beta, double q, double pheromonePersistence, 
			double initialPheromone, int numAgents, int maxIterations, int maxExecutions, StrategyAS strategy) {
		this.algorithmName = algorithmName;
		this.alpha = alpha;
		this.beta = beta;
		this.q = q;
		this.pheromonePersistence = pheromonePersistence;
		this.initialPheromone = initialPheromone;
		this.numAgents = numAgents;
		this.maxIterations = maxIterations;
		this.maxExecutions = maxExecutions;
		this.strategy = strategy;
	}
	
	/**
	 * @param algorithmName
	 * @param alpha
	 * @param beta
	 * @param q
	 * @param pheromonePersistence
	 * @param initialPheromone
	 * @param numAgents
	 * @param maxIterations
	 */
	public AntSystemConfiguration(String algorithmName, double alpha, double beta, double q, double pheromonePersistence, 
			double initialPheromone, int numAgents, int maxIterations, StrategyAS strategy) {
		this.algorithmName = algorithmName;
		this.alpha = alpha;
		this.beta = beta;
		this.q = q;
		this.pheromonePersistence = pheromonePersistence;
		this.initialPheromone = initialPheromone;
		this.numAgents = numAgents;
		this.maxIterations = maxIterations;
		this.maxExecutions = 1;
		this.strategy = strategy;
	}
	
	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}
	/**
	 * @return the beta
	 */
	public double getBeta() {
		return beta;
	}
	/**
	 * @return the q
	 */
	public double getQ() {
		return q;
	}
	/**
	 * @return the pheromonePersistence
	 */
	public double getPheromonePersistence() {
		return pheromonePersistence;
	}
	/**
	 * @return the initialPheromone
	 */
	public double getInitialPheromone() {
		return initialPheromone;
	}

	/**
	 * @return the numAgents
	 */
	public int getNumAgents() {
		return numAgents;
	}

	/**
	 * @return the maxIterations
	 */
	public int getMaxIterations() {
		return maxIterations;
	}

	/**
	 * @return the maxExecutions
	 */
	public int getMaxExecutions() {
		return maxExecutions;
	}

	/**
	 * @return the strategy
	 */
	public StrategyAS getStrategy() {
		return strategy;
	}

	/**
	 * @return the algorithmName
	 */
	public String getAlgorithmName() {
		return algorithmName;
	}
}
