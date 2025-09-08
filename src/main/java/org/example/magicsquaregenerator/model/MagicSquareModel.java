package org.example.magicsquaregenerator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MagicSquareModel {
    private int size;
    private int[][] square;

    public MagicSquareModel(int size) {
        this.size = size;
        this.square = new int[size][size];
    }

    public void setValue(int row, int col, int value) {
        square[row][col] = value;
    }

    public int getValue(int row, int col) {
        return square[row][col];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(String.format("%4d", square[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
