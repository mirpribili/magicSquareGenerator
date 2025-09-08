package org.example.magicsquaregenerator.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.magicsquaregenerator.validation.UniqueListElements;

import java.util.List;

public class DiagonalRequest {

    @NotNull(message = "Список чисел не может быть пустым")
    @Size(min = 3, max = 3, message = "Требуется ровно 3 числа")
    @UniqueListElements(message = "Числа должны быть уникальными")
    @Schema(
            description = "Список из 3 чисел для диагонали",
            example = "[17, 26, 35]"
    )
    @ArraySchema(schema = @Schema(type = "integer"), minItems = 3, maxItems = 3, uniqueItems = true)
    private List<Integer> numbers;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
