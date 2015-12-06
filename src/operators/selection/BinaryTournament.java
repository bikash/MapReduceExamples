package operators.selection;

import solutions.Solution;
import util.ParameterSet;
import util.PseudoRandom;

public class BinaryTournament extends Selection {

	@Override
	public Solution execute(ParameterSet parameters, Solution[] population) {
		Solution s1 = population[PseudoRandom.randInt(0, population.length - 1)];
		Solution s2 = population[PseudoRandom.randInt(0, population.length - 1)];

		int flag = s1.compareTo(s2);

		if (flag == -1) {
			return s1;
		} else if (flag == 1) {
			return s2;
		} else {
			if (PseudoRandom.randDouble() < 0.5) {
				return s1;
			} else {
				return s2;
			}
		}
	}
}
