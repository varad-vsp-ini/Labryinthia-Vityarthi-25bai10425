package cellularuniverse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class EvolutionEngine {

    private static final int POPULATION_SIZE = 200;
    private static final int ELITE_COUNT = 20;

    private final Random random = new Random();

    private final String[] maze;

    private final int goalRow;
    private final int goalCol;

    private ArrayList<Agent> population;

    private Agent bestAgent;

    public EvolutionEngine(
        String[] maze,
        int goalRow,
        int goalCol
    ) {

        this.maze = maze;
        this.goalRow = goalRow;
        this.goalCol = goalCol;

        reset();
    }

    public void reset() {

        population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new Agent());
        }

        evaluatePopulation();
    }

    public void evolve() {

        evaluatePopulation();

        population.sort(
            Comparator.comparingInt(
                Agent::getFitness
            )
        );

        bestAgent = population.get(0);

        ArrayList<Agent> next =
            new ArrayList<>();

        // Keep the best agents
        for (int i = 0; i < ELITE_COUNT; i++) {
            next.add(
                population.get(i).copy()
            );
        }

        // Create mutated children
        while (next.size() < POPULATION_SIZE) {

            Agent parent =
                population.get(
                    random.nextInt(ELITE_COUNT)
                );

            Agent child =
                parent.copy();

            child.mutate();

            next.add(child);
        }

        population = next;

        evaluatePopulation();
    }

    private void evaluatePopulation() {

        for (Agent agent : population) {
            agent.evaluate(
                maze,
                goalRow,
                goalCol
            );
        }

        population.sort(
            Comparator.comparingInt(
                Agent::getFitness
            )
        );

        bestAgent = population.get(0);
    }

    public Agent getBestAgent() {
        return bestAgent;
    }
}