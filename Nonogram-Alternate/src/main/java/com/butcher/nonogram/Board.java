package com.butcher.nonogram;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {
    private int size;

    private Cell[] cells;

    @Override
    public void setSize(int size) {
        this.size = size;
        cells = new Cell[size * size];
        for (int i = 0; i < size * size; i++) {
            cells[i] = new Cell();
        }
    }

    @Override
    public Cell[] getRow(int rowNum) {
        Cell[] row = new Cell[size];
        for(int i=0; i< size; i++) {
            row[i] = cells[rowNum * size + i];
        }
        return row;
    }

    @Override
    public List<Integer> setRow(int rowNum, Cell[] row) {
        List<Integer> changes = new ArrayList<>();
        for(int i=0; i<size; i++) {
            if(row[i].isConfirmed() && row[i].getValue() != CellValue.UNKNOWN && cells[rowNum*size + i] != row[i]) {
                cells[rowNum * size + i] = row[i];
                changes.add(i);
            }
        }
        return changes;
    }

    @Override
    public Cell[] getCol(int colNum) {
        Cell[] col = new Cell[size];
        for(int i=0; i<size; i++) {
            col[i] = cells[i * size + colNum];
        }
        return col;
    }

    @Override
    public List<Integer> setCol(int colNum, Cell[] col) {
        List<Integer> changes = new ArrayList<>();
        for(int i=0; i<size; i++) {
            if(col[i].isConfirmed() && col[i].getValue() != CellValue.UNKNOWN && cells[i*size + colNum] != col[i]) {
                cells[i * size + colNum] = col[i];
                changes.add(i);
            }
        }
        return changes;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int r=0; r< size; r++) {
            for(int c=0; c< size; c++) {
                s.append(cells[r*size + c].getValue().value);
            }
            s.append("\r\n");
        }
        return s.toString();
    }
}
