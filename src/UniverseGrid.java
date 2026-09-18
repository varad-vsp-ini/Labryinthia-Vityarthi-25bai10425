package cellularuniverse;

import java.util.Random;

public class UniverseGrid {

    private final int rows;
    private final int cols;

    private boolean[][] cells;

    private final Random random = new Random();

    public UniverseGrid(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        cells = new boolean[rows][cols];
    }

    public boolean getCell(int row, int col) {

        return cells[row][col];
    }

    public void setCell(
            int row,
            int col,
            boolean alive) {

        cells[row][col] = alive;
    }

    public void toggleCell(
            int row,
            int col) {

        cells[row][col] =
                !cells[row][col];
    }

    public void randomize() {

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                cells[row][col] =
                        random.nextDouble() < 0.25;
            }
        }
    }

    public void clear() {

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                cells[row][col] = false;
            }
        }
    }

    public int countPopulation() {

        int population = 0;

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (cells[row][col]) {
                    population++;
                }
            }
        }

        return population;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}