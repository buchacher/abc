package tests;

import mains.Grid;
import mains.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part1.CheckerABC;
import part2.ProceduralABC;

/**
 * Test class for unit tests for {@link ProceduralABC}.
 */
public class ProceduralABCTestsStudent {

    Puzzle puzzle;
    Grid fullConsistentGrid;
    Grid gridWithTwoUnfilledCorners;
    Grid gridWithUnfilledLettersInDiagonal;
    Grid gridRemainingMustBeBlanksRow;
    Grid gridRemainingMustBeBlanksCol;
    Puzzle puzzleTwo;
    Grid gridPuzzleTwoRows;
    Grid puzzleTwoRowsSolution;
    Grid gridPuzzleTwoCols;
    Grid puzzleTwoColsSolution;
    Puzzle puzzleThree;
    Puzzle puzzleFour;
    Grid gridCommonClues;
    Grid commonOneSolution;
    Grid commonSixSolution;
    Puzzle simpleCommonCluesTop;
    Grid gridSimpleCommonCluesTop;
    Grid simpleCommonClueTopSolution;

    // Custom setup for extended corner test case
    Puzzle extCornerPuzzle;
    Grid gridExtendedCorner;


    @BeforeEach
    public void setup() {
        // ABC6
        puzzle = new Puzzle(
                "abcd",	// letters
                "acbbda",	// clues top
                "cbadab",	// clues bottom
                "abacdc",	// clues left
                "dacdba"  // clues right
        );

        // ABC6Sol
        fullConsistentGrid = new Grid(new String[]{
                "acbxdx",
                "bdxxca",
                "xadbxc",
                "xxcabd",
                "dxacxb",
                "cbxdax"
        });

        gridWithTwoUnfilledCorners = new Grid(new String[]{
                "acbxd_",
                "bdxxca",
                "xadbxc",
                "xxcabd",
                "dxacxb",
                "cbxda_"
        });

        gridWithUnfilledLettersInDiagonal = new Grid(new String[]{
                "_cbxdx",
                "b_xxca",
                "xa_bxc",
                "xxc_bd",
                "dxacxb",
                "cbxdax"
        });

        gridRemainingMustBeBlanksRow = new Grid(new String[]{
                "acbxdx",
                "bdxxca",
                "xadbxc",
                "__cabd",
                "d_ac_b",
                "cbxdax"
        });

        gridRemainingMustBeBlanksCol = new Grid(new String[]{
                "acbxdx",
                "bd_xca",
                "_adbxc",
                "_xcabd",
                "dxacxb",
                "cb_dax"
        });

        // ABC1
        puzzleTwo = new Puzzle(
                "abcd",	// letters
                "ddccba",	// clues top
                "acdbcd",	// clues bottom
                "dbbcaa",	// clues left
                "aacabd"  // clues right
        );

        // ABC1testrows
        gridPuzzleTwoRows = new Grid(new String[] {
                "_xxcba",
                "_dcaxx",
                "_baxdc",
                "_xbdax",
                "_adxcb",
                "_cxbxd"
        });

        // ABC1testblanksrowsrows
        puzzleTwoRowsSolution = new Grid(new String[] {
                "_xxcba",
                "_dcaxx",
                "xbaxdc",
                "_xbdax",
                "xadxcb",
                "_cxbxd"
        });

        // ABC1testcols
        gridPuzzleTwoCols = new Grid(new String[] {
                "dxxcba",
                "bdcaxx",
                "xbaxdc",
                "cxbdax",
                "xadxcb",
                "______"
        });

        // ABC1testblankscolscols
        puzzleTwoColsSolution = new Grid(new String[] {
                "dxxcba",
                "bdcaxx",
                "xbaxdc",
                "cxbdax",
                "xadxcb",
                "__x_x_"
        });

        // ABC1
        puzzleThree = new Puzzle(
                "abcd",	// letters
                "ddccba",	// clues top
                "acdbcd",	// clues bottom
                "dbbcaa",	// clues left
                "aacabd"  // clues right
        );


        // ABC6
        puzzleFour = new Puzzle(
                "abcd",	// letters
                "acbbda",	// clues top
                "cbadab",	// clues bottom
                "abacdc",	// clues left
                "dacdba"  // clues right
        );

        // ABC6Empty
        gridCommonClues = new Grid(new String[] {
                "______",
                "______",
                "______",
                "______",
                "______",
                "______"
        });

        // ABCcommonclues1
        commonOneSolution = new Grid(new String[] {
                "d___ba",
                "______",
                "_____c",
                "c_____",
                "_____b",
                "a__b_d"
        });

        // ABCcommonclues6
        commonSixSolution = new Grid(new String[] {
                "_c__d_",
                "b_____",
                "_____c",
                "______",
                "d____b",
                "c__d__"
        });

        simpleCommonCluesTop = new Puzzle(
                "abcd",	// letters
                "acbbda",	// clues top
                "______",	// clues bottom
                "______",	// clues left
                "______"  // clues right
        );

        gridSimpleCommonCluesTop = new Grid(new String[] {
                "______",
                "______",
                "______",
                "______",
                "______",
                "______"
        });

        simpleCommonClueTopSolution = new Grid(new String[] {
                "_c__d_",
                "______",
                "______",
                "______",
                "______",
                "______"
        });


        // --------------------------------------------------
        // --- Custom setup for extended corner test case ---
        // --------------------------------------------------
        extCornerPuzzle = new Puzzle(
                "abcd",	// letters
                "_cbbd_",	// clues top
                "cbadab",	// clues bottom
                "abacd_",	// clues left
                "dacdb_"  // clues right
        );

        gridExtendedCorner = new Grid(new String[] {
                "______",
                "______",
                "______",
                "______",
                "______",
                "______"
        });
        // --------------------------------------------------
        // --------------------------------------------------
    }

