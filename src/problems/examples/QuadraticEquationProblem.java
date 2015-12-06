package problems.examples;

import problems.Problem;
import solutions.BinarySolution;
import solutions.Solution;
import util.PseudoRandom;

public class QuadraticEquationProblem extends Problem {

protected double min;
	
	protected double max;

	public QuadraticEquationProblem(int numberOfBits, double min, double max) {
		super(numberOfBits);
		this.min = min;
		this.max = max;
	}

	@Override
	public double evaluate(Solution solution) {
		// Convert the solution to decimal value
		String result = "";
		BinarySolution s = (BinarySolution) solution;
		
		for (int i = 0; i < s.getNumberOfBits(); i++) {
			result += s.getValue(i);
		}
		
		double decimalValue = Integer.parseInt(result, 2);
		
		double x = min + (max - min) * (decimalValue / (Math.pow(2, getNumberOfBits()) - 1.0));

		// The function to maximize f(x) = x^2-3x+4
		return Math.pow(x, 2.0) - 3.0 * x + 4.0;
	}

	@Override
	public boolean isValid(Solution solution) {
		return true;
	}

	@Override
	public Solution generateRandomSolution() {
		Solution s = new BinarySolution(this, numberOfBits);

		for (int i = 0; i < numberOfBits; i++) {
			s.setValue(i, PseudoRandom.randInt(0, 1));
		}

		return s;
	}
}
