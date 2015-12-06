package operators.mutation;

import solutions.Solution;
import util.ParameterSet;
import util.PseudoRandom;

public class BitFlipMutation extends Mutation {

	public void mute(double probability, Solution solution) {
		for (int i = 0; i < solution.getNumberOfBits(); i++) {
			if (PseudoRandom.randDouble() < probability) {
				solution.flip(i);
			}
		}
	}

	@Override
	public void execute(ParameterSet parameters, Solution[] offspring) {
		// Default value for mutation's probability
		double probMutation = 1.0 / (10.0 * offspring[0].getNumberOfBits());

		// Read the elitism's probability defined by user
		if (parameters.exists("prob_mutation")) {
			probMutation = parameters.getDouble("prob_mutation");
		}

		for (int i = 0; i < offspring.length; i++) {
			mute(probMutation, offspring[i]);
		}
	}
}
