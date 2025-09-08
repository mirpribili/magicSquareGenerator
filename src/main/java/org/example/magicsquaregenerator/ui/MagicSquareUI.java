package org.example.magicsquaregenerator.ui;

import org.example.magicsquaregenerator.model.MagicSquareModel;
import org.example.magicsquaregenerator.service.MagicSquareService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MagicSquareUI extends JFrame {

    private final MagicSquareService magicSquareService;
    private final List<JTextField> inputFields = new ArrayList<>();
    private final JTextArea outputArea = new JTextArea(10, 20);

    public MagicSquareUI(MagicSquareService service) {
        this.magicSquareService = service;
        setTitle("Генератор магического квадрата 3x3 (диагональ из 3 чисел)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        int[] defaultValues = {13, 25, 37};
//        int[] defaultValues = {34, 26, 18};
        for (int i = 0; i < 3; i++) {
            JTextField field = new JTextField(String.valueOf(defaultValues[i]));
            inputFields.add(field);
            inputPanel.add(field);
        }

        JButton generateButton = new JButton("Сгенерировать");
        generateButton.addActionListener(this::onGenerate);

        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(generateButton, BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.EAST);

        setContentPane(mainPanel);
        setPreferredSize(new Dimension(400, 250));  // задаем предпочитаемые размеры окна
        pack();  // адаптируем размер окна под содержимое и предпочтения
        setLocationRelativeTo(null);  // размещаем окно по центру экрана
        setVisible(true);
    }

    private void onGenerate(ActionEvent event) {
        List<Integer> numbers = new ArrayList<>();
        try {
            for (JTextField field : inputFields) {
                int num = Integer.parseInt(field.getText().trim());
                numbers.add(num);
            }
            if (numbers.size() != 3) {
                outputArea.setText("Введите ровно 3 числа для диагонали.");
                return;
            }
            MagicSquareModel square = magicSquareService.createMagicSquare(numbers);
            int attempts = magicSquareService.getAttemptCount();

            outputArea.setText("Попыток генерации: " + attempts + "\n");
            if (square == null) {
                outputArea.append("Не удалось построить магический квадрат с этими числами.");
            } else {
                outputArea.append("Результат:\n" + square.toString());
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Ошибка: Введите корректные целые числа.");
        }
    }
}
