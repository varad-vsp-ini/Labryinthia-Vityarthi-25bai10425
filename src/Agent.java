package cellularuniverse;

import java.util.Random;

public class Agent {

    private static final int PATH_LENGTH = 150;

    private final Random random = new Random();

    private char[] moves;
    private int fitness;

    public Agent() {

        moves = new char[PATH_LENGTH];

        char[] directions = {'U', 'D', 'L', 'R'};

        for (int i = 0; i < PATH_LENGTH; i++) {
            moves[i] = directions[random.nextInt(4)];
        }
    }

    public Agent copy() {

        Agent copy = new Agent();

        copy.moves = moves.clone();
        copy.fitness = fitness;

        return copy;
    }

    public void mutate() {

        char[] directions = {'U', 'D', 'L', 'R'};

        for (int i = 0; i < moves.length; i++) {

            if (random.nextDouble() < 0.02) {
                moves[i] = directions[random.nextInt(4)];
            }
        }
    }

    public void evaluate(String[] maze, int goalRow, int goalCol) {

        int row = 1;
        int col = 1;

        for (char move : moves) {

            int newRow = row;
            int newCol = col;

            switch (move) {

                case 'U':
                    newRow--;
                    break;

                case 'D':
                    newRow++;
                    break;

                case 'L':
                    newCol--;
                    break;

                case 'R':
                    newCol++;
                    break;
            }

            if (newRow >= 0 &&
                newRow < maze.length &&
                newCol >= 0 &&
                newCol < maze[0].length() &&
                maze[newRow].charAt(newCol) != '#') {

                row = newRow;
                col = newCol;
            }

            if (maze[row].charAt(col) == 'G') {
                break;
            }
        }

        fitness =
            Math.abs(row - goalRow)
            +
            Math.abs(col - goalCol);
    }

    public int getFitness() {
        return fitness;
    }

    public char[] getMoves() {
        return moves;
    }
}