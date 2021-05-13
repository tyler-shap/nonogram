package com.butcher.nonogram;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NonogramSolverTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solve() {
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

        //assertArrayEquals(expected, result);

    }

    private Cell[] getTestRow(int size) {
        Cell[] row = new Cell[size];
        for (int i = 0; i < row.length; i++) {
            row[i] = new Cell();
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

    @Test
    void getAllPermutationsOfRow() {
        int[] numberOfSpaces = new int[]{0,1,1,0};
        int spacesNeeded = 4;
        ArrayList<int[]> ans = NonogramSolver.findAllPermutationsOfRow(numberOfSpaces, spacesNeeded);
        StringBuilder finalString = new StringBuilder();
        for(int[] a : ans) {
            finalString.append('[');
            for(int i = 0; i < a.length; i++) {
                finalString.append(a[i]);
            }
            finalString.append(']');
        }
        System.out.println(finalString.toString());
    }

    @Test
    void getAllLinePermutations() {
        int[][] values = new int[][] {{3},{3,3},{3},{4,1,2},{4,1,1},{3,3},{1,3,1},{1,1,2},{1,1,2},{1,2,3}};
        int boardSize = 10;

        ArrayList<ArrayList<int[]>> allLinePermutations =
                NonogramSolver.getAllSolvedLines(values, boardSize);

        StringBuilder finalString = new StringBuilder();
        for(ArrayList<int[]> a : allLinePermutations) {
            for(int[] b : a) {
                finalString.append('[');
                for (int i = 0; i < b.length; i++) {
                    finalString.append(b[i]);
                }
                finalString.append(']');
            }
        }
        System.out.println(finalString.toString());
    }
}