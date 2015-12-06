package metaheuristics;

import condition.termination.Iterations;
import condition.termination.Termination;
import problems.Problem;
import solutions.Solution;
import util.ParameterSet;

public abstract class Metaheuristic {

	protected ParameterSet parameters;
	
	protected Termination termination;

	protected Problem p;
	
	/** Number of Evaluations */
	protected int evaluations;
	
	/** Number of Iterations */
	protected int iterations;
	
	public Metaheuristic() {
		this.evaluations = 0;
		this.iterations = 1;
		this.parameters = new ParameterSet();
		this.termination = new Iterations(1000);
	}

	public void addParameters(String name, Object value) {
		this.parameters.add(name, value);
	}
	
	public void addTerminationCondition(Termination termination) {
		this.termination = termination;
	}
	
	public int getEvaluations() {
		return evaluations;
	}

	public int getIterations() {
		return iterations;
	}

	public abstract Solution solve(Problem p);
}
