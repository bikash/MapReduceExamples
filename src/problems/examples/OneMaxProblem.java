package problems.examples;

import problems.Problem;
import solutions.BinarySolution;
import solutions.Solution;
import util.PseudoRandom;

public class OneMaxProblem extends Problem {

	public OneMaxProblem(int numberOfBits) {
		super(numberOfBits);
	}

	@Override
	public double evaluate(Solution solution) {
		double sum = 0.0;

		for (int i = 0; i < solution.getNumberOfBits(); i++) {
			sum += solution.getValue(i);
		}

		return -sum;
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
