package com.bw.blobboundary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anatoly Chernysh
 */
public class Blob {

    private int [][]cells;

    private int right;

    private int left;

    private int top;

    private int bottom;

    private int readCount;

    private List<Cell> visitedCells = new ArrayList<>();

    public Blob(int left, int top, int readCount, int [][]cells) {
        this.left = left;
        this.top = top;
        this.right = -1;
        this.bottom = -1;
        this.readCount = readCount;
        this.cells = cells;
        addVisitedCell(left, top);
    }

    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public List<Cell> getVisitedCells() {
        return visitedCells;
    }

    public void setVisitedCells(List<Cell> visitedCells) {
        this.visitedCells = visitedCells;
    }

    public boolean isVisitedCell(int x, int y) {
        return visitedCells.stream().filter(cell -> cell.getX() == x && cell.getY() == y).findAny().isPresent();
    }

    public void addVisitedCell(int x, int y) {
        visitedCells.add(new Cell(x, y));
    }

    public void incrementReadCount() {
        this.readCount++;
    }

    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer();
        result.append("Cell Reads: ").append(readCount).append("\r\n");
        result.append("Top: ").append(top).append("\r\n");
        result.append("Left: ").append(left).append("\r\n");
        result.append("Bottom: ").append(bottom).append("\r\n");
        result.append("Right: ").append(right).append("\r\n");
        result.append("Visited cells:\r\n");
        for (int i = 0;i < cells.length;i++) {
            for (int j = 0;j < cells.length;j++) {
                result.append(isVisitedCell(i, j) ? "1 " : "0 ");
            }
            result.append("\r\n");
        }

        return result.toString();
    }
}
