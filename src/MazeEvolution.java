package cellularuniverse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MazeEvolution extends JFrame {

    private static final String[] MAZE = {

        "####################",
        "#S     #            #",
        "#####  #  ########  #",
        "#      #           ##",
        "#  ###########  #   #",
        "#              # #  #",
        "############## # #  #",
        "#               #   G",
        "####################"
    };

    private static final int GOAL_ROW = 7;
    private static final int GOAL_COL = 19;

    private final EvolutionEngine evolutionEngine;
    private final MazePanel mazePanel;

    private final JLabel generationLabel;
    private final JLabel fitnessLabel;
    private final JButton runButton;

    private Timer timer;

    private boolean running = false;

    private int generation = 0;

    public MazeEvolution() {

        setTitle("Goal-Directed Evolution");

        setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        // -------------------------
        // Evolution engine
        // -------------------------

        evolutionEngine =
            new EvolutionEngine(
                MAZE,
                GOAL_ROW,
                GOAL_COL
            );

        // -------------------------
        // Maze
        // -------------------------

        mazePanel =
            new MazePanel(MAZE);

        add(
            mazePanel,
            BorderLayout.CENTER
        );

        // -------------------------
        // Information
        // -------------------------

        JPanel information =
            new JPanel(
                new FlowLayout(
                    FlowLayout.LEFT
                )
            );

        generationLabel =
            new JLabel(
                "Generation: 0"
            );

        fitnessLabel =
            new JLabel(
                "Distance: -"
            );

        information.add(
            generationLabel
        );

        information.add(
            Box.createHorizontalStrut(20)
        );

        information.add(
            fitnessLabel
        );

        add(
            information,
            BorderLayout.NORTH
        );

        // -------------------------
        // Controls
        // -------------------------

        JPanel controls =
            new JPanel();

        runButton =
            new JButton("Run");

        JButton stepButton =
            new JButton("Step");

        JButton resetButton =
            new JButton("Reset");

        runButton.addActionListener(e -> {

            if (!running) {

                running = true;

                runButton.setText("Pause");

                timer.start();

            } else {

                running = false;

                runButton.setText("Run");

                timer.stop();
            }
        });

        stepButton.addActionListener(e -> {

            evolve();
        });

        resetButton.addActionListener(e -> {

            reset();
        });

        controls.add(runButton);
        controls.add(stepButton);
        controls.add(resetButton);

        add(
            controls,
            BorderLayout.SOUTH
        );

        // -------------------------
        // Timer
        // -------------------------

        timer =
            new Timer(
                50,
                e -> evolve()
            );

        // -------------------------
        // Start
        // -------------------------

        reset();

        pack();

        setLocationRelativeTo(null);

        setResizable(false);
    }

    private void evolve() {

        evolutionEngine.evolve();

        generation++;

        updateDisplay();
    }

    private void reset() {

        timer.stop();

        running = false;

        runButton.setText("Run");

        generation = 0;

        evolutionEngine.reset();

        updateDisplay();
    }

    private void updateDisplay() {

        Agent bestAgent =
            evolutionEngine.getBestAgent();

        generationLabel.setText(
            "Generation: " + generation
        );

        fitnessLabel.setText(
            "Distance: " +
            bestAgent.getFitness()
        );

        mazePanel.setBestAgent(
            bestAgent
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MazeEvolution maze =
                new MazeEvolution();

            maze.setVisible(true);
        });
    }
}