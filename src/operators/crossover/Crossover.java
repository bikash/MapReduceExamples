package operators.crossover;

import operators.Operator;
import solutions.Solution;
import util.ParameterSet;

public abstract class Crossover extends Operator {

	public abstract Solution[] execute(ParameterSet parameters, Solution s1, Solution s2);
}
