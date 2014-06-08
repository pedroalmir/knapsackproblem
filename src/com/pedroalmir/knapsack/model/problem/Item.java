/**
 * 
 */
package com.pedroalmir.knapsack.model.problem;

import java.util.HashMap;
import java.util.Map;

import com.pedroalmir.knapsack.model.vo.item.ItemVO;

/**
 * This class represents an Item in Knapsack problem.
 * @author Pedro Almir
 */
public class Item {
	/** ID */
	private Long id;
	/** Description */
	private String description;
	/** Weight */
	private double weight;
	/** Other information */
	private Map<String, Double> otherInformation;
	
	/**
	 * @param id
	 * @param description
	 * @param weight
	 * @param otherInformation
	 */
	public Item(Long id, String description, double weight, Map<String, Double> otherInformation) {
		super();
		this.id = id;
		this.description = description;
		this.weight = weight;
		this.otherInformation = otherInformation;
	}
	
	/**
	 * @param description
	 * @param weight
	 */
	public Item(String description, double weight) {
		super();
		this.id = null;
		this.description = description;
		this.weight = weight;
		this.otherInformation = new HashMap<String, Double>();
	}
	
	/**
	 * @param description
	 * @param weight
	 */
	public Item(Long id, double weight, double gain) {
		super();
		this.id = id;
		this.description = "";
		this.weight = weight;
		this.otherInformation = new HashMap<String, Double>();
		this.otherInformation.put("gain", gain);
	}
	
	/**
	 * @param vo
	 */
	public Item(ItemVO vo){
		super();
		this.id = (long) vo.getId();
		this.description = "";
		this.weight = vo.getWeight();
		this.otherInformation = new HashMap<String, Double>();
		this.otherInformation.put("gain", vo.getGain());
	}
	
	/**
	 * @param field
	 * @param value
	 */
	public void putOtherInformation(String field, double value){
		this.otherInformation.put(field, value);
	}
	
	/**
	 * Calculate the item fitness.
	 * TODO: Flexibilizar para possibilitar problemas de minimização,
	 * ou outras definições de funções que calculem o fitness de cada item.
	 * @return item fitness
	 */
	public double calculateItemFitness(){
		double accumulatedFitness = 0.0;
		for(String key : this.otherInformation.keySet()){
			accumulatedFitness += this.otherInformation.get(key);
		}
		return accumulatedFitness;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the otherInformation
	 */
	public Map<String, Double> getOtherInformation() {
		return otherInformation;
	}

	/**
	 * @param otherInformation the otherInformation to set
	 */
	public void setOtherInformation(Map<String, Double> otherInformation) {
		this.otherInformation = otherInformation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [id=" + id + ", weight=" + weight + ", otherInformation=" + otherInformation + "]";
	}
	
}
