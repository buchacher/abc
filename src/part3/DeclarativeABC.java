package part3;


import mains.Grid;
import mains.Puzzle;
import org.logicng.datastructures.Assignment;
import org.logicng.datastructures.Tristate;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.formulas.Variable;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.solvers.MiniSat;
import org.logicng.solvers.SATSolver;

import java.util.ArrayList;
import java.util.List;

public class DeclarativeABC {

	private Puzzle puzzle;
	private Grid grid;

	FormulaFactory f;
	PropositionalParser p;
	MiniSat solver;
    Tristate result;

    // Added
    private ArrayList<String> KB;
    private int size;
    private char[] symbols;
    private char[] letters;
    /** The delta between puzzle size and number of letters (unique, excl. blank), which determines the number of
     * admissible blanks per row or column. */
    private int delta;
    private char[] top;
    private char[] bottom;
    private char[] left;
    private char[] right;
    Assignment model;
    MiniSat miniSat;

    /** StringBuilders for unit tests in {@link tests.ProceduralABCTestsStudent} */
    private StringBuilder alos;
    private StringBuilder amos;
    private StringBuilder rllo;
    private StringBuilder cllo;
    private StringBuilder rlmo;
    private StringBuilder clmo;
    private StringBuilder tcc;
    private StringBuilder bcc;
    private StringBuilder lcc;
    private StringBuilder rcc;


    public DeclarativeABC()  {
    }

	public DeclarativeABC(Puzzle puzzle)  {
	    this.setup(puzzle);
    }

	public DeclarativeABC(Puzzle puzzle, Grid grid)  {
	    this.setup(puzzle,grid);
    }

    public boolean isUnknown() { 
        return result == Tristate.UNDEF;
    }
    public boolean isTrue() { 
        return result == Tristate.TRUE;
    }
    public boolean isFalse() { 
        return result == Tristate.FALSE;
    }

	public void setup(Puzzle puzzle)  { 
        this.setup(puzzle,new Grid(puzzle.size())); 
    }

    /**
     * Sets up any necessary data structures.
     */
	public void setup(Puzzle puzzle, Grid grid)  { 
        this.puzzle=puzzle;
        this.grid=grid;

		this.f = new FormulaFactory();
		this.p = new PropositionalParser(f);
		this.solver = MiniSat.miniSat(f);
        this.result = Tristate.UNDEF;

        // Added
        this.KB = new ArrayList<>();
        this.size = puzzle.size();
        this.symbols = puzzle.symbols;
        this.letters = puzzle.letters;
        this.delta = size - letters.length;
        this.top = puzzle.top;
        this.bottom = puzzle.bottom;
        this.left = puzzle.left;
        this.right = puzzle.right;

        this.miniSat = MiniSat.miniSat(f);

        // For testing purposes
        this.alos = new StringBuilder();
        this.amos = new StringBuilder();
        this.rllo = new StringBuilder();
        this.cllo = new StringBuilder();
        this.rlmo = new StringBuilder();
        this.clmo = new StringBuilder();
        this.tcc = new StringBuilder();
        this.bcc = new StringBuilder();
        this.lcc = new StringBuilder();
        this.rcc = new StringBuilder();
    }

    /**
     * Creates the necessary clauses.
     */
    public void createClauses() {
        atLeastOneSymbol();
        atMostOneSymbol();
        rowLetterAtLeastOnce();
        colLetterAtLeastOnce();
        rowLetterAtMostOnce();
        colLetterAtMostOnce();
        topClueConsistent();
        bottomClueConsistent();
        leftClueConsistent();
        rightClueConsistent();
    }

    /**
     * Encodes the constraint that every square contains at least one symbol into clauses and adds them to the
     * knowledge base.
     */
    public void atLeastOneSymbol() {
        StringBuilder everySquareLeast = new StringBuilder();

        char[] inLoopSymbols;
        if (puzzle.numLetters() == size) { // No blanks because unique letters = puzzle size
            inLoopSymbols = new char[puzzle.numLetters()];
            if (puzzle.numLetters() >= 0) System.arraycopy(symbols, 0, inLoopSymbols, 0, puzzle.numLetters());
        }
        else {
            inLoopSymbols = new char[puzzle.numSymbols()];
            if (puzzle.numSymbols() >= 0) System.arraycopy(symbols, 0, inLoopSymbols, 0, puzzle.numSymbols());
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                everySquareLeast.append("(");
                for (char symbol : inLoopSymbols) {
                    if (symbol != inLoopSymbols[inLoopSymbols.length-1]) {
                        everySquareLeast.append(symbol).append(i).append(j).append(" | ");
                    }
                    else if (i == size-1 && j == size-1 && symbol == inLoopSymbols[inLoopSymbols.length-1]) {
                        everySquareLeast.append(symbol).append(i).append(j).append(")");
                    }
                    else {
                        everySquareLeast.append(symbol).append(i).append(j).append(") & ");
                    }
                }
            }
        }

