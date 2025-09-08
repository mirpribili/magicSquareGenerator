package org.example.magicsquaregenerator.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MagicSquareGenerationAspect {

    private int attemptCount = 0;

    @Pointcut("execution(* org.example.magicsquaregenerator.generator.MagicSquareGenerator.generateMagicSquare(..))")
    public void generateMagicSquareMethod() {}

    @Before("generateMagicSquareMethod()")
    public void beforeGenerate() {
        attemptCount++;
    }

    public void resetCount() {
        attemptCount = 0;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}
