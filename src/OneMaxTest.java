import metaheuristics.ga.GeneticAlgorithm;
import operators.crossover.SinglePointCrossover;
import operators.mutation.BitFlipMutation;
import operators.selection.RouletteWheel;
import problems.Problem;
import problems.examples.OneMaxProblem;
import solutions.Solution;
import util.Logger;
import condition.termination.Iterations;

public class OneMaxTest {

	public static void main(String args[]) {
		
		Logger.setLevel(Logger.BEST_SOLUTION);
		
		Problem p = new OneMaxProblem(10);

		GeneticAlgorithm ga = new GeneticAlgorithm();

		// Parameters
		ga.addParameters("size_population", 10);
		ga.addParameters("rate_elitism", 0.2);
		ga.addParameters("prob_crossover", 1);
		ga.addParameters("prob_mutation", 0.0005);

		// Operators
		ga.addSelectionOperator(new RouletteWheel());
		ga.addCrossoverOperator(new SinglePointCrossover());
		ga.addMutationOperator(new BitFlipMutation());

		// Termination Condition
		ga.addTerminationCondition(new Iterations(100));
		
		// Run
		Solution bestSolution = ga.solve(p);

		System.out.println("Best Solution:" + bestSolution);
		System.out.println("Evaluations: " + ga.getEvaluations());
		System.out.println("Iterations: " + ga.getIterations());
	}
}
