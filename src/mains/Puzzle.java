package mains;
import java.util.ArrayList;

public class Puzzle {

    public static final char unfilledChar = '_';
    public static final char blankSymbol  = 'x';

    public char[] letters ; // valid letters
    public char[] symbols ; // letters blankSymbol

    public char[] top;
    public char[] bottom;
    public char[] left;
    public char[] right;


    public int size() { 
        return top.length;
    }

    public int numLetters() { 
        return letters.length;
    }

    public int numSymbols() { 
        return symbols.length;
    }

    public boolean validLetter(char c) { 
        for(char i : letters) {
               if(i==c) return true;}
        return false;
    }

    public boolean checkValid() { 
        if( top.length != size() ) return false;  
        if( bottom.length != size() ) return false;  
        if( left.length != size() ) return false;  
        if( right.length != size() ) return false;  

	for(int i=0; i < numLetters(); i++) { 
		if( letters[i] == unfilledChar || letters[i] == blankSymbol) return false;
		}
        for(int i=0; i < size() ;i++) { 
		if( top[i] != unfilledChar && !validLetter(top[i])) return false;
		if( bottom[i] != unfilledChar && !validLetter(bottom[i])) return false;
		if( left[i] != unfilledChar && !validLetter(left[i]) ) return false;
		if( right[i] != unfilledChar && !validLetter(right[i]) ) return false;
		};

    	return true;
    }

   
	public void printPuzzleGrid(Grid grid) {
        if(!grid.checkValid(this)) {
            System.out.println("Grid is not valid for the given puzzle"); }
        else {
            printPuzzleGrid(grid.chars);}
    }

	public void printPuzzleGrid(char[][] chars) {

		int size = size();


		System.out.println();
		// first line
		System.out.println("   "+String.valueOf(top)+"  ");// shift to start
		// second line
		System.out.print("   ");
		for (int j = 0; j < size; j++) {
			System.out.print("-");// separator
		}
		System.out.println();// shift to start
		// the board
		for (int i = 0; i < size; i++) {
			System.out.print(left[i] + "| ");// left+separator
			for (int j = 0; j < chars[0].length; j++) {
				System.out.print(chars[i][j]);// value in the board
			}
			System.out.print(" |"+right[i] );// separator+right
			System.out.println();
		}
		// penultimate line
		System.out.print("   ");
		for (int j = 0; j < size; j++) {
			System.out.print("-");// separator
		}
		System.out.println("  ");// shift to start
		// last line
		System.out.println("   "+String.valueOf(bottom)+"  ");// shift to start
	}
	

	public Puzzle(String letterStr, String topStr, String bottomStr, String leftStr, String rightStr) {
        this.letters = letterStr.toCharArray();
        this.top = topStr.toCharArray();
        this.bottom = bottomStr.toCharArray();
        this.left = leftStr.toCharArray();
        this.right = rightStr.toCharArray();
	    this.symbols = (letterStr+String.valueOf(blankSymbol)).toCharArray();
	}

    public char[] getLetters() {
        return letters;
    }
}


