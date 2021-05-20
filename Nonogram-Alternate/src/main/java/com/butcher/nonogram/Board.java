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
    public void setRow(int rowNum, Cell[] row) {
        for(int i=0; i<size; i++) {
            if(cells[rowNum*size + i] != row[i]) {
                cells[rowNum * size + i] = row[i];
            }
        }
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
    public void setCol(int colNum, Cell[] col) {
        for (int i = 0; i < size; i++) {
            if (cells[i * size + colNum] != col[i]) {
                cells[i * size + colNum] = col[i];
            }
        }
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
