package com.butcher.nonogram;

import java.util.ArrayList;
import java.util.Arrays;

public class Heuristics {

    public static boolean isLineComplete(Cell[] row) {
        for(Cell cell : row) {
            if(cell.getValue() == CellValue.UNKNOWN) return false;
        }
        return true;
    }



    public static Cell[] valueSumEqualToGridSize(int[] values, Cell[] row) {
        Cell[] solution = row;
        int i=0;
        for(int value : values) {
            for(int count = 0; count < value; count++) {
                solution[i].setValue(CellValue.FILLED);
                //solution[i].setConfirmed(true);
                i++;
            }
            if(i < solution.length) {
                solution[i].setValue(CellValue.OPEN);
                //solution[i].setConfirmed(true);
                i++;
            }
        }
        return solution;
    }

    public static Cell[] valueConfirmedOnEdge(int[] values, Cell[] row) {
        Cell[] solution = row;
        if(row[0].getValue() == CellValue.FILLED) {
            int i;
            for(i = 1; i < values[0]; i++) {
                if(row[i].getValue() != CellValue.FILLED) {
                    row[i].setValue(CellValue.FILLED);
//                    row[i].setConfirmed(true);
                }
            }
            row[i].setValue(CellValue.OPEN);
        } else if(row[row.length].getValue() == CellValue.FILLED && !row[row.length].isConfirmed()) {
            int i;
            for(i = 0; i < values[values.length]; i++) {
                if(row[row.length-i].getValue() != CellValue.FILLED) {
                    row[row.length-i].setValue(CellValue.FILLED);
//                    row[row.length-i].setConfirmed(true);
                }
            }
        }
        return solution;
    }

    public static Cell[] valueMoreThanHalf(int[] values, Cell[] row) {
        Cell[] solution = row;
        for(int value : values) {
            int previousFilledOrOpenValues = 0;
            for(Cell cell : row) {
                if(cell.getValue() == CellValue.FILLED || cell.getValue() == CellValue.OPEN) { previousFilledOrOpenValues++; }
                else { break; }
            }
            if(previousFilledOrOpenValues + value > row.length/2) {
                int start = previousFilledOrOpenValues + value-1;
                int end = row.length - value-1;
                for(int i = start; i > end; i--) {
                    solution[i].setValue(CellValue.FILLED);
                }
            }
        }
        return solution;
    }

    // TODO
    // Fills in OPEN for impossible unknowns.
    public static Cell[] valueOpenFind(int[] values, Cell[] row) {
        Cell[] solution = row;
        int valueSum = 0;
        for(int value : values) {
            //plus one for the blank ahead of it
            valueSum = valueSum + value + 1;
        }

        for(int value : values) {
            for(int i = 0; i < row.length; i++) {
                if(row[i].getValue() == CellValue.UNKNOWN) {
                    int j = i;
                    ArrayList<Integer> counts = new ArrayList();
                    while(row[j].getValue() == CellValue.UNKNOWN) {
                        counts.add(j);
                        j++;
                    }
                    // OPEN values if it is not a possible slot for said value instead of entire board.
                    // TODO: Currently works with beginning and ending values, not anything in-between.
                    // valuesBefore < currentValue < ValuesAfter
                    if(counts.size() < value && i < (valueSum - value)) {
                        for(int index : counts) {
                            solution[index].setValue(CellValue.OPEN);
                        }
                    }
                    i = i + j;
                }
            }
        }
        return solution;
    }
}
