package tests;

import mains.Puzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part2.ProceduralABC;
import part3.DeclarativeABC;

/**
 * Test class for unit tests for {@link DeclarativeABC}.
 */
public class DeclarativeABCTestsStudent {

    Puzzle veryEasy;
    DeclarativeABC veryEasyDeclarative;
    Puzzle notHard;
    DeclarativeABC notHardDeclarative;
    Puzzle fourByFour;
    DeclarativeABC fourByFourDeclarative;

    @BeforeEach
    public void setup() {
        // VeryEasy
        veryEasy = new Puzzle(
                "ab",
                "ab",
                "ba",
                "ab",
                "ba"
        );
        veryEasyDeclarative = new DeclarativeABC(veryEasy);

        // NotHard 3x3
        notHard = new Puzzle(
                "ab",
                "aba",
                "bab",
                "bab",
                "aba"
        );
        notHardDeclarative = new DeclarativeABC(notHard);

        // FourByFour
        fourByFour = new Puzzle(
                "abc",
                "c___",
                "___c",
                "b___",
                "___a"
        );
        fourByFourDeclarative = new DeclarativeABC(fourByFour);
    }

    @Test
    public void alosShouldReturnClausesAtLeastOneSymbol() {
        String expected = "(a00 | b00) & (a01 | b01) & (a10 | b10) & (a11 | b11)";
        veryEasyDeclarative.atLeastOneSymbol();
        String actual = veryEasyDeclarative.getStringSbForTesting("alos");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void alosShouldReturnClauseAtLeastOneSymbolInclBlanks() {
        String expected = "(a00 | b00 | x00) & (a01 | b01 | x01) & (a02 | b02 | x02) "
                + "& (a10 | b10 | x10) & (a11 | b11 | x11) & (a12 | b12 | x12) "
                + "& (a20 | b20 | x20) & (a21 | b21 | x21) & (a22 | b22 | x22)";
        notHardDeclarative.atLeastOneSymbol();
        String actual = notHardDeclarative.getStringSbForTesting("alos");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void amosShouldReturnClausesAtMostOneSymbolSizeTwo() {
        String expected = "(~a00 & ~b00 | a00 & ~b00 | ~a00 & b00) "
                + "& (~a01 & ~b01 | a01 & ~b01 | ~a01 & b01) "
                + "& (~a10 & ~b10 | a10 & ~b10 | ~a10 & b10) "
                + "& (~a11 & ~b11 | a11 & ~b11 | ~a11 & b11)";
        veryEasyDeclarative.atMostOneSymbol();
        String actual = veryEasyDeclarative.getStringSbForTesting("amos");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void amosShouldReturnClauseAtMostOneSymbolSizeThreeInclBlanks() {
        String expected = "(~a00 & ~b00 & ~x00 | a00 & ~b00 & ~x00 | ~a00 & b00 & ~x00 | ~a00 & ~b00 & x00) "
                + "& (~a01 & ~b01 & ~x01 | a01 & ~b01 & ~x01 | ~a01 & b01 & ~x01 | ~a01 & ~b01 & x01) "
                + "& (~a02 & ~b02 & ~x02 | a02 & ~b02 & ~x02 | ~a02 & b02 & ~x02 | ~a02 & ~b02 & x02) "
                + "& (~a10 & ~b10 & ~x10 | a10 & ~b10 & ~x10 | ~a10 & b10 & ~x10 | ~a10 & ~b10 & x10) "
                + "& (~a11 & ~b11 & ~x11 | a11 & ~b11 & ~x11 | ~a11 & b11 & ~x11 | ~a11 & ~b11 & x11) "
                + "& (~a12 & ~b12 & ~x12 | a12 & ~b12 & ~x12 | ~a12 & b12 & ~x12 | ~a12 & ~b12 & x12) "
                + "& (~a20 & ~b20 & ~x20 | a20 & ~b20 & ~x20 | ~a20 & b20 & ~x20 | ~a20 & ~b20 & x20) "
                + "& (~a21 & ~b21 & ~x21 | a21 & ~b21 & ~x21 | ~a21 & b21 & ~x21 | ~a21 & ~b21 & x21) "
                + "& (~a22 & ~b22 & ~x22 | a22 & ~b22 & ~x22 | ~a22 & b22 & ~x22 | ~a22 & ~b22 & x22)";
        notHardDeclarative.atMostOneSymbol();
        String actual = notHardDeclarative.getStringSbForTesting("amos");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void rlloShouldReturnClausesRowLetterAtLeastOnceSizeTwo() {
        String expected = "(a00 | a01) & (a10 | a11) "
                + "& (b00 | b01) & (b10 | b11)";
        veryEasyDeclarative.rowLetterAtLeastOnce();
        String actual = veryEasyDeclarative.getStringSbForTesting("rllo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void rlloShouldReturnClausesRowLetterAtLeastOnceSizeThreeBlanks() {
        String expected = "(a00 | a01 | a02) & (a10 | a11 | a12) & (a20 | a21 | a22) "
                + "& (b00 | b01 | b02) & (b10 | b11 | b12) & (b20 | b21 | b22)";
        notHardDeclarative.rowLetterAtLeastOnce();
        String actual = notHardDeclarative.getStringSbForTesting("rllo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void clloShouldReturnClausesColLetterAtLeastOnceSizeTwo() {
        String expected = "(a00 | a10) & (a01 | a11) "
                + "& (b00 | b10) & (b01 | b11)";
        veryEasyDeclarative.colLetterAtLeastOnce();
        String actual = veryEasyDeclarative.getStringSbForTesting("cllo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void clloShouldReturnClausesColLetterAtLeastOnceSizeThreeBlanks() {
        String expected = "(a00 | a10 | a20) & (a01 | a11 | a21) & (a02 | a12 | a22) "
                + "& (b00 | b10 | b20) & (b01 | b11 | b21) & (b02 | b12 | b22)";
        notHardDeclarative.colLetterAtLeastOnce();
        String actual = notHardDeclarative.getStringSbForTesting("cllo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void rlmoShouldReturnClausesRowLetterAtMostOnceSizeTwo() {
        String expected = "(~a00 & ~a01 | a00 & ~a01 | ~a00 & a01) "
                + "& (~b00 & ~b01 | b00 & ~b01 | ~b00 & b01) "
                + "& (~a10 & ~a11 | a10 & ~a11 | ~a10 & a11) "
                + "& (~b10 & ~b11 | b10 & ~b11 | ~b10 & b11)";
        veryEasyDeclarative.rowLetterAtMostOnce();
        String actual = veryEasyDeclarative.getStringSbForTesting("rlmo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void rlmoShouldReturnClausesRowLetterAtMostOnceSizeThreeBlanks() {
        String expected = "(~a00 & ~a01 & ~a02 | a00 & ~a01 & ~a02 | ~a00 & a01 & ~a02 | ~a00 & ~a01 & a02) "
                + "& (~b00 & ~b01 & ~b02 | b00 & ~b01 & ~b02 | ~b00 & b01 & ~b02 | ~b00 & ~b01 & b02) "
                + "& (~a10 & ~a11 & ~a12 | a10 & ~a11 & ~a12 | ~a10 & a11 & ~a12 | ~a10 & ~a11 & a12) "
                + "& (~b10 & ~b11 & ~b12 | b10 & ~b11 & ~b12 | ~b10 & b11 & ~b12 | ~b10 & ~b11 & b12) "
                + "& (~a20 & ~a21 & ~a22 | a20 & ~a21 & ~a22 | ~a20 & a21 & ~a22 | ~a20 & ~a21 & a22) "
                + "& (~b20 & ~b21 & ~b22 | b20 & ~b21 & ~b22 | ~b20 & b21 & ~b22 | ~b20 & ~b21 & b22)";
        notHardDeclarative.rowLetterAtMostOnce();
        String actual = notHardDeclarative.getStringSbForTesting("rlmo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void clmoShouldReturnClausesColLetterAtMostOnceSizeTwo() {
        String expected = "(~a00 & ~a10 | a00 & ~a10 | ~a00 & a10) "
                + "& (~b00 & ~b10 | b00 & ~b10 | ~b00 & b10) "
                + "& (~a01 & ~a11 | a01 & ~a11 | ~a01 & a11) "
                + "& (~b01 & ~b11 | b01 & ~b11 | ~b01 & b11)";;
        veryEasyDeclarative.colLetterAtMostOnce();
        String actual = veryEasyDeclarative.getStringSbForTesting("clmo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void clmoShouldReturnClausesColLetterAtMostOnceSizeThreeBlanks() {
        String expected = "(~a00 & ~a10 & ~a20 | a00 & ~a10 & ~a20 | ~a00 & a10 & ~a20 | ~a00 & ~a10 & a20) "
                + "& (~b00 & ~b10 & ~b20 | b00 & ~b10 & ~b20 | ~b00 & b10 & ~b20 | ~b00 & ~b10 & b20) "
                + "& (~a01 & ~a11 & ~a21 | a01 & ~a11 & ~a21 | ~a01 & a11 & ~a21 | ~a01 & ~a11 & a21) "
                + "& (~b01 & ~b11 & ~b21 | b01 & ~b11 & ~b21 | ~b01 & b11 & ~b21 | ~b01 & ~b11 & b21) "
                + "& (~a02 & ~a12 & ~a22 | a02 & ~a12 & ~a22 | ~a02 & a12 & ~a22 | ~a02 & ~a12 & a22) "
                + "& (~b02 & ~b12 & ~b22 | b02 & ~b12 & ~b22 | ~b02 & b12 & ~b22 | ~b02 & ~b12 & b22)";
        notHardDeclarative.colLetterAtMostOnce();
        String actual = notHardDeclarative.getStringSbForTesting("clmo");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void tccShouldReturnTopClues() {
        /*
        fourByFour = new Puzzle(
                "abc",
                "c___",
                "___c",
                "b___",
                "___a"
        );
         */

        String expected = "(c00 | x00 & c10)";
        fourByFourDeclarative.topClueConsistent();
        String actual = fourByFourDeclarative.getStringSbForTesting("tcc");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void bccShouldReturnBottomClues() {
        String expected = "(c33 | x33 & c23)";
        fourByFourDeclarative.bottomClueConsistent();
        String actual = fourByFourDeclarative.getStringSbForTesting("bcc");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void lccShouldReturnLeftClues() {
        String expected = "(b00 | x00 & b01)";
        fourByFourDeclarative.leftClueConsistent();
        String actual = fourByFourDeclarative.getStringSbForTesting("lcc");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void rccShouldReturnRightClues() {
        String expected = "(a33 | x33 & a32)";
        fourByFourDeclarative.rightClueConsistent();
        String actual = fourByFourDeclarative.getStringSbForTesting("rcc");

        Assertions.assertEquals(expected, actual);
    }
}
