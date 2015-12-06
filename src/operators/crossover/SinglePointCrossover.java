package operators.crossover;

import solutions.Solution;
import util.ParameterSet;
import util.PseudoRandom;

public class SinglePointCrossover extends Crossover {
	
	public Solution[] doCrossover(double prob, Solution s1, Solution s2) {
		int pos = PseudoRandom.randInt(0, s1.getNumberOfBits() - 1);
		return doCrossover(prob, pos, s1, s2);
	}

	public Solution[] doCrossover(double prob,int pos, Solution s1, Solution s2) {
		Solution[] childrens = new Solution[2];

		if (PseudoRandom.randDouble() <= prob) {
			childrens[0] = s1.clone();
			childrens[1] = s2.clone();
			
			int numberOfBits = s1.getNumberOfBits();
			
			// Generate the first child
			for (int i = 0; i < numberOfBits; i++) {
				if (i <= pos) {
					childrens[0].setValue(i, s1.getValue(i));
				}else{
					childrens[0].setValue(i, s2.getValue(i));
				}
			}

			// Generate the second child
			for (int i = 0; i < numberOfBits; i++) {
				if (i <= pos) {
					childrens[1].setValue(i, s2.getValue(i));
				}else{
					childrens[1].setValue(i, s1.getValue(i));
				}
			}
		} else {
			childrens[0] = s1;
			childrens[1] = s2;
		}
		/*for (int i = 0; i < childrens.length; i++) {
			System.out.println("crossover" + childrens[i]);
		}*/
		return childrens;
	}

	@Override
	public Solution[] execute(ParameterSet parameters, Solution s1, Solution s2){
		// Default value for crossover's probability
		double probCrossover = 0.6;

		// Read the crossover's probability defined by user
		if (parameters.exists("prob_crossover")) {
			probCrossover = parameters.getDouble("prob_crossover");
		}
		
		return doCrossover(probCrossover, s1, s2);
	}

}
