package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 *
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in NON_BACKGROUND (red) color;
     *      barrier cells are in BACKGROUND (white) color.
     * @post If a path is found, all cells on it are set to the
     *       PATH (green) color; all cells that were visited but are
     *       not on the path are in the TEMPORARY (black) color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
	public boolean findMazePath(int x, int y) {
		if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows())
			return false; // Cell is out of bounds.
		else if (!maze.getColor(x, y).equals(NON_BACKGROUND))
			return false; // Cell is on barrier or dead end.
		else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x, y, PATH); // Cell is on path
			return true; // and is maze exit.
		} else { // Recursive case.
			// Attempt to find a path from each neighbor.
			// Tentatively mark cell as on path.
			maze.recolor(x, y, PATH);
			if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
				return true;
			} else {
				maze.recolor(x, y, TEMPORARY); // Dead end.
				return false;
			}
		}
	}

	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, result, trace);
		return result;
	}

	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		trace.push(new PairInt(x, y)); // Push current coordinates on the stack
		if (!(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows())) // Cell is not out of bounds.
			if (maze.getColor(x, y).equals(NON_BACKGROUND)) // Cell is NON_BACKGROUND (red) color for possible path
				if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) { // true if cell is exit
					// Copy the contents of the stack to result
					ArrayList<PairInt> temp = new ArrayList<>();
					temp.addAll(trace);
					result.add(temp);
				} else { // Recursive case
					maze.recolor(x, y, PATH); //  Mark current cell as path to avoid visiting it while visiting alternative paths
					findMazePathStackBased(x - 1, y, result, trace);
					findMazePathStackBased(x + 1, y, result, trace);
					findMazePathStackBased(x, y - 1, result, trace);
					findMazePathStackBased(x, y + 1, result, trace);
					maze.recolor(x, y, NON_BACKGROUND); // Unmark current cell so it can be visited again
				} 
		trace.pop(); // Pop current coordinates off the stack
	}

	public ArrayList<PairInt> findMazePathMin(int x, int y) {
		ArrayList<ArrayList<PairInt>> solutions = findAllMazePaths(x, y);
		int minIndex = 0;
		if (solutions.isEmpty())
			return new ArrayList<PairInt>();
		else
			for (int i = 1; i < solutions.size(); i++)
				if (solutions.get(i).size() < solutions.get(minIndex).size())
					minIndex = i;
		return solutions.get(minIndex);
	}

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
}
