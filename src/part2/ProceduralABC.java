package part2;

import mains.Grid;
import mains.Puzzle;

import java.util.Arrays;

/**
 * For each of the following techniques, implement a method which takes a partially filled grid and returns a grid in
 * which the rule has been applied to every square in the grid.
 */
public class ProceduralABC {

	public ProceduralABC() {}

	/**
	 * Takes a partially filled grid and, if any given square is the only unfilled square in a row and one letter
	 * does not appear in that line, returns a grid with the letter placed in that square.
	 */
	public static Grid onlyPlaceForLetterCol(Puzzle puzzle, Grid grid) {
		Grid gridCopy = new Grid(grid);

		for (int i = 0; i < gridCopy.chars.length; i++) {
			StringBuilder col = new StringBuilder();
			for (int j = 0; j < gridCopy.chars.length; j++) {
				col.append(gridCopy.chars[j][i]);
			}

			// If removing unfilled char reduces length of the string by exactly one, then it contains exactly one
			if (col.toString().length() -
					col.toString().replace(String.valueOf(Puzzle.unfilledChar), "").length() == 1) {
				for (char letter : puzzle.letters) {
					if (!col.toString().contains(String.valueOf(letter))) {
						int idx = col.toString().indexOf(Puzzle.unfilledChar);
						gridCopy.chars[idx][i] = letter;
					}
				}
			}
		}

		return gridCopy;
	}

	/**
	 * Takes a partially filled grid and, if any given square is the only unfilled square in a column and one letter
	 * does not appear in that line, returns a grid with the letter placed in that square.
	 */
	public static Grid onlyPlaceForLetterRow(Puzzle puzzle, Grid grid) {
		Grid gridCopy = new Grid(grid);

		for (int i = 0; i < gridCopy.chars.length; i++) {
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < gridCopy.chars.length; j++) {
				row.append(gridCopy.chars[i][j]);
			}

			// If removing unfilled char reduces length of the string by exactly one, then it contains exactly one
			if (row.toString().length() -
					row.toString().replace(String.valueOf(Puzzle.unfilledChar), "").length() == 1) {
				for (char letter : puzzle.letters) {
					if (!row.toString().contains(String.valueOf(letter))) {
						int idx = row.toString().indexOf(Puzzle.unfilledChar);
						gridCopy.chars[i][idx] = letter;
					}
				}
			}
		}

