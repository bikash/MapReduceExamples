package operators.selection;

import operators.Operator;
import solutions.Solution;
import util.ParameterSet;

public abstract class Selection extends Operator {

	public abstract Solution execute(ParameterSet parameters, Solution[] population);
}
