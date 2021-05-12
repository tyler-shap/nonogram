package com.butcher.nonogram;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class NonogramSolverTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solve() {
        Board board = new Board();
        int size = 5;
        board.setSize(size);
        int[][] rows = {{1,1,1}, {1}, {4},{2},{3}};
        int[][] cols = {{1,1}, {1}, {1,1,1}, {3}, {2,2}};
        NonogramSolver solver = new NonogramSolver();
        solver.setSize(size);
        solver.setRows(rows);
        solver.setCols(cols);
        solver.setBoard(board);
        solver.solve();
        solver.printBoard(System.out);
    }

    @Test
    void solveLine() {
        Cell[] expected = getAllConfirmedTestRow(5);
        expected[0].setValue(CellValue.FILLED);
        expected[1].setValue(CellValue.OPEN);
        expected[2].setValue(CellValue.FILLED);
        expected[3].setValue(CellValue.FILLED);
        expected[4].setValue(CellValue.FILLED);

        int[] input = {1, 3};

        Cell[] result = NonogramSolver.solveLine(input, getTestRow(5));
        int i = 0;
        while( i < expected.length-1) {
            assertTrue(expected[i].getValue() == result[i].getValue());
            i++;
        }

    }

    @Test
    void solveLineDifferentSolution() {
        Cell[] expected = getAllConfirmedTestRow(5);
        expected[0].setValue(CellValue.FILLED);
        expected[1].setValue(CellValue.FILLED);
        expected[2].setValue(CellValue.OPEN);
        expected[3].setValue(CellValue.FILLED);
        expected[4].setValue(CellValue.FILLED);

        int[] input = {2, 2};

        Cell[] result = NonogramSolver.solveLine(input, getTestRow(5));
        int i = 0;
        while( i < expected.length-1) {
            assertTrue(expected[i].getValue() == result[i].getValue());
            i++;
        }
    }

    @Test
    void solveLineSingleSetInput() {
        Cell[] expected = getAllConfirmedTestRow(5);
        expected[0].setValue(CellValue.FILLED);
        expected[1].setValue(CellValue.FILLED);
        expected[2].setValue(CellValue.FILLED);
        expected[3].setValue(CellValue.OPEN);
        expected[4].setValue(CellValue.OPEN);

        int[] input = {3};

        Cell[] result = NonogramSolver.solveLine(input, getTestRowFirstFilled(5));

        int i = 0;
        while( i < expected.length-1) {
            assertTrue(expected[i].getValue() == result[i].getValue());
            i++;
        }
    }

    @Test
    void isLineCompleteTest() {
        Cell[] completeLine = getAllConfirmedTestRow(5);
        completeLine[0].setValue(CellValue.OPEN);
        completeLine[1].setValue(CellValue.OPEN);
        completeLine[2].setValue(CellValue.OPEN);
        completeLine[3].setValue(CellValue.OPEN);
        completeLine[4].setValue(CellValue.FILLED);

        assertTrue(Heuristics.isLineComplete(completeLine));

        Cell[] incompleteLine = getAllConfirmedTestRow(5);
        completeLine[0].setValue(CellValue.OPEN);
        completeLine[1].setValue(CellValue.OPEN);
        completeLine[2].setValue(CellValue.UNKNOWN);
        completeLine[3].setValue(CellValue.UNKNOWN);
        completeLine[4].setValue(CellValue.OPEN);

        assertFalse(Heuristics.isLineComplete(incompleteLine));
    }

    @Test
    void solveLineMoreThanHalf() {
        Cell[] expected = getAllConfirmedTestRow(10);
        expected[0].setValue(CellValue.UNKNOWN);
        expected[1].setValue(CellValue.UNKNOWN);
        expected[2].setValue(CellValue.FILLED);
        expected[3].setValue(CellValue.FILLED);
        expected[4].setValue(CellValue.FILLED);
        expected[5].setValue(CellValue.FILLED);
        expected[6].setValue(CellValue.FILLED);
        expected[7].setValue(CellValue.FILLED);
        expected[8].setValue(CellValue.UNKNOWN);
        expected[9].setValue(CellValue.UNKNOWN);

        int[] input = {8};

        Cell[] result = NonogramSolver.solveLine(input, getTestRow(10));

        int i = 0;
        while( i < expected.length-1) {
            assertTrue(expected[i].getValue() == result[i].getValue());
            i++;
        }
    }



    private Cell[] getTestRow(int size) {
        Cell[] row = new Cell[size];
        for (int i = 0; i < row.length; i++) {
            row[i] = new Cell();
        }
        return row;
    }

    private Cell[] getTestRowFirstFilled(int size) {
        Cell[] row = new Cell[size];
        for (int i = 0; i < row.length; i++) {
            row[i] = new Cell();
            if(i == 0) {
                row[i].setValue(CellValue.FILLED);
            }
        }
        return row;
    }

    private Cell[] getAllConfirmedTestRow(int size) {
        Cell[] row = new Cell[size];
        for (int i = 0; i < row.length; i++) {
            row[i] = new Cell();
            row[i].setConfirmed(true);
        }
        return row;
    }
}