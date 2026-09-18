# Project Statement

## Project Title

**Cellular Universe**

## 1. Problem Statement

Complex behavior can emerge from systems governed by very simple rules. Cellular automata provide a computational way to study this phenomenon by defining a collection of simple cells and repeatedly applying local transition rules.

However, observing these systems through mathematical definitions alone can make the relationship between local rules and global behavior difficult to visualize.

**Cellular Universe** addresses this problem by providing an interactive Java application in which users can construct an initial cellular state, configure transition rules, and observe the resulting evolution. The project also includes a separate evolutionary-search experiment that demonstrates how simple selection and mutation can improve randomly generated strategies toward a defined goal.

---

## 2. Project Scope

The project focuses on two computational experiments:

### A. Cellular Automaton Simulation

The user interacts with a two-dimensional grid of cells. Each cell is either alive or dead. The state of each cell in the next generation is determined by the state of its neighbors and a configurable B/S rule.

### B. Goal Evolution

The application generates random movement strategies for an agent inside a maze. Strategies are evaluated using their distance from a target, better strategies are selected, and mutations are introduced to produce subsequent generations.

---

## 3. Core Computational Concepts

### Cellular Automata

The cellular universe is modeled as:

\[
U_t\in\{0,1\}^{R\times C}
\]

and evolves according to:

\[
U_{t+1}=F(U_t,R)
\]

where `R` represents the selected transition rule.

### Evolutionary Search

Candidate solutions are represented as movement sequences. Their quality is measured using Manhattan distance:

\[
d=|r-r_G|+|c-c_G|
\]

The evolutionary process is:

\[
\text{Population}
\rightarrow
\text{Evaluation}
\rightarrow
\text{Selection}
\rightarrow
\text{Mutation}
\rightarrow
\text{New Population}
\]

---

## 4. Expected Outcome

The expected outcome is an executable Java application that allows users to experiment with rule-based cellular systems and observe a simple evolutionary search process.

The project demonstrates that useful and visually complex behavior can be produced using relatively small computational rules and algorithms.
