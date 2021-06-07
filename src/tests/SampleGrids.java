package tests;

/*
 * CS5011 A2 Starter code 
 * This enum class holds the various puzzles to be solved/tested
 *
 * Feel free to add additional grids to this file but please do not change any of the ones here
 *
 * author Ian Gent
 * 
 */

import mains.Puzzle;
import mains.Grid;

public enum SampleGrids {

        TrivialSol(new String[] {
                   "a"}),

        EasySol(new String[] {
                   "ab",
                   "ba"}),

        NotHardSol(new String[] {
                   "xba",
                   "axb",
                   "bax"}),

        FourByFourSol(new String[] {
                   "xbca",
                   "caxb",
                   "axbc",
                   "bcax"}),
        
        FiveByFiveSol(new String[] {
                   "acxbx",
                   "bxaxc",
                   "cbxxa",
                   "xabcx",
                   "xxcab"}),

        SevenBySevenSol(new String[] {
                   "cbaxxdx",
                   "dabxcxx",
                   "xxxdbac",
                   "xxdaxcb",
                   "xdcxxba",
                   "acxbdxx",
                   "bxxcaxd"}),

        ABC6Empty(new String[] { 
                    "______",
                    "______",
                    "______",
                    "______",
                    "______",
                    "______"
                    }),

        ABC6Sol(new String[] {
                   "acbxdx",
                   "bdxxca",
                   "xadbxc",
                   "xxcabd",
                   "dxacxb",
                   "cbxdax"}),



        ABC1Empty(new String[] { 
                    "______",
                    "______",
                    "______",
                    "______",
                    "______",
                    "______"
                    }),

        ABC1incomplete(new String[] { 
                    "dxxcba",
                    "bdcaxx",
                    "xbaxdc",
                    "cxb_ax",
                    "xadxcb",
                    "acxbxd"}),

        ABC1inconsistent1(new String[] { 
                    "bxxcba",
                    "bdcaxx",
                    "xbaxdc",
                    "cxbdax",
                    "xadxcb",
                    "acxbxd"}),

        ABC1inconsistent2(new String[] { 
                    "dxxxba",
                    "bdcaxx",
                    "xbaxdc",
                    "cxbdax",
                    "xadxcb",
                    "acxbxd"}),

        ABC1inconsistent3(new String[] { 
                    "dxxcba",
                    "bdcaxx",
                    "xbxadc",
                    "cxbdax",
                    "xadxcb",
                    "acxbxd"}),

        ABC1inconsistent4(new String[] { 
                    "______",
                    "______",
                    "______",
                    "______",
                    "__b___",
                    "__x___"}),

        ABC1inconsistent5(new String[] { 
                    "___c__",
                    "______",
                    "______",
                    "___c__",
                    "______",
                    "______"}),

        ABC1inconsistent6(new String[] { 
                    "__e___",
                    "______",
                    "______",
                    "______",
                    "______",
                    "______"}),

        ABC1inconsistent7(new String[] { 
                    "__ ___",
                    "______",
                    "______",
                    "______",
                    "______",
                    "______"}),

        ABC1inconsistent8(new String[] { 
                    "______",
                    "___bxx",
                    "______",
                    "______",
                    "______",
                    "______"}),

        ABC1inconsistent9(new String[] { 
                    "______",
                    "______",
                    "xd____",
                    "______",
                    "______",
                    "______"}),

        ABC1inconsistent10(new String[] { 
                    "__x___",
                    "__x___",
                    "__d___",
                    "______",
                    "______",
                    "______"}),

        ABC1inconsistent11(new String[] { 
                    "__x___",
                    "__x___",
                    "__c___",
                    "__a___",
                    "__d___",
                    "__x___"}),

        ABC1inconsistent12(new String[] { 
                    "______",
                    "bxcaxx",
                    "______",
                    "______",
                    "______",
                    "______"}),

        ABC1Sol(new String[] { 
                    "dxxcba",
                    "bdcaxx",
                    "xbaxdc",
                    "cxbdax",
                    "xadxcb",
                    "acxbxd"}),

        ABC1testcols(new String[] { 
                    "dxxcba",
                    "bdcaxx",
                    "xbaxdc",
                    "cxbdax",
                    "xadxcb",
                    "______"}),
        ABC1testblankscolscols(new String[] {
                   "dxxcba",
                   "bdcaxx",
                   "xbaxdc",
                   "cxbdax",
                   "xadxcb",
                   "__x_x_"}),
        ABC1testblanksrowsrows(new String[] {
                   "_xxcba",
                   "_dcaxx",
                   "xbaxdc",
                   "_xbdax",
                   "xadxcb",
                   "_cxbxd"}),

        ABC1testrows(new String[] { 
                    "_xxcba",
                    "_dcaxx",
                    "_baxdc",
                    "_xbdax",
                    "_adxcb",
                    "_cxbxd"}),
        ABC1testrowscols(new String[] {
                   "_xxcba",
                   "_dcaxx",
                   "_baxdc",
                   "_xbdax",
                   "_adxcb",
                   "_cxbxd"}),

        ABC1testboth(new String[] { 
                    "_xxcba",
                    "b_caxx",
                    "xb_xdc",
                    "cxb_ax",
                    "xadx_b",
                    "acxbx_"}),

        ABCdifferentcorners6(new String[] {
                   "_____x",
                   "______",
                   "______",
                   "______",
                   "______",
                   "_____x"}),
        

        ABCcommonclues1(new String[] {
                   "d___ba",
                   "______",
                   "_____c",
                   "c_____",
                   "_____b",
                   "a__b_d"}),
        ABCcommonclues6(new String[] {
                   "_c__d_",
                   "b_____",
                   "_____c",
                   "______",
                   "d____b",
                   "c__d__"}),


        ABC1testrowsrows(new String[] {
                   "dxxcba",
                   "bdcaxx",
                   "_baxdc",
                   "cxbdax",
                   "_adxcb",
                   "acxbxd"}),
        ABC1testcolsrows(new String[] {
                   "dxxcba",
                   "bdcaxx",
                   "xbaxdc",
                   "cxbdax",
                   "xadxcb",
                   "______"}),
        ABC1testonlyplacecolrows(new String[] {
                   "_xxcba",
                   "_dcaxx",
                   "_baxdc",
                   "_xbdax",
                   "_adxcb",
                   "_cxbxd"}),
        ABC1testonlyplacecolcols(new String[] {
                   "dxxcba",
                   "bdcaxx",
                   "xbaxdc",
                   "cxbdax",
                   "xadxcb",
                   "ac_b_d"}),
        ABC1testonlyplacerowrows(new String[] {
                   "dxxcba",
                   "bdcaxx",
                   "_baxdc",
                   "cxbdax",
                   "_adxcb",
                   "acxbxd"}),
        ABC1testonlyplacerowcols(new String[] {
                   "dxxcba",
                   "bdcaxx",
                   "xbaxdc",
                   "cxbdax",
                   "xadxcb",
                   "______"}),
	
        ABC1testblanks(new String[] { 
                    "d__cba",
                    "bdca__",
                    "_ba_dc",
                    "c_bda_",
                    "_ad_cb",
                    "ac_b_d"})
    ; 
 

    public Grid grid;

    SampleGrids(char[][] chars) {
	    this.grid = new Grid(chars);
    }

    SampleGrids(String[] strings) { 
        this.grid = new Grid(strings);
	}

}


