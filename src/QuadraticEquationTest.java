import metaheuristics.ga.GeneticAlgorithm;
import operators.crossover.SinglePointCrossover;
import operators.mutation.BitFlipMutation;
import operators.selection.RouletteWheel;
import problems.Problem;
import problems.examples.QuadraticEquationProblem;
import solutions.Solution;
import condition.termination.Iterations;

public class QuadraticEquationTest {

	public static void main(String args[]) {
		Problem p = new QuadraticEquationProblem(22, -10.0, 10.0);

		GeneticAlgorithm ga = new GeneticAlgorithm();

		// Parameters
		ga.addParameters("size_population", 4);
		ga.addParameters("rate_elitism", 0.0);
		ga.addParameters("prob_crossover", 0.6);
		ga.addParameters("prob_mutation", 0.01);

		// Operators
		ga.addSelectionOperator(new RouletteWheel());
		ga.addCrossoverOperator(new SinglePointCrossover());
		ga.addMutationOperator(new BitFlipMutation());

		// Termination Condition
		ga.addTerminationCondition(new Iterations(100));
		
		// Run
		Solution bestSolution = ga.solve(p);

		System.out.println(bestSolution);
	}
}
