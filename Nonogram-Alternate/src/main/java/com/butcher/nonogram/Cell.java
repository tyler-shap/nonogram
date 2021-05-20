package com.butcher.nonogram;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private CellValue value;

    public Cell() {
        value = CellValue.OPEN;
    }

    @Override
    public String toString() {
        return value.value;
    }
}
