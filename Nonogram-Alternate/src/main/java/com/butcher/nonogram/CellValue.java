package com.butcher.nonogram;

public enum CellValue {
    FILLED(" ■ "),
    OPEN(" Γ ");

    public final String value;

    private CellValue(String value) {
        this.value = value;
    }
}
