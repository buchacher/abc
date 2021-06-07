package tests;

import mains.Grid;
import mains.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part1.CheckerABC;

/**
 * Test class for unit tests for {@link CheckerABC}.
 */
public class CheckerABCTestsStudent {

    Puzzle puzzle;
    Grid fullConsistentGrid;
    Grid notFullGrid;
    Grid inconsistentGrid;

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

        notFullGrid = new Grid(new String[]{
                "a_bxdx",
                "bdxxca",
                "xad_xc",
                "x_cabd",
                "dxa_xb",
                "c_xdax"
        });

        inconsistentGrid = new Grid(new String[]{
                "acaxdx",
                "bxxxca",
                "xadbxc",
                "xxcabd",
                "xxacxb",
                "cbxdax"
        });
    }

    @Test
    public void isFullGridShouldReturnTrue() {
        Assertions.assertTrue(CheckerABC.isFullGrid(puzzle, fullConsistentGrid));
    }

    @Test
    public void isFullGridShouldReturnFalse() {
        Assertions.assertFalse(CheckerABC.isFullGrid(puzzle, notFullGrid));
    }

    @Test
    public void isConsistentShouldReturnTrue() {
        Assertions.assertTrue(CheckerABC.isConsistent(puzzle, fullConsistentGrid));
    }

    @Test
    public void isConsistentShouldReturnFalse() {
        Assertions.assertFalse(CheckerABC.isConsistent(puzzle, inconsistentGrid));
    }
}
