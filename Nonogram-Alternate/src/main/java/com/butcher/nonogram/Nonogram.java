package com.butcher.nonogram;

public class Nonogram {
    public static void main(String[] args) throws Exception {
        NonogramSolver solver = new NonogramSolver();
        solver.setSize(10);
        solver.setCols(new int[][] {{7,1}, {6,1}, {6}, {4,1}, {2,1,1}, {5,1}, {5}, {3,1}, {2,1}, {2}});
        solver.setRows(new int[][] {{5,1}, {6,1}, {4,1}, {4,2}, {3,2}, {3,4}, {1,2}, {3}, {1}, {2,3,1,1}});
        solver.solve();
    }
}