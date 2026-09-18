# Cellular Universe

A Java-based computational laboratory for exploring **cellular automata, emergent behavior, configurable transition rules, and simple evolutionary search**.

The project contains two interactive experiments:

1. **Cellular Universe**: a grid-based cellular automaton whose behavior is controlled by B/S rules such as `B3/S23`.
2. **Goal Evolution**: a simple evolutionary-search experiment in which randomly generated movement strategies are selected and mutated to approach a goal inside a maze.

The project uses Java Swing for visualization and standard Java data structures and algorithms for the computational logic.

---

## 1. Project Objectives

The project aims to:

- Demonstrate how simple local rules can produce complex global behavior.
- Allow users to experiment with different cellular automaton rules.
- Visualize the evolution of a discrete system generation by generation.
- Demonstrate selection and mutation through a simple goal-directed evolutionary process.
- Apply Java programming concepts to a computation and visualization problem.

---

## 2. Main Features

### Cellular Automaton Simulator

- Interactive grid.
- Click cells to create or remove live cells.
- Randomize the initial universe.
- Clear the universe.
- Advance the simulation one generation at a time.
- Run and pause continuous simulation.
- Display generation and population.
- Configure the cellular automaton using B/S notation.
- Validate invalid rule input.

### Goal Evolution Experiment

- Maze containing a start and goal.
- Population of randomly generated movement strategies.
- Fitness evaluation based on distance from the goal.
- Selection of better candidates.
- Mutation of candidate movement sequences.
- Generation-by-generation evolution.
- Visualization of the best current candidate.

---

## 3. Technologies Used

- **Java**
- **Java Swing / AWT** for GUI and visualization
- Java arrays and collections
- Java `Comparator` for candidate sorting
- Java `Random` for stochastic initialization and mutation
- Eclipse IDE for development
- Git and GitHub for version control

No external libraries or third-party dependencies are required.

---

## 4. Requirements

Install:

- **JDK 17 or later**
- Git (recommended for cloning the repository)

---

## 5. Project Structure
```
CellularUniverse/
├── src/
│   └── cellularuniverse/
│       ├── CellularUniverse.java
│       ├── CARule.java
│       └── MazeEvolution.java
├── README.md
└── statement.md
```
---

# 6. Running the Project from the Command Line

### Step 1: Compile

From the project root:

```bash
mkdir out
javac -d out src/cellularuniverse/*.java
```

### Step 2: Run

```bash
java -cp out cellularuniverse.CellularUniverse
```

The Cellular Universe window should open.

From the Cellular Universe interface, use the **Goal Evolution** button to open the evolutionary-search experiment.
