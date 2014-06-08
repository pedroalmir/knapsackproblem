/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.antsytem.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Almir
 * 
 */
public class Graph {

	/**
	 * id
	 */
	private Long id;
	/**
	 * information
	 */
	private String information;
	/**
	 * nodes
	 */
	private List<Node> nodes;
	/**
	 * edges
	 */
	private List<Edge> edges;
	/**
	 * Matriz da heuristiva (i, j)
	 */
	private double heuristic[][];
	
	/**
	 * @param id
	 * @param information
	 * @param nodes
	 * @param edges
	 */
	public Graph(Long id, String information, List<Node> nodes, List<Edge> edges) {
		super();
		this.id = id;
		this.information = information;
		this.nodes = nodes;
		this.edges = edges;
	}
	
	/**
	 * @param id
	 * @param information
	 */
	public Graph(String information) {
		this.information = information;
		this.nodes = new LinkedList<Node>();
		this.edges = new LinkedList<Edge>();
	}
	
	/**
	 * @param node
	 * @return true is all is correct
	 */
	public boolean addNode(Node node){
		try{
			this.nodes.add(node);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	/**
	 * @param edge
	 * @return true is all is correct
	 */
	public boolean addEdge(Edge edge){
		try{
			this.edges.add(edge);
			return true;
		}catch(Exception ex){
			return false;
		}
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
	 * @return the nodes
	 */
	public List<Node> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * @return the heuristic
	 */
	public double[][] getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(double[][] heuristic) {
		this.heuristic = heuristic;
	}
	
	
}
