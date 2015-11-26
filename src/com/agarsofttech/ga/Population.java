package com.agarsofttech.ga;
public class Population {

    Chromosome[] individuals;

    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        individuals = new Chromosome[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Chromosome newIndividual = new Chromosome();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Getters */
    public Chromosome getIndividual(int index) {
        return individuals[index];
    }

    public Chromosome getFittest() {
        Chromosome fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Chromosome indiv) {
        individuals[index] = indiv;
    }
}