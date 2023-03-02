# Recursive-Maze-Solver
This is a Java program that uses recursion and backtracking to find a path through a maze.

This project was built with the help of the book [Data Structures: Abstraction and Design Using Java](https://www.wiley.com/en-us/Data+Structures%3A+Abstraction+and+Design+Using+Java%2C+4th+Edition-p-9781119703594) by Elliot B. Koffman and Paul A. T. Wolfgang.

## Contents
- [Description](#Description)
  - [Classes and Interfaces](#classes-and-interfaces)
- [Dependencies](#Dependencies)
- [Running the Code](#Running-the-Code)
- [Challenges](#Challenges)

## Description
#### Classes and Interfaces
- `TwoDimGrid`: Implements the two-dimensional grid.
- `GridColors`: Assigns names to the various colors that a cell can have.
- `Maze`: Implements the search algorithm using backtracking.
- `MazeTest`: Provides a graphical interface to test the algorithm.

The Maze is represented by a two-dimensional grid of colored cells where the exit point is the cell `(getNCols()-1,getNRows()-1)`. The class `TwoDimGrid` implements the two-dimensional grid, and the `Maze` class implements the search algorithm.

The `Maze` class takes a `TwoDimGrid` object as a parameter in its constructor, which represents the maze. The `TwoDimGrid` class provides a 2D grid data structure for storing the colors of cells in the maze. The colors are represented as integers, and constants for the colors are defined in the `GridColors` interface.

The `Maze` class has three methods for solving the maze problem:

1. `findMazePath`: This is a recursive method that attempts to find a path through the maze starting from the given coordinates (x, y). It returns true if a path is found and false otherwise. The method uses backtracking to explore all possible paths until a path to the exit is found. When a cell is explored, it is colored green to indicate that it is on the path. If the current cell is a dead end, it is colored black and backtracked.
2. `findAllMazePaths`: This method uses a stack-based implementation to find all possible paths through the maze. It returns an `ArrayList` of `ArayList` of `PairInt`, where each `ArrayList` represents a path through the maze. The method uses a stack to keep track of the coordinates visited, and it adds the coordinates to the result list when it reaches the exit.
3. `findMazePathMin`: This method finds the shortest path through the maze. It calls the `findAllMazePaths` method to get all possible paths through the maze, and then it returns the shortest path by comparing the lengths of the paths in the result list.

The `Maze` class also has two methods for resetting the colors of cells in the maze:

1. `resetTemp`: This method sets all cells colored `TEMPORARY` to `BACKGROUND`.
2. `restore`: This method sets all cells colored `PATH` or `NON_BACKGROUND` to `BACKGROUND`.

## Dependencies
This project requires the following dependencies:
- [Java SDK 8 or higher](https://www.oracle.com/java/technologies/downloads/)

## Running the Code
1. Clone the repository: `git clone https://github.com/Alaqian/Maze-Solver`
2. Navigate to the root directory: `cd Maze-Solver`
3. Compile the source code: `javac MazeTest.java`
4. Run the application: `java MazeTest`

The `MazeTest` class provides a graphical interface to test the algorithm, allowing the user to select the size of the grid and mark the non-background colored cells as potentially part of a path. Once the selection is made, the "solve" button will initiate the algorithm, and the resulting path will be shown in green while the visited and unvisited cells are marked in black and red, respectively.

The algorithm's `findMazePath(int x, int y)` method returns `true` if a path is found, and `false` otherwise. The algorithm starts at the cell (0, 0), and if it falls outside the grid or if it is not a non-background colored cell, it returns `false`. If it reaches the exit cell, it sets the color of the cell to `PATH` and returns `true`. Otherwise, it sets the current cell's color to `PATH`, explores each of the four neighboring cells recursively, and returns true if any of the four calls returns `true`.

The `GridColors` interface assigns the following colors to each type of cell:

- `PATH`: Green
- `BACKGROUND`: White
- `NON_BACKGROUND`: Red
- `TEMPORARY`: Black
To use this program, simply run the `MazeTest` class and follow the on-screen instructions.

## Challenges
One of the challenges faced during the implementation of this program was finding a way to keep track of the path that had already been visited during backtracking. This was solved by using a stack to keep track of the coordinates of the cells visited.

Another challenge was finding a way to implement the third method, `findMazePathMin`, efficiently. This was solved by finding all possible paths and returning the one with the smallest number of cells.
