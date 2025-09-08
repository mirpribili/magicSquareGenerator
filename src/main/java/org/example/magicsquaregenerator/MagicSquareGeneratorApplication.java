package org.example.magicsquaregenerator;

import org.example.magicsquaregenerator.ui.MagicSquareUI;
import org.example.magicsquaregenerator.service.MagicSquareService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class MagicSquareGeneratorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MagicSquareGeneratorApplication.class)
				.headless(false) // Важно: отключаем headless режим
				.run(args);

		SwingUtilities.invokeLater(() -> {
			MagicSquareService service = ctx.getBean(MagicSquareService.class);
			new MagicSquareUI(service);
		});
	}
}