    @Test
    public void shouldReturnGridWithCornersFilled() {
        Grid gridCopy = new Grid(gridWithTwoUnfilledCorners);
        gridCopy = ProceduralABC.differentCorners(puzzle,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzle.printPuzzleGrid(gridWithTwoUnfilledCorners);
        puzzle.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(fullConsistentGrid));
    }

    @Test
    public void shouldReturnGridWithSingleUnfilledSquaresInColsFilled() {
        Grid gridCopy = new Grid(gridWithUnfilledLettersInDiagonal);
        gridCopy = ProceduralABC.onlyPlaceForLetterCol(puzzle,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzle.printPuzzleGrid(gridWithUnfilledLettersInDiagonal);
        puzzle.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(fullConsistentGrid));
    }

    @Test
    public void shouldReturnGridWithSingleUnfilledSquaresInRowsFilled() {
        Grid gridCopy = new Grid(gridWithUnfilledLettersInDiagonal);
        gridCopy = ProceduralABC.onlyPlaceForLetterRow(puzzle,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzle.printPuzzleGrid(gridWithUnfilledLettersInDiagonal);
        puzzle.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(fullConsistentGrid));
    }

    @Test
    public void shouldReturnGridWithBlanksInCol() {
        Grid gridCopy = new Grid(gridRemainingMustBeBlanksCol);
        gridCopy = ProceduralABC.fillInBlanksCol(puzzle,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzle.printPuzzleGrid(gridRemainingMustBeBlanksCol);
        puzzle.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(fullConsistentGrid));
    }

    @Test
    public void shouldReturnGridWithBlanksInRow() {
        Grid gridCopy = new Grid(gridRemainingMustBeBlanksRow);
        gridCopy = ProceduralABC.fillInBlanksRow(puzzle,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzle.printPuzzleGrid(gridRemainingMustBeBlanksRow);
        puzzle.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(fullConsistentGrid));
    }

    @Test
    public void advancedShouldReturnGridWithBlanksInCol() {
        Grid gridCopy = new Grid(gridPuzzleTwoCols);
        gridCopy = ProceduralABC.fillInBlanksCol(puzzleTwo,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzleTwo.printPuzzleGrid(gridPuzzleTwoCols);
        puzzleTwo.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(puzzleTwoColsSolution));
    }

    @Test
    public void advancedShouldReturnGridWithBlanksInRow() {
        Grid gridCopy = new Grid(gridPuzzleTwoRows);
        gridCopy = ProceduralABC.fillInBlanksRow(puzzleTwo,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzleTwo.printPuzzleGrid(gridPuzzleTwoRows);
        puzzleTwo.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(puzzleTwoRowsSolution));
    }

    @Test
    public void shouldReturnCommonCluesOneSolution() {
        Grid gridCopy = new Grid(gridCommonClues);
        gridCopy = ProceduralABC.commonClues(puzzleThree,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzleThree.printPuzzleGrid(gridCommonClues);
        puzzleThree.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(commonOneSolution));
    }

    @Test
    public void shouldReturnCommonCluesSixSolution() {
        Grid gridCopy = new Grid(gridCommonClues);
        gridCopy = ProceduralABC.commonClues(puzzleFour,gridCopy);

        /*
        // Uncomment for print statements for comparison
        puzzleFour.printPuzzleGrid(gridCommonClues);
        puzzleFour.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(commonSixSolution));
    }

    @Test
    public void shouldReturnGridCommonCluesFilledAcrossTopRow() {
        Grid gridCopy = new Grid(gridSimpleCommonCluesTop);
        gridCopy = ProceduralABC.commonClues(simpleCommonCluesTop,gridCopy);

        /*
        // Uncomment for print statements for comparison
        simpleCommonCluesTop.printPuzzleGrid(gridSimpleCommonCluesTop);
        simpleCommonCluesTop.printPuzzleGrid(gridCopy);

         */

        Assertions.assertTrue(gridCopy.equals(simpleCommonClueTopSolution));
    }

    /**
     * Assert that differentCorners does not insert a blank in squares that have a valid clue on one but an unfilled
     * char on the other side, although they, technically speaking, do contain unequal string literals.
     */
    @Test
    public void shouldReturnEmptyGridWithNoBlanksInCorners() {
        Grid gridCopy = new Grid(gridExtendedCorner);
        gridCopy = ProceduralABC.differentCorners(extCornerPuzzle,gridCopy);

        /*
        // Uncomment for print statements for comparison
        extCornerPuzzle.printPuzzleGrid(gridExtendedCorner);
        extCornerPuzzle.printPuzzleGrid(gridCopy);
         */

        Assertions.assertTrue(gridCopy.equals(gridExtendedCorner));
    }
}
