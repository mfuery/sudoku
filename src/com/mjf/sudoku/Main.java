/**
 * License blah blah. 
 * You can do whatever you want with this source code! :D
 * 
 * @author mfuery@gmail.com
 */
package com.mjf.sudoku;

import java.io.IOException;

/**
 * Entry point for the sudoku solution checker app.
 * It takes the csv filename as the only parameter.
 * 
 * Some notes:
 * 
 * This is a super simple program, so we just echo to stdout. In a "real" app,
 * we'd implement some sort of logger, like log4j.
 * 
 * The language could definitely be abstracted into static (final) vars in a language class
 * if we had plans for internationalization. There are libraries to help with that.
 * 
 * @author mfuery@gmail.com
 */
public class Main {

    /**
     * 
     * @param args Filename of csv puzzle
     */
    public static void main(String[] args) {
        // Handle CLI usage
        if (args.length != 1) {
            System.out.println("Usage: ./sudokuchecker <filename>");
            System.exit(0);
        }
        
        final String filename = args[0].trim();

        SudokuChecker checker = new SudokuChecker();
        try {
            checker.parseFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        final int width = checker.getWidth();
        System.out.println("Puzzle dimensions detected as " + width + "x" + width);
        
        // Check rows, columns for digit uniqueness
        try {
            if (checker.isValidSolution()) {
                System.out.println("You've got a valid solution there, mate.");
            } else {
                System.out.println("Not a solution - try again, buddy.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
