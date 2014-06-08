/**
 * 
 */
package com.pedroalmir.knapsack.algorithm.antsytem.graph;

/**
 * @author Pedro Almir
 *
 */
public class Edge {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * information
	 */
	private String information;
	/**
	 * pheromone
	 */
	private double pheromone;
	/**
	 * Essa informação é usada apenas no ACS, pois o deposito usa o valor
	 * do primeiro deposito de feromônio.
	 */
	public double pheromoneInitial;
	/**
	 * Custo ou distância
	 */
	private double distance; 
	/**
	 * begin
	 */
	private Node begin;
	/**
	 * end
	 */
	private Node end;
	
	/**
	 * @param information
	 * @param pheromone
	 * @param begin
	 * @param end
	 */
	public Edge(String information, double pheromone, Node begin, Node end) {
		this.information = information;
		this.pheromone = pheromone;
		this.begin = begin;
		this.end = end;
		this.distance = 0.0;
	}

	/**
	 * @param information
	 * @param begin
	 * @param end
	 * @param value
	 */
	public Edge(Long id, Node begin, Node end, double distance, double pheromone) {
		this.id = id;
		this.information = "Edge[" + begin.getId() + "-" + end.getId() + "], Pheromone: " + pheromone + ", Distance: " + distance;
		this.pheromone = pheromone;
		this.begin = begin;
		this.end = end;
		this.distance = distance;
		
		begin.getAdjacentEdges().add(this);
		end.getAdjacentEdges().add(this);
	}
	
	/**
	 * @param information
	 * @param begin
	 * @param end
	 * @param value
	 */
	public Edge(Long id, Node begin, Node end) {
		this.id = id;
		this.information = "Edge[" + begin.getId() + "-" + end.getId() + "]";
		this.begin = begin;
		this.end = end;
		this.pheromone = 0.0;
		this.distance = 0.0;
		
		begin.getAdjacentEdges().add(this);
		//end.getAdjacentEdges().add(this);
	}
	
	/**
	 * @param information
	 * @param pheromone
	 * @param begin
	 * @param end
	 */
	public Edge(String information, Node begin, Node end) {
		this.information = information;
		this.pheromone = 0.0;
		this.begin = begin;
		this.end = end;
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
	 * @return the pheromone
	 */
	public double getPheromone() {
		return pheromone;
	}

	/**
	 * @param pheromone the pheromone to set
	 */
	public void setPheromone(double pheromone) {
		this.pheromone = pheromone;
	}

	/**
	 * @return the begin
	 */
	public Node getBegin() {
		return begin;
	}

	/**
	 * @param begin the begin to set
	 */
	public void setBegin(Node begin) {
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public Node getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Node end) {
		this.end = end;
	}
	
	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	/**
	 * @return the pheromoneInitial
	 */
	public double getPheromoneInitial() {
		return pheromoneInitial;
	}
	/**
	 * @param pheromoneInitial the pheromoneInitial to set
	 */
	public void setPheromoneInitial(double pheromoneInitial) {
		this.pheromoneInitial = pheromoneInitial;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((information == null) ? 0 : information.hashCode());
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
		Edge other = (Edge) obj;
		if (information == null) {
			if (other.information != null)
				return false;
		} else if (!information.equals(other.information))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [information=" + information + ", pheromone=" + pheromone + "]";
	}
}
