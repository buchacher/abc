package mains;
/*
 * CS5011 A2 Starter code 
 * This enum class holds the various boards to be played
 *
 * author Alice Toniolo, Ian Gent
 * 
 * 
 * TEST: 3 boards
 * SMALL (S): 5x5 with 5T, 10 boards
 * MEDIUM (M): 7x7 with 10T, 10 boards
 * LARGE (L): 11x11 with 28T, 10 boards
 * 
 * Two hints are given at indexes [0][0] and [length/2][length/2]
 * 
 * 
 */

import mains.Puzzle;

public class Grid {

    public char[][] chars;

    

    public boolean equals(Grid other) { 
        int size = size();
        if(size != other.size() || !checkValid() || !checkValid(other)) 
            {return false;}
        for(int i=0; i< size; i++) { 
            for (int j=0; j< size; j++) {
                if (chars[i][j] != other.chars[i][j]) {
                    return false;
                }}}
        return true;
    }

    public int size() { 
        return chars.length;
    }

    public boolean checkValid() { 
        return checkValid(this);
        }
    
    public boolean checkValid(Grid grid) { 
        int size = grid.size();
        for(int i=0; i<size; i++) { 
            if (grid.chars.length != size) { 
                return false;}
            }
        return true;
        }
    
    public boolean checkValid(Puzzle puzzle) { 
        if (!checkValid()) return false; 

        for(int i=0; i<size(); i++){
            for(int j=0; j<size(); j++){
                if(chars[i][j] != puzzle.unfilledChar && chars[i][j]!=puzzle.blankSymbol &&
                        !puzzle.validLetter(chars[i][j])) { 
                    return false;
                }
            }
		}
	    return true;
	}
	
    // Constructs new grid of given size with each square unfilled
    //
    public Grid(int n) { 
        this.chars = new char[n][n];
        for (int i=0;i<n;i++) {
            for(int j=0;j<n;j++) { 
                 this.chars[i][j] = Puzzle.unfilledChar; 
            }}}

    // Constructs new grid with given strings as rows
    public Grid(String rows[]) { 
        int size = rows.length;
        this.chars = new char[size][size];
        for (int i=0;i<size;i++) {
            for(int j=0;j<size;j++) { 
                 this.chars[i][j] = rows[i].charAt(j); 
            }}}

    // copy constructor
    public Grid(Grid toCopy) { 
        int size=toCopy.size();
        char[][] copied = new char[size][size];
        for (int i=0;i<size;i++) {
            for(int j=0;j<size;j++) { 
                 copied[i][j] = toCopy.chars[i][j]; 
            }}
        this.chars=copied;
    }

    
    // Just use given grid 
    
    public Grid(char[][] chars) {
	    this.chars = chars;
	}

}


