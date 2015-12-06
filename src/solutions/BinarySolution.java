package solutions;

import problems.Problem;

public class BinarySolution extends IntegerSolution {

	public BinarySolution(Problem p, int numberOfBits, int[] variable) {
		super(p, numberOfBits, variable);
	}
	
	public BinarySolution(Problem p, int numberOfBits) {
		super(p, numberOfBits);
	}

	public void setValue(int index, int value) {
		if (value != 0 && value != 1) {
			throw new IllegalArgumentException("The value should be 0 or 1");
		}
		super.setValue(index, value);
	}

	@Override
	public void flip(int index) {
		if (variable[index] == 0) {
			variable[index] = 1;
		} else if (variable[index] == 1) {
			variable[index] = 0;
		} else {
			throw new IllegalArgumentException("Can't flip with different values 0 or 1");
		}
	}
	
	@Override
	public Solution clone() {
		BinarySolution s = new BinarySolution(p, numberOfBits);
		s.variable = variable.clone();
		s.fitness = fitness;
		return s;
	}
}
