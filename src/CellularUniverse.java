package cellularuniverse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class CellularUniverse extends JFrame {

    // =========================
    // WORLD SETTINGS
    // =========================

    static final int ROWS = 40;
    static final int COLS = 60;
    static final int CELL_SIZE = 15;

    private final UniverseGrid grid;
    private final SimulationEngine simulation;

    private int generation = 0;

    private CARule rule;
    
    private final Random random = new Random();

    private final JPanel gridPanel;

    private final JLabel generationLabel;
    private final JLabel populationLabel;

    private JTextField ruleField;

    private Timer timer;
    private boolean running = false;


    // =========================
    // CONSTRUCTOR
    // =========================

    public CellularUniverse() {

    	grid = new UniverseGrid(ROWS, COLS);

    	rule = new CARule("B3/S23");

    	simulation =
    	        new SimulationEngine(
    	                grid,
    	                rule
    	        );

        setTitle("Cellular Universe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // =========================
        // GRID
        // =========================

        gridPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                for (int row = 0; row < ROWS; row++) {

                    for (int col = 0; col < COLS; col++) {

                        int x = col * CELL_SIZE;
                        int y = row * CELL_SIZE;


                        // -------------------------
                        // Draw living cells
                        // -------------------------

                        if (grid.getCell(row, col)) {

                            g.setColor(Color.BLACK);

                            g.fillRect(
                                x,
                                y,
                                CELL_SIZE,
                                CELL_SIZE
                            );
                        }


                        // -------------------------
                        // Draw grid lines
                        // -------------------------

                        g.setColor(Color.LIGHT_GRAY);

                        g.drawRect(
                            x,
                            y,
                            CELL_SIZE,
                            CELL_SIZE
                        );
                    }
                }
            }
        };


        gridPanel.setPreferredSize(
            new Dimension(
                COLS * CELL_SIZE,
                ROWS * CELL_SIZE
            )
        );


        // =========================
        // CLICK CELLS
        // =========================

        gridPanel.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {

                    int col =
                        e.getX() / CELL_SIZE;

                    int row =
                        e.getY() / CELL_SIZE;


                    if (row >= 0 &&
                        row < ROWS &&
                        col >= 0 &&
                        col < COLS) {

                    	grid.toggleCell(row, col);

                        updateDisplay();
                    }
                }
            }
        );


        add(
            gridPanel,
            BorderLayout.CENTER
        );


        // =========================
        // CONTROLS
        // =========================

        JPanel controls =
            new JPanel();


        JButton randomButton =
            new JButton("Randomize");

        JButton stepButton =
            new JButton("Step");

        JButton runButton =
            new JButton("Run");

        JButton clearButton =
            new JButton("Clear");
        
        JButton evolutionButton =
        	new JButton("Goal Evolution");


        // -------------------------
        // Randomize
        // -------------------------

        randomButton.addActionListener(e -> {

            randomize();

        });


        // -------------------------
        // One generation
        // -------------------------

        stepButton.addActionListener(e -> {

            step();

        });


        // -------------------------
        // Run / Pause
        // -------------------------

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


        // -------------------------
        // Clear
        // -------------------------

        clearButton.addActionListener(e -> {

            clear();

        });
        
        //---------------------------
        // Goal Evolution
        //---------------------------
    

        evolutionButton.addActionListener(e -> {
        	MazeEvolution maze =
        	  new MazeEvolution();

        	maze.setVisible(true);
        });

        	


        controls.add(randomButton);
        controls.add(stepButton);
        controls.add(runButton);
        controls.add(clearButton);
        controls.add(evolutionButton);


        add(
            controls,
            BorderLayout.SOUTH
        );


        // =========================
        // INFORMATION PANEL
        // =========================

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


        populationLabel =
            new JLabel(
                "Population: 0"
            );

        information.add(
            generationLabel
        );


        information.add(
            Box.createHorizontalStrut(20)
        );


        information.add(
            populationLabel
        );


        information.add(
            Box.createHorizontalStrut(20)
        );

        add(
            information,
            BorderLayout.NORTH
        );


        // =========================
        // RULE INPUT
        // =========================

        ruleField =
            new JTextField(
                "B3/S23",
                8
            );


        JButton applyRuleButton =
            new JButton(
                "Apply Rule"
            );


        applyRuleButton.addActionListener(e -> {

            String ruleText =
                    ruleField.getText();

            if (!RuleValidator.isValid(ruleText)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid rule. Use B/S notation such as B3/S23.",
                        "Invalid Rule",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            try {

                rule = new CARule(ruleText);

                simulation.setRule(rule);

                generation = 0;

                updateDisplay();

            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Invalid Rule",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        information.add(
            new JLabel("Rule:")
        );


        information.add(
            ruleField
        );


        information.add(
            applyRuleButton
        );


        // =========================
        // TIMER
        // =========================

        timer =
            new Timer(
                100,
                e -> {

                    step();

                }
            );


        // =========================
        // WINDOW SETTINGS
        // =========================

        pack();

        setLocationRelativeTo(null);

        setResizable(false);

        updateDisplay();
    }


    // =========================
    // RANDOMIZE
    // =========================

    private void randomize() {

        generation = 0;

        grid.randomize();

        updateDisplay();
    }

    // =========================
    // CLEAR
    // =========================

    private void clear() {

        timer.stop();

        running = false;

        generation = 0;

        grid.clear();

        updateDisplay();
    }

    // =========================
    // NEXT GENERATION
    // =========================

    private void step() {

        simulation.step();

        generation++;

        updateDisplay();
    }

    // =========================
    // UPDATE GUI
    // =========================

    private void updateDisplay() {

        int population =
                grid.countPopulation();

        generationLabel.setText(
                "Generation: " + generation
        );

        populationLabel.setText(
                "Population: " + population
        );

        gridPanel.repaint();
    }

    // =========================
    // MAIN
    // =========================

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            CellularUniverse universe =
                new CellularUniverse();

            universe.setVisible(true);

        });
    }
}