        KB.add(everySquareLeast.toString());

        // For testing purposes
        alos.append(everySquareLeast);
    }

    /**
     * Encodes the constraint that every square contains at most one symbol into clauses and adds them to the
     * knowledge base.
     */
    public void atMostOneSymbol() {
        StringBuilder everySquareMost = new StringBuilder();

        char[] inLoopSymbols;
        if (puzzle.numLetters() == size) { // No blanks because unique letters = puzzle size
            inLoopSymbols = new char[puzzle.numLetters()];
            if (puzzle.numLetters() >= 0) System.arraycopy(symbols, 0, inLoopSymbols, 0, puzzle.numLetters());
        }
        else {
            inLoopSymbols = new char[puzzle.numSymbols()];
            if (puzzle.numSymbols() >= 0) System.arraycopy(symbols, 0, inLoopSymbols, 0, puzzle.numSymbols());
        }

        for (int i = 0; i < size; i++) {
            if (i == 0) {
                everySquareMost.append("(");
            }
            for (int j = 0; j < size; j++) {
                for (char symbol : inLoopSymbols) {
                    everySquareMost.append("~").append(symbol).append(i).append(j);
                    if (symbol != inLoopSymbols[inLoopSymbols.length-1]) {
                        everySquareMost.append(" & ");
                    }
                    else {
                        everySquareMost.append(" | ");
                    }
                }
                for (int k = 0; k < inLoopSymbols.length; k++) {
                    for (char symbol : inLoopSymbols) {
                        if (symbol == inLoopSymbols[k]) {
                            everySquareMost.append(symbol).append(i).append(j);
                        }
                        else {
                            everySquareMost.append("~").append(symbol).append(i).append(j);
                        }
                        if (symbol != inLoopSymbols[inLoopSymbols.length-1]) {
                            everySquareMost.append(" & ");
                        }
                    }
                    if (k != inLoopSymbols.length-1) {
                        everySquareMost.append(" | ");
                    }
                    else if (i < inLoopSymbols.length) {
                        everySquareMost.append(") & (");
                    }
                }
            }
        }
        everySquareMost.append(")");

        KB.add(everySquareMost.substring(0, everySquareMost.length()-5));

        // For testing purposes
        amos.append(everySquareMost.substring(0, everySquareMost.length()-5));
    }

    /**
     * Encodes the constraint that every row holds every letter at least once into clauses and adds them to the
     * knowledge base.
     */
    public void rowLetterAtLeastOnce() {
        StringBuilder everyRowLeast = new StringBuilder();
        everyRowLeast.append("(");
        for (char letter : letters) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (j != size-1) {
                        everyRowLeast.append(letter).append(i).append(j).append(" | ");
                    }
                    else if (i == size-1 && j == size-1 && letter == letters[letters.length-1]) {
                        everyRowLeast.append(letter).append(i).append(j).append(")");
                    }
                    else {
                        everyRowLeast.append(letter).append(i).append(j).append(") & (");
                    }
                }
            }
        }

        KB.add(everyRowLeast.toString());

        // For testing purposes
        rllo.append(everyRowLeast);
    }

    /**
     * Encodes the constraint that every column holds every letter at least once into clauses and adds them to the
     * knowledge base.
     */
    public void colLetterAtLeastOnce() {
        StringBuilder everyColLeast = new StringBuilder();
        everyColLeast.append("(");
        for (char letter : letters) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (j != size-1) {
                        everyColLeast.append(letter).append(j).append(i).append(" | ");
                    }
                    else if (i == size-1 && j == size-1 && letter == letters[letters.length-1]) {
                        everyColLeast.append(letter).append(j).append(i).append(")");
                    }
                    else {
                        everyColLeast.append(letter).append(j).append(i).append(") & (");
                    }
                }
            }

        }

        KB.add(everyColLeast.toString());

        // For testing purposes
        cllo.append(everyColLeast);
    }

    /**
     * Encodes the constraint that every row has each letter at most once into clauses and adds them to the knowledge
     * base.
     */
    public void rowLetterAtMostOnce() {
        List<String> constraints = new ArrayList<>();

        for(int row = 0; row < size; row++) {
            for(char letter : letters) {

                List<String> permutations = new ArrayList<>();

                for(int perm = 0; perm <= size; perm++) {

                    List<String> positions = new ArrayList<>();

                    for(int col = 0; col < size; col++) {
                        boolean isNegated = (col + 1) != perm;
                        positions.add(String.format("%s%s%d%d", isNegated? "~" : "", letter, row, col));
                    }

                    String conj = String.join(" & ", positions);
                    permutations.add(conj);
                }

                String constraint = "(" + String.join(" | ", permutations) + ")";
                constraints.add(constraint);
            }
        }

        String rowMostOnce = String.join(" & ", constraints);

        KB.add(rowMostOnce);

        // For testing purposes
        rlmo.append(rowMostOnce);
    }

    /**
     * Encodes the constraint that every column has each letter at most once into clauses and adds them to the
     * knowledge base.
     */
    public void colLetterAtMostOnce() {
        List<String> constraints = new ArrayList<>();

        for(int col = 0; col < size; col++) {
            for(char letter : letters) {

                List<String> permutations = new ArrayList<>();

                for(int perm = 0; perm <= size; perm++) {

                    List<String> positions = new ArrayList<>();

                    for(int row = 0; row < size; row++) {
                        boolean isNegated = (row + 1) != perm;
                        positions.add(String.format("%s%s%d%d", isNegated? "~" : "", letter, row, col));
                    }

                    String conj = String.join(" & ", positions);
                    permutations.add(conj);
                }

                String constraint = "(" + String.join(" | ", permutations) + ")";
                constraints.add(constraint);
            }
        }

        String colMostOnce = String.join(" & ", constraints);

        KB.add(colMostOnce);

        // For testing purposes
        clmo.append(colMostOnce);
    }

    /**
     * For the top row of clues, encodes the constraint that where a clue is given, the first symbol in that direction
     * is consistent with the clue, i.e. the first non-blank symbol is the clued letter, into clauses and adds them to
     * the knowledge base. If no clue is provided, no restriction applies.
     */
    public void topClueConsistent() {
        List<String> allConstraints = new ArrayList<>();
        for (int col = 0; col < size; col++) {
            if (top[col] != Puzzle.unfilledChar) { // Only applicable if clue given
                List<String> constraints = new ArrayList<>();
                for(int row = 0; row <= delta; row++) {
                    List<String> positions = new ArrayList<>();
                    for(int prevRow = 0; prevRow < row; prevRow++) {
                        positions.add(String.format("%s%d%d", Puzzle.blankSymbol, prevRow, col));
                    }

                    positions.add(String.format("%s%d%d", top[col], row, col));

                    String conj = String.join(" & ", positions);
                    constraints.add(conj);
                }

                allConstraints.add("(" + String.join(" | ", constraints) + ")");
            }
        }

        KB.add(String.join(" & ", allConstraints));

        // For testing purposes
        tcc.append(String.join(" & ", allConstraints));
    }

    /**
     * For the bottom row of clues, encodes the constraint that where a clue is given, the first symbol in that
     * direction is consistent with the clue, i.e. the first non-blank symbol is the clued letter, into clauses and
     * adds them to the knowledge base. If no clue is provided, no restriction applies.
     */
    public void bottomClueConsistent() {
        List<String> allConstraints = new ArrayList<>();
        for (int col = 0; col < size; col++) {
            if (bottom[col] != Puzzle.unfilledChar) { // Only applicable if clue given
                List<String> constraints = new ArrayList<>();
                for(int row = size-1; row > delta; row--) {
                    List<String> positions = new ArrayList<>();
                    for(int prevRow = size-1; prevRow > row; prevRow--) {
                        positions.add(String.format("%s%d%d", Puzzle.blankSymbol, prevRow, col));
                    }

                    positions.add(String.format("%s%d%d", bottom[col], row, col));

                    String conj = String.join(" & ", positions);
                    constraints.add(conj);
                }

                allConstraints.add("(" + String.join(" | ", constraints) + ")");
            }
        }

        KB.add(String.join(" & ", allConstraints));

        // For test
        bcc.append(String.join(" & ", allConstraints));
    }

    /**
     * For the left column of clues, encodes the constraint that where a clue is given, the first symbol in that
     * direction is consistent with the clue, i.e. the first non-blank symbol is the clued letter, into clauses and
     * adds them to the knowledge base. If no clue is provided, no restriction applies.
     */
    public void leftClueConsistent() {
        List<String> allConstraints = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            if (left[row] != Puzzle.unfilledChar) { // Only applicable if clue given
                List<String> constraints = new ArrayList<>();
                for(int col = 0; col <= delta; col++) {
                    List<String> positions = new ArrayList<>();
                    for(int prevCol = 0; prevCol < col; prevCol++) {
                        positions.add(String.format("%s%d%d", Puzzle.blankSymbol, prevCol, row));
                    }

                    positions.add(String.format("%s%d%d", left[row], row, col));

                    String conj = String.join(" & ", positions);
                    constraints.add(conj);
                }

                allConstraints.add("(" + String.join(" | ", constraints) + ")");
            }
        }

        KB.add(String.join(" & ", allConstraints));

        // For test
        lcc.append(String.join(" & ", allConstraints));
    }

    /**
     * For the right column of clues, encodes the constraint that where a clue is given, the first symbol in that
     * direction is consistent with the clue, i.e. the first non-blank symbol is the clued letter, into clauses and
     * adds them to the knowledge base. If no clue is provided, no restriction applies.
     */
    public void rightClueConsistent() {
        List<String> allConstraints = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            if (right[row] != Puzzle.unfilledChar) { // Only applicable if clue given
                List<String> constraints = new ArrayList<>();
                for(int col = size-1; col > delta; col--) {
                    List<String> positions = new ArrayList<>();
                    for(int prevCol = size-1; prevCol > col; prevCol--) {
                        positions.add(String.format("%s%d%d", Puzzle.blankSymbol, prevCol, row));
                    }

                    positions.add(String.format("%s%d%d", right[row], row, col));

                    String conj = String.join(" & ", positions);
                    constraints.add(conj);
                }

                allConstraints.add("(" + String.join(" | ", constraints) + ")");
            }
        }

        KB.add(String.join(" & ", allConstraints));

        // For test
        rcc.append(String.join(" & ", allConstraints));
    }



    /**
     * Uses a MiniSAT solver to solve the problem.
     */
    public boolean solvePuzzle() {
        createClauses();

        try {
            for (String statements : KB) {
                Formula KBraw = p.parse(statements);
                Formula KBcnf = KBraw.cnf();
                miniSat.add(KBcnf);
            }
            result = miniSat.sat();
            model = miniSat.model();
            return (result == Tristate.TRUE);
        } catch (ParserException e) {
            /*
            // Uncomment to print stack trace
            e.printStackTrace();
             */
            System.out.println("LogicNG throwing ParserException");
        }

        return false;
    }

    /**
     * (When the puzzle has been solved) returns a Grid object with the solution in it.
     */
    public Grid modelToGrid() { 
        if (result != Tristate.TRUE) {
            return grid;
        }
        else {
            Grid gridCopy = new Grid(grid);
            char[][] chars = gridCopy.chars;

            // Get and iterate through true clauses; place letters in grid
            for (Variable v : model.positiveVariables()) {
                String s = String.valueOf(v).trim();
                char symbol = s.charAt(0);
                int row = Integer.parseInt(s.substring(1,2));
                int col = Integer.parseInt(s.substring(2,3));
                chars[row][col] = symbol;
            }
            return gridCopy;
        }
    }

    /**
     * Method for unit testing of the methods in {@link DeclarativeABC}. Can be called in unit tests in
     * {@link tests.ProceduralABCTestsStudent} in order to assert that actual equals expected method output.
     */
    public String getStringSbForTesting(String method) {
        if ("alos".equals(method)) { // atLeastOneSymbol
            return alos.toString();
        }
        else if ("amos".equals(method)) { // atMostOneSymbol
            return amos.toString();
        }
        else if ("rllo".equals(method)) { // rowLetterAtLeastOnce
            return rllo.toString();
        }
        else if ("cllo".equals(method)) { // colLetterAtLeastOnce
            return cllo.toString();
        }
        else if ("rlmo".equals(method)) { // colLetterAtLeastOnce
            return rlmo.toString();
        }
        else if ("clmo".equals(method)) { // colLetterAtLeastOnce
            return clmo.toString();
        }
        else if ("tcc".equals(method)) { // topClueConsistent
            return tcc.toString();
        }
        else if ("bcc".equals(method)) { // bottomClueConsistent
            return bcc.toString();
        }
        else if ("lcc".equals(method)) { // leftClueConsistent
            return lcc.toString();
        }
        else if ("rcc".equals(method)) { // rightClueConsistent
            return rcc.toString();
        }
        return "Not valid param";
    }
}
