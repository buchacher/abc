package tests;
/*
 * CS5011 A2 Starter code 
 * This enum class holds the various puzzles to be solved/tested
 *
 * Feel free to add additional puzzles to this file but please do not change any of the ones here
 *
 * author Ian Gent
 * 
 */

import mains.Puzzle;

public enum SamplePuzzles {


    // 1 x 1 puzzle
    //
     Trivial("a","a","a","a","a"),

     // 2 x 2 puzzles
     //
     VeryEasy("ab","ab","ba","ab","ba"),

     StillEasy("ab","ab","__","__","__"),

     // 3 x 3 puzzle
     NotHard("ab","aba","bab","bab","aba"),


     // 4x4 puzzle

     // Source http://puzzlepicnic.com/puzzle?3828
     // 1 star level 1
     //
     FourByFour("abc","c___","___c","b___","___a"),

     // 5x5 puzzle

     // Source http://puzzlepicnic.com/puzzle?5520
     
     FiveByFive("abc",
             "a___c",
             "__c_b",
             "___a_",
             "_c_c_"),

     // 6x6 puzzles

	//Source 
    //http://tectonicpuzzel.eu/easy-as-abc-puzzle%20techniques.html 
    //level 1, puzzle 1
    //

     ABC1( "abcd",	// letters
           "ddccba",	// clues top
	       "acdbcd",	// clues bottom
           "dbbcaa",	// clues left
	       "aacabd"  // clues right
         ),

     // level 1, puzzle 6
     ABC6( "abcd",	// letters
           "acbbda",	// clues top
	       "cbadab",	// clues bottom
           "abacdc",	// clues left
	       "dacdba"  // clues right
         ),
     
     // variants which are unsolvable
     ABCunsolvable1( "abcd",	// letters
           "acbdba",	// clues top
	       "cbadab",	// clues bottom
           "abacdc",	// clues left
	       "dacdba"  // clues right
         ),

     // variants which are unsolvable
     ABCunsolvable2( "abcd",	// letters
           "acdbda",	// clues top
	       "cbadab",	// clues bottom
           "abacdc",	// clues left
	       "dacdba"  // clues right
         ),

    // 7x7 Puzzles

    //Source http://puzzlepicnic.com/puzzle?152
    
    SevenBySeven("abcd",
            "_b_dcd_",
            "_ccca__",
            "_dddd__",
            "_cc_ad_"),

	// Source
	// http://www.gmpuzzles.com/blog/2015/09/easy-as-abc-by-prasanna-seshadri/
	// August, by Prasanna Seshadri
	
	AUGUST(	
	     "AGSTU",
	     "AUGUST_",
	     "_GUST__",
	     "_SAT__G",
	     "AUGUS_T")
    ; 

    public Puzzle puzzle; 
 
	SamplePuzzles(String letters, String top, String bottom, String left, String right) {
        this.puzzle = new Puzzle(letters,top,bottom,left,right);
	}

}


