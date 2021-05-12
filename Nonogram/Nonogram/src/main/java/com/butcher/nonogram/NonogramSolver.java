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
    private int size = 5;
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

    //can only mark confirmed unknowns unless it makes a pass at the end.
    //Might not need confirmed unknowns at all if solving via heuristics.
    static Cell[] solveLine(int[] values, Cell[] row) {
        Cell[] solution = row;
        int spacesNeeded = Arrays.stream(values).sum() + values.length-1;

        if(spacesNeeded == row.length) {
            solution = Heuristics.valueSumEqualToGridSize(values,row);
        } else if(row[0].getValue() == CellValue.FILLED || row[row.length].getValue() == CellValue.FILLED) {
            solution = Heuristics.valueConfirmedOnEdge(values,row);
        } else {
            for(int value : values) {
                if (value > row.length / 2) {
                    solution = Heuristics.valueMoreThanHalf(values, row);
                    break;
                }
            }
        }

        return solution;
    }






    void setSize(int size) {
        board = new Board();
        board.setSize(size);
    }



    void printBoard(PrintStream p) {
        p.println(board.toString());
    }
}
