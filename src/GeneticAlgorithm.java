public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int tournamentSize;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int tournamentSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.tournamentSize = tournamentSize;
    }

    public Timetable[] evolvePopulation(Timetable[] population) {
        Timetable[] newPopulation = new Timetable[populationSize];

        // Keep the best timetable (Elitism)
        newPopulation[0] = getFittest(population);

        // Fill the rest of the new population
        for (int i = 1; i < populationSize; i++) {
            // 1. Selection
            Timetable parent1 = tournamentSelection(population);
            Timetable parent2 = tournamentSelection(population);

            // 2. Crossover
            Timetable child;
            if (Math.random() < crossoverRate) {
                child = crossover(parent1, parent2);
            } else {
                child = parent1; // No crossover, just copy a parent
            }
            
            // 3. Mutation
            mutate(child);
            
            newPopulation[i] = child;
        }

        return newPopulation;
    }

    // Selection Strategy: Tournament Selection 
    private Timetable tournamentSelection(Timetable[] population) {
        Timetable best = null;
        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = (int) (Math.random() * populationSize);
            Timetable randomTimetable = population[randomIndex];
            if (best == null || randomTimetable.getFitness() > best.getFitness()) {
                best = randomTimetable;
            }
        }
        return best;
    }

    // Crossover Operator: Single-Point 
    private Timetable crossover(Timetable parent1, Timetable parent2) {
        Timetable child = new Timetable();
        int crossoverPoint = (int) (Math.random() * Timetable.NUM_SLOTS);

        for (int i = 0; i < Timetable.NUM_SLOTS; i++) {
            if (i < crossoverPoint) {
                child.setGene(i, parent1.getGene(i));
            } else {
                child.setGene(i, parent2.getGene(i));
            }
        }
        return child;
    }

    // Mutation Operator: Random Reassignment 
    private void mutate(Timetable timetable) {
        for (int i = 0; i < Timetable.NUM_SLOTS; i++) {
            if (Math.random() < mutationRate) {
                // Change to a new random subject
                timetable.setGene(i, (int) (Math.random() * Timetable.NUM_SUBJECTS));
            }
        }
    }

    // Helper: Get the fittest individual from a population
    public Timetable getFittest(Timetable[] population) {
        Timetable fittest = population[0];
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() > fittest.getFitness()) {
                fittest = population[i];
            }
        }
        return fittest;
    }
}