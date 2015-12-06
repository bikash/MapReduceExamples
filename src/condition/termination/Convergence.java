package condition.termination;

import solutions.Solution;

public class Convergence extends Termination {
	
	protected int k;
	
	protected int count;
	
	protected Solution bestSolution; 
	
	public Convergence(int k) {
		this.k = k;		
		this.count = 0;
	}

	@Override
	public boolean terminationCondition(int iterations, int evaluations, Solution[] population) {
		if (bestSolution == null) {
			bestSolution = population[0].clone();
		} else {
			if (population[0].getFitness() != bestSolution.getFitness()) {
				bestSolution = population[0].clone();
				count = 0;
			} else {
				count++;
			}
		}
		
		return count == k;
	}
	
}
