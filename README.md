SudokuChecker
=============

Use this class to check if a proposed Sudoku solution is valid!

To Build
========

From the root dir just run `ant`. I put a build.xml in there.
The compiled jar file ends up in dist/SudokuChecker.jar

To Run
======

Execute from a linux/mac/shell command line using the bash script:
bin/sudokuchecker <filename.csv>

For example:
bin/sudokuchecker data/sampleInput\ 4x4.txt

Which is just a wrapper for this command:
java -cp dist/SudokuChecker.jar com.mjf.sudoku.Main <filename>

Yes, this is a simple program and I did not bother to make the jar executable or put in the META-INF info :)

Test files with Sudoku solutions (or non-solutions) are in data/