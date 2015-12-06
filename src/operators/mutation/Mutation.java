package operators.mutation;

import operators.Operator;
import solutions.Solution;
import util.ParameterSet;

public abstract class Mutation extends Operator {

	public abstract void execute(ParameterSet parameters, Solution[] offspring);
}
