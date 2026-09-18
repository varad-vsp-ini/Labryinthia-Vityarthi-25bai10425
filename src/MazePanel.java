package cellularuniverse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MazePanel extends JPanel {

    private static final int CELL_SIZE = 30;

    private final String[] maze;

    private Agent bestAgent;

    private final int rows;
    private final int cols;

    public MazePanel(String[] maze) {

        this.maze = maze;

        rows = maze.length;
        cols = maze[0].length();

        setPreferredSize(
            new Dimension(
                cols * CELL_SIZE,
                rows * CELL_SIZE
            )
        );
    }

    public void setBestAgent(Agent bestAgent) {

        this.bestAgent = bestAgent;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw maze
        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                char cell =
                    maze[row].charAt(col);

                if (cell == '#') {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(
                    col * CELL_SIZE,
                    row * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE
                );

                g.setColor(Color.LIGHT_GRAY);

                g.drawRect(
                    col * CELL_SIZE,
                    row * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE
                );
            }
        }

        // Draw start
        g.setColor(Color.GREEN);

        g.fillRect(
            1 * CELL_SIZE,
            1 * CELL_SIZE,
            CELL_SIZE,
            CELL_SIZE
        );

        // Draw goal
        g.setColor(Color.RED);

        g.fillRect(
            19 * CELL_SIZE,
            7 * CELL_SIZE,
            CELL_SIZE,
            CELL_SIZE
        );

        // Draw best agent
        if (bestAgent != null) {

            int[] position =
                getFinalPosition(bestAgent);

            g.setColor(Color.BLUE);

            g.fillOval(
                position[1] * CELL_SIZE + 5,
                position[0] * CELL_SIZE + 5,
                CELL_SIZE - 10,
                CELL_SIZE - 10
            );
        }
    }

    private int[] getFinalPosition(Agent agent) {

        int row = 1;
        int col = 1;

        for (char move : agent.getMoves()) {

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
                newRow < rows &&
                newCol >= 0 &&
                newCol < cols &&
                maze[newRow].charAt(newCol) != '#') {

                row = newRow;
                col = newCol;
            }

            if (maze[row].charAt(col) == 'G') {
                break;
            }
        }

        return new int[] {row, col};
    }
}