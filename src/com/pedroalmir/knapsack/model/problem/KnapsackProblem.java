/**
 * 
 */
package com.pedroalmir.knapsack.model.problem;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * This class represents a Knapsack problem.
 * @author Pedro Almir
 */
public class KnapsackProblem {
	/** List of items that will be 
	 * selected and put in knapsack */
	private LinkedHashSet<Item> items;
	/** Knapsack restriction */
	private double knapsackRestriction;
	
	/**
	 * Default constructor
	 */
	public KnapsackProblem() {
		this.items = new LinkedHashSet<Item>();
	}

	/**
	 * @param items
	 * @param knapsackRestriction
	 */
	public KnapsackProblem(LinkedHashSet<Item> items, double knapsackRestriction) {
		if(items != null){
			this.items = items;
		}else{
			this.items = new LinkedHashSet<Item>();
		}
		this.knapsackRestriction = knapsackRestriction;
	}

	/**
	 * @param knapsackRestriction
	 */
	public KnapsackProblem(double knapsackRestriction) {
		this.items = new LinkedHashSet<Item>();
		this.knapsackRestriction = knapsackRestriction;
	}
	
	/**
	 * @param item
	 */
	public void addItem(Item item){
		this.items.add(item);
	}
	
	/**
	 * @return list of items in reverse order
	 */
	public LinkedList<Item> getItemsInReverseOrder(){
		LinkedList<Item> reverse = new LinkedList<Item>(this.items);
		Collections.reverse(reverse);
		return reverse;
	}
	
	/**
	 * @return list of items ordered by weight
	 */
	public LinkedList<Item> getItemsOrderByWeight(){
		LinkedList<Item> list = new LinkedList<Item>(this.items);
		Collections.sort(list, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return new Double(o1.getWeight()).compareTo(new Double(o2.getWeight()));
			}
		});
		return list;
	}

	/**
	 * @return the items
	 */
	public LinkedHashSet<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(LinkedHashSet<Item> items) {
		this.items = items;
	}

	/**
	 * @return the knapsackRestriction
	 */
	public double getKnapsackRestriction() {
		return knapsackRestriction;
	}

	/**
	 * @param knapsackRestriction the knapsackRestriction to set
	 */
	public void setKnapsackRestriction(double knapsackRestriction) {
		this.knapsackRestriction = knapsackRestriction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KnapsackProblem [items=" + items + ", knapsackRestriction=" + knapsackRestriction + "]";
	}
}
