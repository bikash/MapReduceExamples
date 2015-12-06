package operators.selection;

import solutions.Solution;
import util.ParameterSet;
import util.PseudoRandom;

public class RouletteWheel extends Selection {

	protected int select(double[] probability, double sumProbability) {
		int j = 0;
		double p = probability[j];
		double r = PseudoRandom.randDouble(0.0, sumProbability);

		while (p < r) {
			j = j + 1;
			p = p + probability[j];
		}

		return j;
	}

	@Override
	public Solution execute(ParameterSet parameters, Solution[] population) {
		double sumProbability = 0.0;
		double[] probability = new double[population.length];

		// Sum all population's fitness
		for (int i = 0; i < population.length; i++) {
			sumProbability += Math.abs(population[i].getFitness());
		}

		// Normalize the probability
		for (int i = 0; i < population.length; i++) {
			probability[i] = Math.abs(population[i].getFitness()) / sumProbability;
		}

		return population[select(probability, 1.0)];
	}
}
