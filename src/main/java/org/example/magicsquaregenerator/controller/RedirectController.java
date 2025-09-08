package org.example.magicsquaregenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToMagicSquare() {
        return "redirect:/swagger-ui/index.html";
    }
}
