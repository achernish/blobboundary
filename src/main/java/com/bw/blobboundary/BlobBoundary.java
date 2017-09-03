package com.bw.blobboundary;

/**
 * Just for fun.
 *
 * @author Anatoly Chernysh
 */
public class BlobBoundary {

    public static String TEXT_BLOB = "0000000000\n" +
            "0011100000\n" +
            "0011111000\n" +
            "0010001000\n" +
            "0011111000\n" +
            "0000101000\n" +
            "0000101000\n" +
            "0000111000\n" +
            "0000000000\n" +
            "0000000000";

    public static void main(String[] args) {
        String []blobRows = TEXT_BLOB.split("\n");
        int width = blobRows[0].length();
        int height = blobRows.length;

        int [][]cells = new int[width][height];
        for (int i = 0;i < cells.length;i++) {
            cells[i] = new int[blobRows[i].length()];
            for (int j = 0;j < cells[i].length;j++) {
                cells[i][j] = blobRows[i].charAt(j) == '0' ? 0 : 1;
            }
        }

        BlobBoundary blobBoundary = new BlobBoundary();
        Blob blob = blobBoundary.findBlob(cells);
        System.out.println(blob);
    }

    public Blob findBlob(int [][]cells) {
        for (int i = 0;i < cells.length;i++) {
            for (int j = 0;j < cells[i].length;j++) {
                if (cells[i][j] == 1) {
                    Blob blob = new Blob(i, j, ((i + 1) * cells.length + j + 1), cells);
                    findBlobHelper(blob, i, j);
                    return blob;
                }
            }
        }

        return null;
    }

    private void findBlobHelper(Blob blob, int x, int y) {
        if ((x == blob.getLeft() && y == blob.getTop()) && (blob.getBottom() != -1 && blob.getRight() != -1)) {
            // blob is found
            return;
        }

        // update top and bottom
        if (x > blob.getRight() && y == blob.getBottom()) {
            blob.setRight(x);
        }
        if (x == blob.getRight() && y > blob.getBottom()) {
            blob.setBottom(y);
        }
        if (x > blob.getRight() && y > blob.getBottom()) {
            blob.setRight(x);
            blob.setBottom(y);
        }

        // go left
        if (x - 1 >= 0 && blob.getCells()[x - 1][y] == 1 && !blob.isVisitedCell(x - 1, y)) {
            blob.incrementReadCount();
            blob.addVisitedCell(x - 1, y);
            findBlobHelper(blob, x - 1, y);
        }

        // go down
        if (y + 1 < blob.getCells().length && blob.getCells()[x][y + 1] == 1 && !blob.isVisitedCell(x, y + 1)) {
            blob.incrementReadCount();
            blob.addVisitedCell(x, y + 1);
            findBlobHelper(blob, x, y + 1);
        }

        // go right
        if (x + 1 <= blob.getCells()[x].length && blob.getCells()[x + 1][y] == 1 && !blob.isVisitedCell(x + 1, y)) {
            blob.incrementReadCount();
            blob.addVisitedCell(x + 1, y);
            findBlobHelper(blob, x + 1, y);
        }

        // go up
        if (y - 1 >= 0 && blob.getCells()[x][y - 1] == 1 && !blob.isVisitedCell(x, y - 1)) {
            blob.incrementReadCount();
            blob.addVisitedCell(x, y - 1);
            findBlobHelper(blob, x, y - 1);
        }
    }
}