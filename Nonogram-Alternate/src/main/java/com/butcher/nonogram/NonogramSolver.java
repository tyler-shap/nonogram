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
    private int size;
    private int[][] rows;
    private int[][] cols;
    private Board board;

    //temporary solution, going to make this recursive soon
    void solve() {
        long start = System.currentTimeMillis();

        int[] whichRows = new int[size];
        Arrays.fill(whichRows,0);
        Board testBoard = new Board();
        testBoard.setSize(size);

        ArrayList<ArrayList<int[]>> possibleLines = getAllPossibleLines(rows,size);

        while(true) {
            for(int rowCount = 0; rowCount < size; rowCount++) {
                testBoard.setRow(rowCount,
                        generateRow(rowCount, possibleLines.get(rowCount).get(whichRows[rowCount])));
            }
            //uncomment to print out all permutations
//            board = testBoard;
//            printBoard(System.out);

            if(isBoardFinished(testBoard))  {
                board = testBoard;
                break;
            }

            whichRows[0]++;

            for(int rowCount = 0; rowCount < size; rowCount++) {
                if(whichRows[rowCount] >= possibleLines.get(rowCount).size()) {
                    whichRows[rowCount] = 0;
                    whichRows[rowCount+1] += 1;
                }
            }
        }


        long end = System.currentTimeMillis();
        long elapsedTime = end - start;

        printBoard(System.out);
        System.out.println("\nFinished.  \nTime elapsed: " + elapsedTime/1000L);
    }

    boolean isBoardFinished(Board board) {
        for(int i = 0; i < size; i++) {
            if(!isColumnValid(board.getCol(i), cols[i]))
                return false;
        }
        return true;
    }

    private boolean isColumnValid(Cell[] column, int[] constraints) {
        int currentCellAddress = 0;

        for(int currentConstraint = 0; currentConstraint < constraints.length; currentConstraint++) {
            while(column[currentCellAddress].getValue() == CellValue.OPEN) {
                currentCellAddress++;
            }
            for(int filledCount = 0; filledCount < constraints[currentConstraint]; filledCount++) {
                if(currentCellAddress >= size)   return false;
                if(column[currentCellAddress].getValue() == CellValue.OPEN) return false;
                currentCellAddress++;
            }
            if(currentConstraint == constraints.length-1) {
                while(currentCellAddress < size) {
                    if(column[currentCellAddress].getValue() == CellValue.FILLED) return false;
                    currentCellAddress++;
                }
            } else {
                if(currentCellAddress >= size)  return false;
                if(column[currentCellAddress].getValue() == CellValue.FILLED) return false;
            }
        }
        return true;
    }

    Cell[] generateRow(int rowNum, int[] rowSpaces) {
        Cell[] generatedRow = new Cell[size];
        for (int i = 0; i < size; i++) {
            generatedRow[i] = new Cell();
        }

        int spacesCount = 0;
        int cellCount = 0;
        while(spacesCount < rowSpaces.length-1) {
            for(int s = 0; s < rowSpaces[spacesCount]; s++) {
                generatedRow[cellCount].setValue(CellValue.OPEN);
                cellCount++;
            }
            for(int f = 0; f < rows[rowNum][spacesCount]; f++) {
                generatedRow[cellCount].setValue(CellValue.FILLED);
                cellCount++;
            }
            spacesCount++;
        }

        return generatedRow;
    }


    //Gets all permutations of every row in the board, in order by array list
    //Values = row constraints
    ArrayList<ArrayList<int[]>> getAllPossibleLines(int[][] values, int boardSize) {
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


    ArrayList<int[]> findAllPermutationsOfRow(int[] numberOfSpaces, int spacesNeeded) {
        if (Arrays.stream(numberOfSpaces).sum() == spacesNeeded) {
            return new ArrayList<>(Arrays.asList(numberOfSpaces));
        } else {
            ArrayList<int[]> possibilities = new ArrayList<>();

            for (int i = 0; i < numberOfSpaces.length; i++) {
                int[] tempNumberOfSpaces = Arrays.copyOf(numberOfSpaces, numberOfSpaces.length);
                tempNumberOfSpaces[i] = tempNumberOfSpaces[i] + 1;
                possibilities.addAll(findAllPermutationsOfRow(tempNumberOfSpaces, spacesNeeded));
            }

            for (int select = 0; select < possibilities.size(); select++) {
                int[] temp = possibilities.get(select);
                for (int c = select + 1; c < possibilities.size(); c++) {
                    if (Arrays.equals(temp, possibilities.get(c))) {
                        possibilities.remove(c);
                    }
                }
            }
            return possibilities;
        }
    }

    void setSize(int size) {
        this.size = size;
        board = new Board();
        board.setSize(size);
    }

    void printBoard(PrintStream p) {
        p.println(board.toString());
    }
}