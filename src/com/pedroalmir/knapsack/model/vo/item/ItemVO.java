/**
 * 
 */
package com.pedroalmir.knapsack.model.vo.item;

import com.pedroalmir.knapsack.model.problem.Item;

/**
 * @author Pedro Almir
 */
public class ItemVO {
	private int id;
	private double weight;
	private double gain;
	
	/**
	 * Default constructor
	 */
	public ItemVO() {
		
	}
	
	/**
	 * @param id
	 * @param weight
	 * @param gain
	 */
	public ItemVO(Item item) {
		super();
		this.id = item.getId().intValue();
		this.weight = item.getWeight();
		this.gain = item.getOtherInformation().get("gain");
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the gain
	 */
	public double getGain() {
		return gain;
	}
	/**
	 * @param gain the gain to set
	 */
	public void setGain(double gain) {
		this.gain = gain;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemVO [id=" + id + ", weight=" + weight + ", gain=" + gain + "]";
	}
}
