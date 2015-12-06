package solutions;

import problems.Problem;

public abstract class Solution implements Comparable<Solution>, Cloneable {

	protected int numberOfBits;

	protected double fitness;
	
	protected Problem p;

	public Solution(Problem p, int numberOfBits) {
		this.p = p;
		this.numberOfBits = numberOfBits;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return this.fitness;
	}
	
	public int getNumberOfBits() {
		return numberOfBits;
	}

	public abstract void setValue(int index, int value);

	public abstract int getValue(int index);

	public abstract String toString();
	
	public abstract void flip(int index);
	
	public abstract Solution clone();
	
	public abstract boolean equals(Object obj);
}
