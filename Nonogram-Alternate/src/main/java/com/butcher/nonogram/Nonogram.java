package com.butcher.nonogram;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class Nonogram {
    public static void main(String[] args) {
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("input.json")){
            NonogramSolver solver = new ObjectMapper().readValue(in, NonogramSolver.class);
            solver.getBoard().setRow(3, test());
            solver.solve();
            solver.printBoard(System.out);

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static Cell[] test() {
        Cell[] row = new Cell[15];
        for (int i = 0; i < row.length; i++) {
            row[i] = new Cell();
        }
        row[3].setValue(CellValue.FILLED);
        row[3].setConfirmed(true);
        return row;
    }
}
