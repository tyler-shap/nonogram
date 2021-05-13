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



    //the hard part i.e. [1,1]
    static ArrayList<ArrayList<int[]>> getAllSolvedLines(int[][] values, int boardSize) {
        ArrayList<ArrayList<int[]>> possibleSolvedLines = new ArrayList<>();

        for(int k = 0; k < boardSize; k++) {
            int spacesNeeded = boardSize - Arrays.stream(values[k]).sum();
            int constraintSize = values[k].length + 1;
            int[] numberOfSpaces = new int[constraintSize];

            for(int i = 0; i < constraintSize; i++) {
                if(i == 0)
                    numberOfSpaces[i] = 0;
                else if(i == constraintSize-1)
                    numberOfSpaces[i] = 0;
                else
                    numberOfSpaces[i] = 1;
            }
            possibleSolvedLines.add(findAllPermutationsOfRow(numberOfSpaces,spacesNeeded));
        }

        return possibleSolvedLines;
    }

    //TODO:  Remove dupes
    static ArrayList<int[]> findAllPermutationsOfRow(int[] numberOfSpaces, int spacesNeeded) {
        if(Arrays.stream(numberOfSpaces).sum() == spacesNeeded) {
            return new ArrayList<>(Arrays.asList(numberOfSpaces));
        } else {
            ArrayList<int[]> possibilities = new ArrayList<>();

            for (int i = 0; i < numberOfSpaces.length; i++) {
                int[] tempNumberOfSpaces = Arrays.copyOf(numberOfSpaces,numberOfSpaces.length);
                tempNumberOfSpaces[i] = tempNumberOfSpaces[i] + 1;
                possibilities.addAll(findAllPermutationsOfRow(tempNumberOfSpaces, spacesNeeded));
            }

            for(int select = 0; select < possibilities.size(); select++){
                int[] temp = possibilities.get(select);
                for(int c = select+1; c < possibilities.size(); c++) {
                    if(Arrays.equals(temp, possibilities.get(c))) {
                        possibilities.remove(c);
                    }
                }
            }
            return possibilities;
        }
    }



    void setSize(int size) {
        board = new Board();
        board.setSize(size);
    }

    void printBoard(PrintStream p) {
        p.println(board.toString());
    }
}
