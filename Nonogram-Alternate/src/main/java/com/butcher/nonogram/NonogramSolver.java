package com.butcher.nonogram;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintStream;
import java.util.*;

@Getter
@Setter
class NonogramSolver {
    @JsonIgnore
    private Board board;
    private int size;
    private int[][] rows;
    private int[][] cols;

    void solve() {
        Set<Integer> rowChanges = new HashSet<>();
        Set<Integer> colChanges = new HashSet<>();
        for (int r = 0; r < size; r++) {
            List<Integer> changes = board.setRow(
                    r, solveLine(rows[r], board.getRow(r))
            );
            rowChanges.addAll(changes);
        }
        for (int c = 0; c < size; c++) {
            List<Integer> changes = board.setCol(
                    c, solveLine(cols[c], board.getCol(c))
            );
            colChanges.addAll(changes);
        }

        while (!rowChanges.isEmpty() || !colChanges.isEmpty()) {
            for (int r = 0; r < colChanges.size(); r++) {
                List<Integer> changes = board.setRow(
                        r, solveLine(rows[r], board.getRow(r))
                );
                rowChanges.addAll(changes);
            }
            for (int c = 0; c < rowChanges.size(); c++) {
                List<Integer> changes = board.setCol(
                        c, solveLine(cols[c], board.getCol(c))
                );
                colChanges.addAll(changes);
            }
        }
    }

    //TODO
    static Cell[] solveLine(int[] values, Cell[] row) {
        Cell[] solution = row;

        return solution;
    }
    //[1,2] -->
    static ArrayList<int[]> getAllSolvedLines(int[] values, Cell[] row) {
        ArrayList<int[]> possibleSolvedLines = new ArrayList<>();
        int spacesNeeded = Arrays.stream(values).sum() + values.length - 1;
        int constraintSize = ((int) Arrays.stream(values).count())+1;
        for(int i = 0; i < constraintSize; i++) {
            ArrayList<Integer> line = new ArrayList<Integer>();
            for(int j = 0; j < spacesNeeded; j++) {
                //TODO
            }
        }
        return possibleSolvedLines;
    }

    void setSize(int size) {
        board = new Board();
        board.setSize(size);
    }

    void printBoard(PrintStream p) {
        p.println(board.toString());
    }
}
