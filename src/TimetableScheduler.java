import java.util.Arrays;

public class TimetableScheduler {

    // GA Parameters
    public static final int POPULATION_SIZE = 5;
    public static final double MUTATION_RATE = 0.02; // 2% chance
    public static final double CROSSOVER_RATE = 0.9; // 90% chance
    public static final int TOURNAMENT_SIZE = 3;
    public static final int MAX_GENERATIONS = 1000;
    public static final int PERFECT_FITNESS = 160; // Our defined perfect score

    // Subjects and Time definitions
    public static final String[] SUBJECTS = {"Data Structures", "Algorithms", "DBMS", "Operating Systems", "Computer Networks"};
    public static final String[] TIME_SLOTS = {
        "09:00 - 10:00",
        "10:00 - 11:00",
        "11:00 - 12:00",
        "12:00 - 13:00",
        "13:00 - 14:00",
        "14:00 - 15:00"
    };

    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm(POPULATION_SIZE, MUTATION_RATE, CROSSOVER_RATE, TOURNAMENT_SIZE);

        // 1. Initialize Population
        Timetable[] population = new Timetable[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population[i] = new Timetable();
        }

        int generation = 0;
        Timetable bestTimetable = null;

        // 2. Start evolution loop 
        while (generation < MAX_GENERATIONS) {
            
            // Calculate fitness for all
            for (Timetable timetable : population) {
                timetable.calculateFitness();
            }

            bestTimetable = ga.getFittest(population);

            // Display intermediate results 
            System.out.println("Generation: " + generation + 
                               " | Fittest: " + bestTimetable.getFitness() + 
                               " | Schedule: " + Arrays.toString(bestTimetable.getSchedule()));

            // Termination condition: Found a perfect solution
            if (bestTimetable.getFitness() == PERFECT_FITNESS) {
                System.out.println("\nFound optimal solution!");
                break;
            }

            // Evolve to the next generation
            population = ga.evolvePopulation(population);
            generation++;
        }
        
        // 3. Output the final timetable 
        System.out.println("\n--- Final Generated Timetable ---");
        printTimetable(bestTimetable);
    }

    // Helper function to print the timetable in a clear format
    public static void printTimetable(Timetable timetable) {
        System.out.println("------------------------------------------");
        System.out.printf("| %-15s | %-20s |%n", "Time Slot", "Subject");
        System.out.println("------------------------------------------");
        
        int[] schedule = timetable.getSchedule();
        for (int i = 0; i < schedule.length; i++) {
            String subjectName = SUBJECTS[schedule[i]];
            String timeSlot = TIME_SLOTS[i];
            System.out.printf("| %-15s | %-20s |%n", timeSlot, subjectName);
        }
        System.out.println("------------------------------------------");
    }
}