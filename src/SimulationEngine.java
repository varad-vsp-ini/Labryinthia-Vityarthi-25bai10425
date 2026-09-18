package cellularuniverse;

public class SimulationEngine {

    private final UniverseGrid grid;

    private CARule rule;

    public SimulationEngine(
            UniverseGrid grid,
            CARule rule) {

        this.grid = grid;
        this.rule = rule;
    }

    public void setRule(CARule rule) {

        this.rule = rule;
    }

    public void step() {

        int rows = grid.getRows();
        int cols = grid.getCols();

        boolean[][] next =
                new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                int neighbors =
                        countNeighbors(row, col);

                next[row][col] =
                        rule.willBeAlive(
                                grid.getCell(row, col),
                                neighbors
                        );
            }
        }

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                grid.setCell(
                        row,
                        col,
                        next[row][col]
                );
            }
        }
    }

    private int countNeighbors(
            int row,
            int col) {

        int count = 0;

        for (int dr = -1; dr <= 1; dr++) {

            for (int dc = -1; dc <= 1; dc++) {

                if (dr == 0 && dc == 0) {
                    continue;
                }

                int newRow = row + dr;
                int newCol = col + dc;

                if (newRow >= 0 &&
                    newRow < grid.getRows() &&
                    newCol >= 0 &&
                    newCol < grid.getCols()) {

                    if (grid.getCell(
                            newRow,
                            newCol)) {

                        count++;
                    }
                }
            }
        }

        return count;
    }
}