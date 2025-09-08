package org.example.magicsquaregenerator.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.example.magicsquaregenerator.dto.DiagonalRequest;
import org.example.magicsquaregenerator.model.MagicSquareModel;
import org.example.magicsquaregenerator.service.MagicSquareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/magicsquare")
public class MagicSquareController {

    private final MagicSquareService magicSquareService;

    public MagicSquareController(MagicSquareService magicSquareService) {
        this.magicSquareService = magicSquareService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateMagicSquare(@Valid @RequestBody DiagonalRequest request) {
        List<Integer> numbers = request.getNumbers(); // тут null
        MagicSquareModel model = magicSquareService.createMagicSquare(numbers);
        int attempts = magicSquareService.getAttemptCount();
        return ResponseEntity.ok(Map.of(
                "square", model,
                "attempts", attempts
        ));
    }
}
