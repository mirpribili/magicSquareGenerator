package org.example.magicsquaregenerator.generator;

import lombok.Getter;
import org.example.magicsquaregenerator.exception.MagicSquareGenerationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MagicSquareGeneratorImpl implements MagicSquareGenerator {

    @Getter
    private int attemptCounter = 0;

    private final int minValue;
    private final int maxValue;
    private final int maxAttempts;

    private int[][] solution = null; // для хранения найденного решения

    public MagicSquareGeneratorImpl() {
        this.maxAttempts = 10_000;
        this.minValue = 1;
        this.maxValue = 99;
    }

    public MagicSquareGeneratorImpl(int minValue, int maxValue, int maxAttempts) {
        this.maxAttempts = maxAttempts;
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue должен быть меньше maxValue");
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public int[][] generateMagicSquare(List<Integer> diagonal) {
        if (diagonal == null || diagonal.size() != 3) {
            throw new IllegalArgumentException("Ожидаются ровно 3 числа для диагонали");
        }

        if (diagonal.contains(null)) {
            throw new IllegalArgumentException("Список не должен содержать null значения");
        }

        Set<Integer> unique = new HashSet<>(diagonal);
        if (unique.size() != 3) {
            throw new IllegalArgumentException("Числа диагонали должны быть уникальными");
        }

        int a = diagonal.get(0);
        int b = diagonal.get(1);
        int c = diagonal.get(2);
        int magicSum = a + b + c;
        attemptCounter = 0;
        solution = null;

        int[][] square = new int[3][3];
        square[2][0] = a;
        square[1][1] = b;
        square[0][2] = c;

        Set<Integer> used = new HashSet<>(diagonal);


        Random random = new Random();

        List<Integer> candidatesX = new ArrayList<>();
        for (int i = minValue; i <= maxValue; i++) {
            if (!used.contains(i)) {
                candidatesX.add(i);
            }
        }

        while (attemptCounter < maxAttempts && !candidatesX.isEmpty()) {
            attemptCounter++;

            // Выбираем случайный x из оставшихся кандидатов
            int index = random.nextInt(candidatesX.size());
            int x = candidatesX.get(index);

            int y = magicSum - b - x;
            if (y < minValue || y > maxValue) {
                // убираем x из кандидатов, т.к. y не в диапазоне
                candidatesX.remove(index);
                continue;
            }
            if (y == x || used.contains(y)) {
                candidatesX.remove(index);
                continue;
            }

            square[0][0] = x;
            square[2][2] = y;

            int up = magicSum - square[0][0] - square[0][2]; // сумма по первой строке минус x и a
            int left = magicSum - square[0][0] - square[2][0]; // сумма по первому столбцу минус x и c
            int down = magicSum - square[2][0] - square[2][2]; // сумма по третьей строке минус c и y
            int right = magicSum - square[0][2] - square[2][2]; // сумма по третьему столбцу минус a и y

            square[0][1] = up;
            square[1][0] = left;
            square[2][1] = down;
            square[1][2] = right;
            if (!Arrays.stream(square)
                    .flatMapToInt(Arrays::stream)
                    .anyMatch(value -> value <= 0) && isMagic(square, magicSum)) {
                return square;
            } else {
                candidatesX.remove(index);
            }
        }
        throw new MagicSquareGenerationException("Не удалось сгенерировать магический квадрат с заданной диагональю и диапазоном " + diagonal);
//        throw new RuntimeException("Не удалось сгенерировать магический квадрат с заданной диагональю и диапазоном " + diagonal);
    }

}
