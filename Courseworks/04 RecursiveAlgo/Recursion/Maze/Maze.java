/* Name-Lastname: Thanapoom Phatthanaphan
 * CWID: 20011296
 * Section: CS 570-PA
 * Subject: Homework 4
 */

package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
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
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    /* Problem 1
     * Implement a recursive algorithm that returns true if a path is found.
     */
    public boolean findMazePath(int x, int y) {
    	
    	/* If the current cell being analyzed falls outside the grid
    	 * or does not have NON_BACKFROUND, then false is returned.
    	 */
    	if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()
    			|| (maze.getColor(x, y) != NON_BACKGROUND)) {
    		
    		return false;
    		
    	}
    	
    	/* If the current cell being analyzed is the exit cell, then
    	 * the cell must be painted color PATH and true is returned.
    	 */
    	else if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) {
    		
    		maze.recolor(x, y, PATH);
    		return true;
    		
		}
    	
    	/* Otherwise, the current cell is assumed to be part of the path and painted color PATH.
    	 * Explore the four neighboring cells too see if they are part of a path. If the result
    	 * of exploring at least one of the neighbors is successful, then a path has been found.
    	 * Otherwise, the cell is not part of a path, then it will be marked as visited and
    	 * painted color TEMPORARY.
    	 */
    	maze.recolor(x, y, PATH);
		if (this.findMazePath(x + 1, y) || this.findMazePath(x - 1, y) ||
				this.findMazePath(x, y + 1) || this.findMazePath(x, y - 1)) {
			
			return true;
			
		} else {
			
			maze.recolor(x, y, TEMPORARY);
			return false;
			
		}	
	}
    
    /* Problem 2
     * Implement a recursive algorithm by modifying the solution of Problem 1 so that
     * a list of all the solutions to the maze is returned.
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	
    	/* If the current cell being analyzed falls outside the grid
    	 * or does not have NON_BACKFROUND, then stop the method
    	 */
    	if ((x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()
    			|| maze.getColor(x,y) != NON_BACKGROUND)) {
    		
    		return;
    		
    	}
    	
    	/* If the current cell being analyzed is the exit cell, then
    	 * push the current cell to stack trace and also add it to result.
    	 * After recolor the exit points to NON_BACKGROUND
    	 */
    	else if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) {
        	
    		trace.push(new PairInt(x, y));
    		result.add(new ArrayList<PairInt>(trace));
    		maze.recolor(x, y, NON_BACKGROUND);
    	
    	}
    	
    	/* Otherwise, push the current cell to stack trace and mark the current cell
    	 * as visited by setting the color to PATH, then continue to find a path by
    	 * calling function recursively on neighboring cells. After returning from
         * visiting neighbors, recolor the cell back to NON_BACKGROUND for visiting
         * again after backtracking.
         */
    	else {
        	
    		trace.push(new PairInt(x,y));
        	maze.recolor(x, y, PATH);
        	int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        	for (int[] dir : directions) {
        		
                int next_x = x + dir[0];
                int next_y = y + dir[1];
                findMazePathStackBased(next_x, next_y, result, (Stack<PairInt>) trace.clone());
                
            }
        	
    		maze.recolor(x, y, NON_BACKGROUND);
    		
    	}
    }
    
    // function to find all paths of the maze using Stack
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
      	 
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
      	Stack<PairInt> trace = new Stack<>();
      	findMazePathStackBased(0, 0, result, trace);   	 
      	return result;
      	 
    }
    
    /* Problem 3
     * Adapt boolean Maze.findMazePath() so that it returns the shortest path
     * in the list of paths.
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	
    	ArrayList<ArrayList<PairInt>> result = findAllMazePaths(x, y);
    	int min = result.get(0).size();
    	for (int i = 1; i < result.size(); i++) {
    		if (result.get(i).size() < min) {
    			
    			min = result.get(i).size();
    			
    		}
    	}
    	
    	ArrayList<PairInt> minimum_path = new ArrayList<PairInt>();
    	for (int i = 0; i < result.size(); i++) {
    		
    		if (result.get(i).size() == min) {
    			minimum_path.addAll(result.get(i));
    			break;
    			
    		}
    	}
    	
    	return minimum_path;
    	
    }	

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
    	
        maze.recolor(TEMPORARY, BACKGROUND);
        
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
    	
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
        
    }
    /*</exercise>*/
}
/*</listing>*/