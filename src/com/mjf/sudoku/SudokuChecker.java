/**
 * License blah blah. 
 * You can do whatever you want with this source code! :D
 * 
 */
package com.mjf.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author mfuery@gmail.com
 */
public class SudokuChecker {
    
    private int width = 0;
    private int[][] solution;
    private int[][] transpose;

    public int getWidth() {
        return width;
    }
    
    /**
     * Not used (yet)... will accept a matrix already loaded in memory. This demonstrates
     * that this class is modular and could be dropped into, say, a Sudoku game app.
     * 
     * @param matrix And MxM 2D int array of the proposed solution
     */
    public void setSolution(int[][] matrix) {
        width = matrix.length;
        solution = new int[width][width];
        makeTranspose(solution);
    }

    /**
     * Parse the csv into an array, do a couple checks that the file is formatted correctly,
     * and set some member variables used by {@link #isValidSolution() isValidSolution}.
     * 
     * @note We would want to put in a limit to the size of the puzzle so we don't crash the JVM
     *       if someone decides to throw millions of rows at it. But for this simple example,
     *       we'll just assume the user is giving us valid input, since the 2 example inputs
     *       look good.
     * 
     * @param filename csv file
     * @throws IOException
     */
    public void parseFile(String filename) throws IOException {
        String line;
        String[] cells;

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        
        int currentRow = 0, 
            i = 0;
        boolean isInitialized = false;
        
        while ((line = br.readLine()) != null) {
            cells = line.split(",");

            if (!isInitialized) {
                // The other way we could do this is read the first line in before this while loop
                // and avoid this boolean check each line, but performance increase would be
                // negligible so I'm fine with this.
                width = cells.length;
                solution = new int[width][width];
                isInitialized = true;
            }
            if (currentRow >= width) {
                throw new IOException("This file has too many rows! (or not enough columns)");
            }
            
            for (i = 0; i < cells.length; i++) {
                solution[currentRow][i] = Integer.parseInt(cells[i]);
            }
            
            currentRow++;
        }

        br.close();
        fr.close();

        
        // todo: Check that all rows are the same length and width == height        

        makeTranspose(solution);
    }

    /**
     * 
     * @return boolean
     * @throws Exception if you did not parse file first
     */
    public boolean isValidSolution() throws Exception {
        if (width == 0) {
            throw new Exception("You must first load the csv by calling parseFile(String)!");
        }
        return (isMatrixValid(solution) && isMatrixValid(transpose));
    }
    
    /**
     * This problem is really pretty easy: all we have to do is validate that each row and each
     * column has exactly one digit. If we find a duplicate digit then we know the solution
     * is invalid.
     * 
     * @return boolean
     */
    private boolean isMatrixValid(int[][] mat) {
        int[] temp = new int[width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                temp[j] = mat[i][j];                
                for (int w = 0; w < j; w++) {
                    if (temp[w] == mat[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Makes a transpose of the given 2D matrix
     * 
     * @param mat
     */
    private void makeTranspose(int[][] mat) {
        // Create transpose of matrix. Makes it easier to check for a valid solution, later.
        transpose = new int[width][width]; 
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                transpose[j][i] = solution[i][j];
            }
        }        
    }

}
