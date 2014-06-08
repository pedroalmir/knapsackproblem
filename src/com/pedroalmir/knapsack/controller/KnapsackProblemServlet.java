package com.pedroalmir.knapsack.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pedroalmir.knapsack.algorithm.antsytem.AntSystem;
import com.pedroalmir.knapsack.algorithm.brute.BruteForce;
import com.pedroalmir.knapsack.algorithm.genetic.GeneticKnapsack;
import com.pedroalmir.knapsack.algorithm.greedy.GreedyAlgorithm;
import com.pedroalmir.knapsack.model.problem.Item;
import com.pedroalmir.knapsack.model.problem.KnapsackProblem;
import com.pedroalmir.knapsack.model.solution.KnapsackSolution;
import com.pedroalmir.knapsack.model.vo.item.ItemVO;
import com.pedroalmir.knapsack.model.vo.problem.KnapsackProblemVO;
import com.pedroalmir.knapsack.model.vo.solution.KnapsackSolutionVO;

/**
 * KnapsackProblemServlet
 * @author Pedro Almir
 */
@SuppressWarnings("serial")
public class KnapsackProblemServlet extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("json");
		/* Parse JSON to Java Object */
		Gson gson = new Gson();
		KnapsackProblemVO problemVO = gson.fromJson(json, KnapsackProblemVO.class);
		
		/* Set content type of the response so that jQuery knows what it can expect. */
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
	    /* Knapsack solution */
	    KnapsackSolution knapsackSolution = null;
	    
		if(problemVO.getAlgorithm() != null && problemVO.getAlgorithm().equals("brute_force")){
			/* Executing brute force algorithm... */
			BruteForce bruteForce = new BruteForce(convertToKnapsackProblem(problemVO), null);
			knapsackSolution = bruteForce.run();
			System.out.println(knapsackSolution.toString());
		}else if(problemVO.getAlgorithm() != null && problemVO.getAlgorithm().equals("greedy")){
			/* Executing greedy algorithm... */
			GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(convertToKnapsackProblem(problemVO), null);
			knapsackSolution = greedyAlgorithm.run();
			System.out.println(knapsackSolution.toString());
		}else if(problemVO.getAlgorithm() != null && problemVO.getAlgorithm().equals("aco")){
			/* Executing ant system algorithm... */
			AntSystem antSystem = new AntSystem(convertToKnapsackProblem(problemVO), extractSettings(problemVO));
			knapsackSolution = antSystem.run();
			System.out.println(knapsackSolution.toString());
		}else if(problemVO.getAlgorithm() != null && problemVO.getAlgorithm().equals("genetic")){
			/* Executing genetic algorithm... */
			GeneticKnapsack geneticKnapsack = new GeneticKnapsack(convertToKnapsackProblem(problemVO), extractSettings(problemVO));
			knapsackSolution = geneticKnapsack.run();
			System.out.println(knapsackSolution.toString());
		}
		
		/* Write response body. */
		if(knapsackSolution != null){
			response.getWriter().write(gson.toJson(new KnapsackSolutionVO(knapsackSolution)));
		}else{
		    response.getWriter().write(gson.toJson("Houston we have a problem!"));
		}
	}
	
	/**
	 * Convert to knapsack problem
	 * @param problemVO
	 * @return a knapsack Problem
	 */
	private static KnapsackProblem convertToKnapsackProblem(KnapsackProblemVO problemVO){
		KnapsackProblem knapsackProblem = new KnapsackProblem(problemVO.getRestriction());
		for(ItemVO vo : problemVO.getItems()){
			knapsackProblem.addItem(new Item(vo));
		}
		return knapsackProblem;
	}
	
	/**
	 * Extract settings
	 * @param problemVO
	 * @return a map with the algorithm settings
	 */
	private static HashMap<String, Object> extractSettings(KnapsackProblemVO problemVO){
		HashMap<String, Object> settings = new HashMap<String, Object>();
		if(problemVO.getAlgorithm().equals("aco")){
			settings.put("alpha", problemVO.getAlpha());
			settings.put("beta", problemVO.getBeta());
			settings.put("q", problemVO.getQ());
			
			settings.put("pheromonePersistence", problemVO.getPheromonePersistence());
			settings.put("initialPheromone", problemVO.getInitialPheromone());
			
			settings.put("numAgents", problemVO.getNumAgents());
			settings.put("maxIterations", problemVO.getMaxIterations());
		}else if(problemVO.getAlgorithm().equals("genetic")){
			settings.put("populationSize", problemVO.getPopulationSize());
			settings.put("maxEvaluations", problemVO.getMaxEvaluations());
			
			settings.put("crossover", problemVO.getCrossover());
			settings.put("mutation", problemVO.getMutation());
		}
		return settings;
	}
}
