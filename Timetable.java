import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a single timetable (a "chromosome") 
 * It holds the genes (the schedule) and its fitness score.
 */
public class Timetable {

    public static final int NUM_SUBJECTS = 5;
    public static final int NUM_SLOTS = 6;

    private int[] schedule;
    private int fitness;

    public Timetable() {
        this.schedule = new int[NUM_SLOTS];
        this.fitness = 0;
        // Initialize with a random schedule
        for (int i = 0; i < NUM_SLOTS; i++) {
            this.schedule[i] = (int) (Math.random() * NUM_SUBJECTS);
        }
    }

    // Calculates the fitness of this timetable 
    public void calculateFitness() {
        int score = 0;

        // Rule 1: Check for unique subjects 
        Set<Integer> uniqueSubjects = new HashSet<>();
        for (int subjectId : schedule) {
            uniqueSubjects.add(subjectId);
        }
        score += uniqueSubjects.size() * 20;

        // Rule 2: Check for excessive repeats 
        // Count occurrences of each subject
        Map<Integer, Long> counts = Arrays.stream(schedule)
        .boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int subjectsWithOneSlot = 0;
        int subjectsWithTwoSlots = 0;

        for (int subjectId = 0; subjectId < NUM_SUBJECTS; subjectId++) {
            long count = counts.getOrDefault(subjectId, 0L);
            if (count == 1) {
                subjectsWithOneSlot++;
            } else if (count == 2) {
                subjectsWithTwoSlots++;
            }
        }

        // Reward 4 subjects with 1 slot and 1 subject with 2 slots
        score += subjectsWithOneSlot * 10;
        if (subjectsWithTwoSlots == 1) {
            score += 10;
        }

        this.fitness = score;
    }

    // --- Getters and Setters ---
    public int getFitness() {
        return fitness;
    }

    public int[] getSchedule() {
        return schedule;
    }

    public void setGene(int index, int value) {
        this.schedule[index] = value;
        this.fitness = 0; // Reset fitness as schedule has changed
    }

    public int getGene(int index) {
        return this.schedule[index];
    }
}