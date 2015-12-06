package condition.termination;

import solutions.Solution;

public class Iterations extends Termination {

	protected int maxIterations;

	public Iterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	@Override
	public boolean terminationCondition(int iterations, int evaluations, Solution[] population) {
		return iterations >= maxIterations;
	}
}
