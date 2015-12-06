package metaheuristics.ga;

import java.util.Arrays;

import metaheuristics.Metaheuristic;
import operators.crossover.Crossover;
import operators.crossover.SinglePointCrossover;
import operators.mutation.BitFlipMutation;
import operators.mutation.Mutation;
import operators.selection.RouletteWheel;
import operators.selection.Selection;
import problems.Problem;
import solutions.Solution;
import util.Logger;

public class GeneticAlgorithm extends Metaheuristic {

	protected Selection selection;

	protected Crossover crossover;

	protected Mutation mutation;
	
	protected Solution[] population;

	public GeneticAlgorithm() {
		this.selection = new RouletteWheel();
		this.crossover = new SinglePointCrossover();
		this.mutation = new BitFlipMutation();
	}

	@Override
	public Solution solve(Problem p) {
		super.p = p;
		
		createInitialPopulation();
		evaluatePopulation();

		while (!terminationCondition()) {
			// Elitism
			Solution[] bestIndividuals = doElitism();
			// Selection
			Solution[] parents = doSelection();
			// Crossover
			Solution[] offspring = doCrossover(parents);
			// Mutation
			doMutation(offspring);
			
			updatePopulation(bestIndividuals, offspring);
			evaluatePopulation();			
		}
		
		return bestElement();
	}
	
	protected Solution[] doElitism() {
		// Number of Elitism
		int n = 0;
		
		// Read the elitism's probability
		if (parameters.exists("rate_elitism")) {
			n = (int) (population.length * parameters.getDouble("rate_elitism"));
		}
		
		// Save the number of elitism.
		parameters.add("n_elitism", n);
			
		Solution[] bestIndividuals = new Solution[n];

		for (int i = 0; i < n; i++) {
			bestIndividuals[i] = population[i];
		}
		
		Logger.log(Logger.DEBUG, "Elitism:");
		Logger.log(Logger.DEBUG, toString(bestIndividuals));
		
		return bestIndividuals;
	}

	protected Solution[] doSelection() {
		// Number of Elitism
		int nElitism = 0;
		
		// Read the elitism's probability
		if (parameters.exists("n_elitism")) {
			nElitism = parameters.getInt("n_elitism");
		}
				
		Solution[] parents = new Solution[population.length - nElitism];
		
		// Select the parents
		for (int i = 0; i < parents.length; i++) {
			parents[i] = selection.execute(parameters, population);
		}
		
		Logger.log(Logger.DEBUG, "Parents:");
		Logger.log(Logger.DEBUG, toString(parents));
		
		return parents;
	}
	
	protected Solution[] doCrossover(Solution[] parents){
		Solution[] offspring = new Solution[parents.length];
		
		for (int i = 0; i < parents.length; i += 2) {
			Solution[] childrens = null;

			if (i + 1 == parents.length) {
				childrens = crossover.execute(parameters, parents[i], parents[0]);
			} else {
				childrens = crossover.execute(parameters, parents[i], parents[i + 1]);
			}
			
			if (childrens == null || childrens.length != 2) {
				throw new IllegalArgumentException("The childrens cannot be null or different of two");
			}

			offspring[i] = childrens[0];

			if (i + 1 != parents.length) {
				offspring[i + 1] = childrens[1];
			}
		}
		
		Logger.log(Logger.DEBUG, "Crossover:");
		Logger.log(Logger.DEBUG, toString(offspring));
		
		return offspring;	
	}
	
	protected void doMutation(Solution[] offspring){
		mutation.execute(parameters, offspring);
		
		Logger.log(Logger.DEBUG, "Mutation:");
		Logger.log(Logger.DEBUG, toString(offspring));
	}
	
	protected Solution bestElement() {
		return population[0];
	}
	
	protected void updatePopulation(Solution[] bestIndividuals, Solution[] offspring) {
		int index = 0;
		// Copy the best individuals
		for (int i = 0; i < bestIndividuals.length; i++) {
			population[index++] = bestIndividuals[i];
		}
		// Copy the offspring
		for (int i = 0; i < offspring.length; i++) {
			population[index++] = offspring[i];
		}
		iterations++;
	}
	
	protected boolean terminationCondition() {
		return termination.terminationCondition(iterations, evaluations, population);
	}

	protected void evaluatePopulation() {
		// Evaluate each population's individual
		for (int i = 0; i < population.length; i++) {
			population[i].setFitness(p.evaluate(population[i]));
			evaluations++;
		}

		// Sort population
		Arrays.sort(population);
		
		Logger.log(Logger.DEBUG, "Population:");
		Logger.log(Logger.DEBUG, toString(population));
		
		Logger.log(Logger.BEST_SOLUTION, "Best Solution:");
		Logger.log(Logger.BEST_SOLUTION, population[0].toString());
	}

	protected void createInitialPopulation() {
		int sizePopulation = 100;

		if (parameters.exists("size_population")) {
			sizePopulation = parameters.getInt("size_population");
		}
		
		//Create the new population
		population = new Solution[sizePopulation];

		for (int i = 0; i < population.length; i++) {
			population[i] = p.generateValidRandomSolution();
		}		
	}

	public String toString(Solution[] population) {
		String result = "";

		for (int i = 0; i < population.length; i++) {
			result += population[i];
			if (i + 1 != population.length) {
				result += "\n";
			}
		}

		return result;
	}
	
	public void addSelectionOperator(Selection selection) {
		this.selection = selection;
	}

	public void addCrossoverOperator(Crossover crossover) {
		this.crossover = crossover;
	}

	public void addMutationOperator(Mutation mutation) {
		this.mutation = mutation;
	}	
}
