/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.antsytem.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.pedroalmir.knapsack.model.problem.Item;

/**
 * @author Pedro Almir
 * 
 */
public class Node {
	
	/**
	 * id
	 */
	private Long id;
	/**
	 * information
	 */
	private Map<String, Object> informations;
	/**
	 * adjacentEdges
	 */
	private LinkedList<Edge> adjacentEdges;
	
	/** Attrs to fuzzy adjustment */
	private int level;
	
	/**
	 * @param id
	 */
	public Node(long id, String name){
		this.id = id;
		this.informations = new HashMap<String, Object>();
		this.informations.put("id", name);
		this.adjacentEdges = new LinkedList<Edge>();
	}
	
	/**
	 * @param information
	 * @param feature
	 */
	public Node(Map<String, Object> informations) {
		this.informations = informations;
		this.adjacentEdges = new LinkedList<Edge>();
	}
	
	/**
	 * @param id
	 * @param informations
	 */
	public Node(Long id, Map<String, Object> informations) {
		super();
		this.id = id;
		this.informations = informations;
		this.adjacentEdges = new LinkedList<Edge>();
	}
	
	/**
	 * @param id
	 * @param gain
	 */
	public Node(Long id, Item item) {
		super();
		this.id = id;
		this.informations = new HashMap<String, Object>();
		this.informations.put("item", item);
		this.informations.put("weight", item.getWeight());
		this.informations.put("gain", item.calculateItemFitness());
		
		this.adjacentEdges = new LinkedList<Edge>();
	}
	
	/**
	 * @param id
	 * @param informations
	 * @param adjacentEdges
	 * @param level
	 */
	public Node(Long id, Map<String, Object> informations, int level) {
		super();
		this.id = id;
		this.informations = informations;
		this.level = level;
		this.adjacentEdges = new LinkedList<Edge>();
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
	 * @return the adjacentEdges
	 */
	public LinkedList<Edge> getAdjacentEdges() {
		return adjacentEdges;
	}

	/**
	 * @param adjacentEdges the adjacentEdges to set
	 */
	public void setAdjacentEdges(LinkedList<Edge> adjacentEdges) {
		this.adjacentEdges = adjacentEdges;
	}

	/**
	 * @return the informations
	 */
	public Map<String, Object> getInformations() {
		return informations;
	}

	/**
	 * @param informations the informations to set
	 */
	public void setInformations(Map<String, Object> informations) {
		this.informations = informations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjacentEdges == null) ? 0 : adjacentEdges.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((informations == null) ? 0 : informations.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (adjacentEdges == null) {
			if (other.adjacentEdges != null)
				return false;
		} else if (!adjacentEdges.equals(other.adjacentEdges))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (informations == null) {
			if (other.informations != null)
				return false;
		} else if (!informations.equals(other.informations))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [informations=" + informations + "]";
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}
