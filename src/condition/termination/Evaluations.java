package condition.termination;

import solutions.Solution;

public class Evaluations extends Termination{

	protected int maxEvaluations;
	
	public Evaluations(int maxEvaluations){
		this.maxEvaluations = maxEvaluations;
	}
	
	@Override
	public boolean terminationCondition(int iterations, int evaluations, Solution[] population) {
		return evaluations >= maxEvaluations;
	}
}
