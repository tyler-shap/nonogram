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
    void getAllPermutationsOfRow() {
        NonogramSolver solver = new NonogramSolver();
        int[] numberOfSpaces = new int[]{0,1,1,0};
        int spacesNeeded = 4;
        ArrayList<int[]> ans = solver.findAllPermutationsOfRow(numberOfSpaces, spacesNeeded);
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
        NonogramSolver solver = new NonogramSolver();
        int[][] values = new int[][] {{3},{3,3},{3},{4,1,2},{4,1,1},{3,3},{1,3,1},{1,1,2},{1,1,2},{1,2,3}};
        int boardSize = 10;

        ArrayList<ArrayList<int[]>> allLinePermutations =
                solver.getAllPossibleLines(values, boardSize);

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