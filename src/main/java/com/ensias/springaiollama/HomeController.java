package com.ensias.springaiollama;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Spring AI Ollama API";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running!";
    }
}
