# 🧬 Genetic Algorithm Timetable Scheduler

This Java project uses a **Genetic Algorithm (GA)** to solve a class timetable scheduling problem. It evolves a random population of timetables over generations to find an optimal solution that meets a set of constraints.

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

1.  **Clone the repo**
    ```bash
    git clone [https://github.com/your-username/your-repository-name.git](https://github.com/your-username/your-repository-name.git)
    ```
2.  Navigate to the project directory
    ```bash
    cd your-repository-name
    ```

## 📁 File Structure

Here is an overview of the key files in the project:

* **`TimetableScheduler.java`**: This is the main runnable class. It contains the `main` method and all the top-level configuration parameters (like population size, mutation rate, etc.).
* **`GeneticAlgorithm.java`**: This is the "engine" of the GA. It holds the core logic for selection (choosing parents), crossover (breeding), and mutation (random changes).
* **`Timetable.java`**: This class represents a single timetable (a "chromosome"). It holds its own schedule and contains the all-important `calculateFitness()` method, which defines the rules for a "good" timetable.

## 🏃 How to Run

1.  Now that you know the files, compile all the Java source files:
    ```bash
    javac TimetableScheduler.java GeneticAlgorithm.java Timetable.java
    ```
2.  Run the main class to start the simulation:
    ```bash
    java TimetableScheduler
    ```
The program will print its progress and output the final, optimized timetable.

## 🔧 How to Tweak (Configuration)

All key parameters are at the top of **`TimetableScheduler.java`**.

* `POPULATION_SIZE`: Number of timetables per generation (Default: 100).
* `MUTATION_RATE`: Chance for a random change (Default: 0.02, or 2%).
* `CROSSOVER_RATE`: Chance for two timetables to "breed" (Default: 0.9).
* `MAX_GENERATIONS`: The safety stop if no solution is found (Default: 1000).

To change the problem rules (e.g., add new subjects or constraints), you must modify:
* **`TimetableScheduler.java`**: Update the `SUBJECTS` array.
* **`Timetable.java`**: Update `NUM_SUBJECTS`, `NUM_SLOTS`, and most importantly, the logic inside the `calculateFitness()` method.
