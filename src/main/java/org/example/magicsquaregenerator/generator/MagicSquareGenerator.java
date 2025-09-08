package org.example.magicsquaregenerator.generator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface MagicSquareGenerator {
    /**
     * Генерирует магический квадрат из заданного набора чисел.
     *
     * @param numbers набор чисел для формирования квадрата (например, 3 числа для диагонали)
     * @return двумерный массив с магическим квадратом или null, если квадрат не найден
     */
    int[][] generateMagicSquare(List<Integer> numbers);

    /**
     * Вернет кол-во попыток.
     *
     * @return int
     */
    int getAttemptCounter();

    /**
     * Проверяет, является ли переданный квадрат магическим с заданной суммой.
     *
     * @param square    двумерный массив
     * @param targetSum ожидаемая сумма по строкам, столбцам и диагоналям
     * @return true, если квадрат магический
     */
    default boolean isMagic(int[][] square, int targetSum) {
        Set<Integer> unique = new HashSet<>();

        for (int[] row : square) {
            for (int val : row) {
                unique.add(val);
            }
        }
        if (unique.size() != 9) return false;

        for (int i = 0; i < 3; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < 3; j++) {
                rowSum += square[i][j];
                colSum += square[j][i];
            }
            if (rowSum != targetSum || colSum != targetSum) return false;
        }
        int diag1 = square[0][0] + square[1][1] + square[2][2];
        int diag2 = square[0][2] + square[1][1] + square[2][0];
        return diag1 == targetSum && diag2 == targetSum;
    }
}
