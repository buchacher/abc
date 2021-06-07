package part1;

import mains.Grid;
import mains.Puzzle;

public class CheckerABC {

	public CheckerABC() {}

	public static boolean isConsistent(Puzzle puzzle, Grid grid) {
		return isConsistent(puzzle, grid.chars);
	}

	/**
	 * Returns true if all the constraints of the puzzle are obeyed for every square except the constraint that every
	 * square should be filled in.
	 */
	public static boolean isConsistent(Puzzle puzzle, char[][] chars) {
		/* The delta between puzzle size and number of letters (unique, excl. blank), which determines the number of
		 admissible blanks per row or column. */
		int delta = puzzle.size() - puzzle.letters.length;

		// 1) Each unique letter must appear exactly once in each row, col

		// 1a) Rows:
		for (char[] aChar : chars) {
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < chars.length; j++) {
				row.append(aChar[j]);
			}
			for (char letter : row.toString().toCharArray()) {
				if (letter != Puzzle.blankSymbol) {
					// If removing a letter reduces the length of a string by >1, then it is contained more than once
					if (row.toString().length() -
							row.toString().replace(String.valueOf(letter), "").length() > 1) {
						return false;
					}
				}
			}
		}

		// 1b) Cols:
		for (int i = 0; i < chars.length; i++) {
			StringBuilder col = new StringBuilder();
			for (char[] aChar : chars) {
				col.append(aChar[i]);
			}
			for (char letter : col.toString().toCharArray()) {
				if (letter != Puzzle.blankSymbol) {
					// If removing a letter reduces the length of a string by >1, then it is contained more than once
					if (col.toString().length() -
							col.toString().replace(String.valueOf(letter), "").length() > 1) {
						return false;
					}
				}
			}
		}

		// 2) First non-blank cell in row, col must contain the letter given as clue

		// 2a) Rows:
		for (int i = 0; i < chars.length; i++) {
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < chars.length; j++) {
				row.append(chars[i][j]);
			}
			if (!(puzzle.left[i] == Puzzle.unfilledChar)) {
				if (row.toString().indexOf(puzzle.left[i]) > delta) {
					return false;
				}
			}
			if (!(puzzle.right[i] == Puzzle.unfilledChar)) {
				if (row.toString().indexOf(puzzle.right[i]) < chars.length - delta - 1) {
					return false;
				}
			}
		}

		// 2b) Cols:
		for (int i = 0; i < chars.length; i++) {
			StringBuilder col = new StringBuilder();
			for (char[] aChar : chars) {
				col.append(aChar[i]);
			}
			if (!(puzzle.top[i] == Puzzle.unfilledChar)) {
				if (col.toString().indexOf(puzzle.top[i]) > delta) {
					return false;
				}
			}
			if (!(puzzle.bottom[i] == Puzzle.unfilledChar)) {
				if (col.toString().indexOf(puzzle.bottom[i]) < chars.length - delta - 1) {
					return false;
				}
			}
		}

		// 3) The number of blanks in each row, col is equal to delta puzzle size and number of unique letters

		// 3a) Rows:
		for (char[] aChar : chars) {
			int rowBlanks = 0;
			for (int j = 0; j < chars.length; j++) {
				if (rowBlanks > delta) {
					return false;
				} else if (aChar[j] == Puzzle.blankSymbol) {
					rowBlanks++;
				}
			}
		}

		// 3b) Cols:
		for (int i = 0; i < chars.length; i++) {
			int colBlanks = 0;
			for (char[] aChar : chars) {
				if (colBlanks > delta) {
					return false;
				} else if (aChar[i] == Puzzle.blankSymbol) {
					colBlanks++;
				}
			}
		}

		return true;
	}
          
	public static boolean isFullGrid(Puzzle puzzle, Grid grid) {
		return isFullGrid(puzzle, grid.chars);
	}

	/**
	 * Returns true if a given grid is completely assigned with valid letters or blanks from the puzzle, i.e. with no
	 * cells not yet filled in.
	 */
	public static boolean isFullGrid(Puzzle puzzle, char[][] chars ) {
		// n x n grids, so chars.length will hold for both number of rows and number of columns in 2D array
		for (char[] aChar : chars) {
			for (int j = 0; j < chars.length; j++) {
				if (!puzzle.validLetter(aChar[j])) {
					if (aChar[j] != Puzzle.blankSymbol) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean isSolution(Puzzle puzzle, Grid grid) {
		return isFullGrid(puzzle,grid) && isConsistent(puzzle,grid);
	}
}

