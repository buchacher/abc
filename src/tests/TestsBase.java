package tests;

import mains.Puzzle;
import mains.Grid;
import part1.CheckerABC;
import part2.ProceduralABC;
import part3.DeclarativeABC
        ;



public class TestsBase {

      // Common code to print simple test result message and return boolean
      //
        public static boolean printTestResult(boolean pass, String testclass, String name) { 
            if (pass) {
                System.out.print("passed "); }
            else { 
                System.out.print("FAILED "); }
            System.out.print("Test " + name);
            System.out.println(" type " + testclass);
            return pass;
        }

        // Part 1
       

        public static boolean testIsConsistent(String name, boolean intended, String puzzle, String grid) {            return testIsConsistent(name,intended,SamplePuzzles.valueOf(puzzle).puzzle,SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testIsConsistent(String name, boolean intended, Puzzle puzzle, Grid grid) { 
            boolean result = CheckerABC.isConsistent(puzzle, grid);
            return printTestResult(result==intended,"isConsistent",name);
        }
       
        public static boolean testIsFullGrid(String name, boolean intended, String puzzle, String grid) {            return testIsFullGrid(name,intended,SamplePuzzles.valueOf(puzzle).puzzle,SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testIsFullGrid(String name, boolean intended, Puzzle puzzle, Grid grid) { 
            boolean result = CheckerABC.isFullGrid(puzzle, grid);
            return printTestResult(result==intended,"isFullGrid",name);
        }

        public static boolean testIsSolution(String name, boolean intended, String puzzle, String grid) {            return testIsSolution(name,intended,SamplePuzzles.valueOf(puzzle).puzzle,SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testIsSolution(String name, boolean intended, Puzzle puzzle, Grid grid) { 
            boolean result = CheckerABC.isSolution(puzzle, grid);
            return printTestResult(result==intended,"isSolution",name);
        }

        // Part 2 
        //
        public static boolean testDifferentCorners(String name, String intended, String puzzle, String grid) {           
            return testDifferentCorners(name,
                                SampleGrids.valueOf(intended).grid,
                                SamplePuzzles.valueOf(puzzle).puzzle,
                                SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testDifferentCorners(String name, Grid intended, Puzzle puzzle, Grid grid) { 
            Grid result = ProceduralABC.differentCorners(puzzle, grid);
            return printTestResult(result.equals(intended),"differentCorners",name);
        }

        public static boolean testCommonClues(String name, String intended, String puzzle, String grid) {      
            return testCommonClues(name,
                                SampleGrids.valueOf(intended).grid,
                                SamplePuzzles.valueOf(puzzle).puzzle,
                                SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testCommonClues(String name, Grid intended, Puzzle puzzle, Grid grid) { 
            Grid result = ProceduralABC.commonClues(puzzle, grid);
            return printTestResult(result.equals(intended),"commonClues",name);
        }
       
        public static boolean testOnlyPlaceCol(String name, String intended, String puzzle, String grid) {            
            return testOnlyPlaceCol(name,
                                SampleGrids.valueOf(intended).grid,
                                SamplePuzzles.valueOf(puzzle).puzzle,
                                SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testOnlyPlaceCol(String name, Grid intended, Puzzle puzzle, Grid grid) { 
            Grid result = ProceduralABC.onlyPlaceForLetterCol(puzzle, grid);
            return printTestResult(result.equals(intended),"onlyPlaceForLetterCol",name);
        }
       
        public static boolean testOnlyPlaceRow(String name, String intended, String puzzle, String grid) {            
            return testOnlyPlaceRow(name,
                                SampleGrids.valueOf(intended).grid,
                                SamplePuzzles.valueOf(puzzle).puzzle,
                                SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testOnlyPlaceRow(String name, Grid intended, Puzzle puzzle, Grid grid) { 
            Grid result = ProceduralABC.onlyPlaceForLetterRow(puzzle, grid);
            return printTestResult(result.equals(intended),"onlyPlaceForLetterRow",name);
        }
       
        public static boolean testFillInBlanksCol(String name, String intended, String puzzle, String grid) {            
            return testFillInBlanksCol(name,
                                SampleGrids.valueOf(intended).grid,
                                SamplePuzzles.valueOf(puzzle).puzzle,
                                SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testFillInBlanksCol(String name, Grid intended, Puzzle puzzle, Grid grid) { 
            Grid result = ProceduralABC.fillInBlanksCol(puzzle, grid);
            return printTestResult(result.equals(intended),"fillInBlanksCol",name);
        }

        public static boolean testFillInBlanksRow(String name, String intended, String puzzle, String grid) {            
            return testFillInBlanksRow(name,
                                SampleGrids.valueOf(intended).grid,
                                SamplePuzzles.valueOf(puzzle).puzzle,
                                SampleGrids.valueOf(grid).grid); 
        }
        public static boolean testFillInBlanksRow(String name, Grid intended, Puzzle puzzle, Grid grid) { 
            Grid result = ProceduralABC.fillInBlanksRow(puzzle, grid);
            return printTestResult(result.equals(intended),"fillInBlanksRow",name);
        }
       
        // Part 3


        public static DeclarativeABC
        testDeclarativeCommon(Puzzle puzzle ) {
            return testDeclarativeCommon(puzzle, new Grid(puzzle.size()));
        }

        public static DeclarativeABC
        testDeclarativeCommon(Puzzle puzzle, Grid grid) {
            DeclarativeABC
                    D = new DeclarativeABC
                    ();
            D.setup(puzzle,grid);
            D.createClauses();
            boolean result = D.solvePuzzle();
            return D;
        }

        // following intended where we just want to test true/false result without checking solution
        //
        public static boolean testDeclarativeBoolean(String name, boolean intended, String puzzle) { 
            return testDeclarativeBoolean(name,intended,
                                SamplePuzzles.valueOf(puzzle).puzzle);
        }
        public static boolean testDeclarativeBoolean(String name, boolean intended, Puzzle puzzle) { 
            DeclarativeABC
                    D = testDeclarativeCommon(puzzle);
            boolean passed = !D.isUnknown() & D.isTrue() == intended;
            return printTestResult(passed,"DeclarativeBoolean",name);
        }

        // following intended for solvable puzzles with known solution 
        //
        public static boolean testDeclarativeModel(String name, String intended, String puzzle) { 
            return testDeclarativeModel(name,
                                SampleGrids.valueOf(intended).grid,
                                SamplePuzzles.valueOf(puzzle).puzzle);
        }
        public static boolean testDeclarativeModel(String name, Grid intended, Puzzle puzzle ) { 
            DeclarativeABC
                    D = testDeclarativeCommon(puzzle);
            boolean result = D.solvePuzzle();
            if(!result) {
                return printTestResult(false,"DeclarativeModel",name);
            }
            else {
                Grid resultgrid = D.modelToGrid();
                return printTestResult(resultgrid.equals(intended),"DeclarativeModel",name);
            }
        }

        // following intended for solvable puzzles with unknown solution 
        //
        public static boolean testDeclarativeIsSolution(String name, String puzzle ) { 
            return testDeclarativeIsSolution(name,
                                SamplePuzzles.valueOf(puzzle).puzzle);
        }

        public static boolean testDeclarativeIsSolution(String name, Puzzle puzzle) { 
            DeclarativeABC
                    D = testDeclarativeCommon(puzzle);
            boolean result = D.solvePuzzle();
            if(!result) {
                return printTestResult(false,"DeclarativeIsSolution",name);
            }
            else {
                Grid resultgrid = D.modelToGrid();
                boolean checked = CheckerABC.isSolution(puzzle,resultgrid);
                return printTestResult(checked,"DeclarativeIsSolution",name);
            }
        }

        TestsBase() {} 

}


