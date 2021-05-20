package com.butcher.nonogram;

import java.util.List;

public interface IBoard {
    void setSize(int size);

    Cell[] getRow(int rowNum);

    void setRow(int rowNum, Cell[] row);

    Cell[] getCol(int colNum);

    void setCol(int colNum, Cell[] col);

    @Override
    String toString();
}
