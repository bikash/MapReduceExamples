import metaheuristics.ga.GeneticAlgorithm;
import operators.crossover.SinglePointCrossover;
import operators.mutation.BitFlipMutation;
import operators.selection.RouletteWheel;
import problems.Problem;
import problems.examples.SenoProblem;
import solutions.Solution;
import condition.termination.Iterations;

public class SenoTest {

	public static void main(String args[]) {
		Problem p = new SenoProblem(22, -1.0, 2.0);

		GeneticAlgorithm ga = new GeneticAlgorithm();

		// Parameters
		ga.addParameters("size_population", 100);
		ga.addParameters("rate_elitism", 0.2);
		ga.addParameters("prob_crossover", 0.8);
		ga.addParameters("prob_mutation", 0.0005);

		// Operators
		ga.addSelectionOperator(new RouletteWheel());
		ga.addCrossoverOperator(new SinglePointCrossover());
		ga.addMutationOperator(new BitFlipMutation());

		// Termination Condition
		ga.addTerminationCondition(new Iterations(25));
		
		// Run
		Solution bestSolution = ga.solve(p);

		System.out.println(bestSolution);
	}
}
