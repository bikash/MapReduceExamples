package problems;

import solutions.Solution;

public abstract class Problem {

	protected int numberOfBits;
	
	public Problem(int numberOfBits) {
		this.numberOfBits = numberOfBits;
	}

	public int getNumberOfBits() {
		return numberOfBits;
	}

	public Solution generateValidRandomSolution() {
		Solution s = null;

		do {
			s = generateRandomSolution();
		} while (isValid(s) == false);

		return s;
	}

	protected abstract Solution generateRandomSolution();

	public abstract double evaluate(Solution solution);

	public abstract boolean isValid(Solution solution);
}
