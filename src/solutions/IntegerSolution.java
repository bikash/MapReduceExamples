package solutions;

import java.util.Arrays;

import problems.Problem;

public class IntegerSolution extends Solution {

	protected int[] variable;

	public IntegerSolution(Problem p, int numberOfBits, int[] variable) {
		this(p, numberOfBits);
	
		if (numberOfBits != variable.length) {
			throw new IllegalArgumentException("Should have the same number of bits");
		}
		
		this.variable = variable;
	}

	public IntegerSolution(Problem p, int numberOfBits) {
		super(p, numberOfBits);
		this.variable = new int[numberOfBits];
	}

	public void setValue(int index, int value) {
		this.variable[index] = value;
	}

	public int getValue(int index) {
		return variable[index];
	}

	@Override
	public String toString() {
		return Arrays.toString(variable) + "=" + fitness;
	}

	@Override
	public int compareTo(Solution s) {
		double diff = p.evaluate(this) - p.evaluate(s);

		if (diff < 0) {
			return -1;
		}
		if (diff > 0) {
			return 1;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IntegerSolution other = (IntegerSolution) obj;
		if (!Arrays.equals(variable, other.variable)) {
			return false;
		}
		return true;
	}

	@Override
	public void flip(int index) {
		// TODO Auto-generated method stub
	}

	@Override
	public Solution clone() {
		IntegerSolution s = new IntegerSolution(p, numberOfBits);
		s.variable = variable.clone();
		s.fitness = fitness;
		return s;
	}
}