		return gridCopy;
	}

	/**
	 * Takes a partially filled grid and, if every unique letter appears in a column, returns a grid with blanks placed
	 * in the remaining squares in that column.
	 */
	public static Grid fillInBlanksCol(Puzzle puzzle, Grid grid) {
		Grid gridCopy = new Grid(grid);

		for (int i = 0; i < gridCopy.chars.length; i++) {
			StringBuilder col = new StringBuilder();
			for (int j = 0; j < gridCopy.chars.length; j++) {
				col.append(gridCopy.chars[j][i]);
			}

			boolean containsEachLetter = true;
			for (char letter : puzzle.letters) {
				if (!col.toString().contains(String.valueOf(letter))) {
					containsEachLetter = false;
					break;
				}
			}

			// If contains each unique letter, then place blanks in unfilled squares
			if (containsEachLetter) {
				int idx = 0;
				for (char c : col.toString().toCharArray()) {
					if (c == Puzzle.unfilledChar) {
						gridCopy.chars[idx][i] = Puzzle.blankSymbol;
					}
					idx++;
				}
			}
		}

		return gridCopy;
	}

	/**
	 * Takes a partially filled grid and, if every unique letter appears in a row, returns a grid with blanks placed in
	 * the remaining squares in that column.
	 */
	public static Grid fillInBlanksRow(Puzzle puzzle, Grid grid) {
		Grid gridCopy = new Grid(grid);

		for (int i = 0; i < gridCopy.chars.length; i++) {
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < gridCopy.chars.length; j++) {
				row.append(gridCopy.chars[i][j]);
			}

			boolean containsEachLetter = true;
			for (char letter : puzzle.letters) {
				if (!row.toString().contains(String.valueOf(letter))) {
					containsEachLetter = false;
					break;
				}
			}

			// If contains each unique letter, then place blanks in unfilled squares
			if (containsEachLetter) {
				int idx = 0;
				for (char c : row.toString().toCharArray()) {
					if (c == Puzzle.unfilledChar) {
						gridCopy.chars[i][idx] = Puzzle.blankSymbol;
					}
					idx++;
				}
			}
		}

		return gridCopy;
	}

	/**
	 * Takes a partially filled grid and, if any corner has different clues in the row and column adjacent to it,
	 * returns that grid with a blank placed in that square.
	 */
	public static Grid differentCorners(Puzzle puzzle, Grid grid) {
		Grid gridCopy = new Grid(grid);

		// Top left corner
		if (neitherCornerIsUnfilledChar(puzzle.top[0], puzzle.left[0])
				&& puzzle.top[0] != puzzle.left[0]) {
			gridCopy.chars[0][0] = Puzzle.blankSymbol;
		}

		// Top right corner
		if (neitherCornerIsUnfilledChar(puzzle.top[gridCopy.chars.length-1], puzzle.right[0])
				&& puzzle.top[gridCopy.chars.length-1] != puzzle.right[0]) {
			gridCopy.chars[0][gridCopy.chars.length-1] = Puzzle.blankSymbol;
		}

		// Bottom left corner
		if (neitherCornerIsUnfilledChar(puzzle.bottom[0], puzzle.left[gridCopy.chars.length-1])
				&& puzzle.bottom[0] != puzzle.left[gridCopy.chars.length-1]) {
			gridCopy.chars[gridCopy.chars.length-1][0] = Puzzle.blankSymbol;
		}

		// Bottom right corner
		if (neitherCornerIsUnfilledChar(puzzle.bottom[gridCopy.chars.length-1], puzzle.right[gridCopy.chars.length-1])
				&& puzzle.bottom[gridCopy.chars.length-1] != puzzle.right[gridCopy.chars.length-1]) {
			gridCopy.chars[gridCopy.chars.length-1][gridCopy.chars.length-1] = Puzzle.blankSymbol;
		}

		return gridCopy;
	}

	/**
	 * Support method for differentCorners to account for the case when a corner no a valid clue in one row or column
	 * adjacent to it and no clue, i.e. unfilled chars, in the other row or column adjacent to it. While this,
	 * technically speaking, constitutes two different string literals, it does not constitute two different clues. See
	 * also test case in {@link tests.ProceduralABCTestsStudent}.
	 */
	public static boolean neitherCornerIsUnfilledChar(char a, char b) {
		return a != Puzzle.unfilledChar && b != Puzzle.unfilledChar;
	}

	/**
	 * Takes a partially filled grid and, if every clue is given on one side and there is only one place for one clue
	 * for any letter, places the letter in the first square in that row or column.
	 */
	public static Grid commonClues(Puzzle puzzle, Grid grid) {
		Grid gridCopy = new Grid(grid);

		// Top
		String topClues = parsePuzzleInput(puzzle.top);
		if (!topClues.contains(String.valueOf(Puzzle.unfilledChar))) {
			for (char letter : puzzle.top) {
				int occurrence = 0;
				for (char otherLetter : puzzle.top) {
					if (letter == otherLetter) {
						occurrence++;
					}
				}
				if (occurrence == 1) {
					gridCopy.chars[0][topClues.indexOf(letter)] = letter;
				}
			}
		}

		// Bottom
		String bottomClues = parsePuzzleInput(puzzle.bottom);
		if (!bottomClues.contains(String.valueOf(Puzzle.unfilledChar))) {
			for (char letter : puzzle.bottom) {
				int occurrence = 0;
				for (char otherLetter : puzzle.bottom) {
					if (letter == otherLetter) {
						occurrence++;
					}
				}
				if (occurrence == 1) {
					gridCopy.chars[gridCopy.chars.length-1][bottomClues.indexOf(letter)] = letter;
				}
			}
		}

		// Left
		String leftClues = parsePuzzleInput(puzzle.left);
		if (!leftClues.contains(String.valueOf(Puzzle.unfilledChar))) {
			for (char letter : puzzle.left) {
				int occurrence = 0;
				for (char otherLetter : puzzle.left) {
					if (letter == otherLetter) {
						occurrence++;
					}
				}
				if (occurrence == 1) {
					gridCopy.chars[leftClues.indexOf(letter)][0] = letter;
				}
			}
		}

		// Right
		String rightClues = parsePuzzleInput(puzzle.right);
		if (!rightClues.contains(String.valueOf(Puzzle.unfilledChar))) {
			for (char letter : puzzle.right) {
				int occurrence = 0;
				for (char otherLetter : puzzle.right) {
					if (letter == otherLetter) {
						occurrence++;
					}
				}
				if (occurrence == 1) {
					gridCopy.chars[rightClues.indexOf(letter)][gridCopy.chars.length-1] = letter;
				}
			}
		}

		return gridCopy;
	}

	/**
	 * Support method for the methods in {@link ProceduralABC}, which takes a char[] and returns a string of its
	 * elements, trimmed of any whitespace and punctuation.
	 */
	public static String parsePuzzleInput(char[] input) {
		return Arrays.toString(input)
				.replace("[", "")
				.replace("]", "")
				.replace(", ", "");
	}
}
