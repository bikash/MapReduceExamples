package condition.termination;

import solutions.Solution;
import condition.Condition;

public abstract class Termination extends Condition{

	public abstract boolean terminationCondition(int iterations, int evaluations, Solution[] population);
}
