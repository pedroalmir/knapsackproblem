/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.antsytem.model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.knapsack.algorithm.antsytem.graph.Edge;
import com.pedroalmir.knapsack.algorithm.antsytem.graph.Node;


/**
 * Ant representation
 * @author Pedro Almir
 */
public class Ant {
	/** ID */
	private Long id;
	/** Information */
	private String information;
	/** ActualNode */
	private Node actualNode;
	/** First Node */
	private Node firstNode;
	/** Tabu list */
	private LinkedHashSet<Node> tabuList;
	/** Edge tabu list*/
	private List<Edge> edgeList;
	/** Quality of solution */
	private Double qualityOfSolution;
	
	/**
	 * @param id
	 * @param information
	 * @param actualNode
	 */
	public Ant(String information, Node actualNode) {
		this.information = information;
		this.actualNode = actualNode;
		this.firstNode = actualNode;
		
		this.tabuList = new LinkedHashSet<Node>();
		this.tabuList.add(actualNode);
		
		this.edgeList = new LinkedList<Edge>();
	}
	
	/**
	 * Reset ant
	 */
	public void reset(){
		this.actualNode = this.firstNode;
		this.tabuList = new LinkedHashSet<Node>();
		this.tabuList.add(this.getActualNode());
		
		this.edgeList = new LinkedList<Edge>();
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}
	/**
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}
	/**
	 * @return the actualNode
	 */
	public Node getActualNode() {
		return actualNode;
	}
	/**
	 * @param actualNode the actualNode to set
	 */
	public void setActualNode(Node actualNode) {
		this.actualNode = actualNode;
	}
	/**
	 * @return the tabuList
	 */
	public LinkedHashSet<Node> getTabuList() {
		return tabuList;
	}
	/**
	 * @param tabuList the tabuList to set
	 */
	public void setTabuList(LinkedHashSet<Node> tabuList) {
		this.tabuList = tabuList;
	}
	/**
	 * @return the firstNode
	 */
	public Node getFirstNode() {
		return firstNode;
	}

	/**
	 * @return the edgeList
	 */
	public List<Edge> getEdgeList() {
		return edgeList;
	}

	/**
	 * @param edgeList the edgeList to set
	 */
	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	/**
	 * @return the qualityOfSolution
	 */
	public Double getQualityOfSolution() {
		return qualityOfSolution;
	}

	/**
	 * @param qualityOfSolution the qualityOfSolution to set
	 */
	public void setQualityOfSolution(Double qualityOfSolution) {
		this.qualityOfSolution = qualityOfSolution;
	}
}
