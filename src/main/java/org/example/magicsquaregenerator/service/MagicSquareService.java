package org.example.magicsquaregenerator.service;

import org.example.magicsquaregenerator.aop.MagicSquareGenerationAspect;
import org.example.magicsquaregenerator.generator.MagicSquareGenerator;
import org.example.magicsquaregenerator.model.MagicSquareModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagicSquareService {

    private final MagicSquareGenerator generator;
    private final MagicSquareGenerationAspect generationAspect;

    public MagicSquareService(MagicSquareGenerator generator, MagicSquareGenerationAspect generationAspect) {
        this.generator = generator;
        this.generationAspect = generationAspect;
    }

    public MagicSquareModel createMagicSquare(List<Integer> numbers) {
        generationAspect.resetCount(); // Сброс перед началом
        int[][] squareArray = generator.generateMagicSquare(numbers);
        if (squareArray == null) return null;

        MagicSquareModel model = new MagicSquareModel(squareArray.length);
        for (int i = 0; i < squareArray.length; i++) {
            for (int j = 0; j < squareArray.length; j++) {
                model.setValue(i, j, squareArray[i][j]);
            }
        }
        return model;
    }

    public int getAttemptCount() {
        //return generationAspect.getAttemptCount();
        return generator.getAttemptCounter();
    }
}

