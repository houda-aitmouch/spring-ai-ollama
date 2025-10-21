package com.ensias.springaiollama;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modules")
public class AIController {

    @Autowired
    private LlamaAiService llamaAiService;

    @GetMapping("/all")
    public List<Module> getModules() {
        return llamaAiService.getAllModules();
    }

    @GetMapping("/{id}")
    public Optional<Module> getModule(@PathVariable Integer id){
        return llamaAiService.getModule(id);
    }

    @PostMapping
    public void ajouterModule(@RequestBody Module module) {
        llamaAiService.ajouterModule(module);
    }

    @GetMapping("/{id}/summary")
    public String summarizeModule(@PathVariable Integer id) {
        Module module = llamaAiService.getModule(id).orElseThrow(() -> new RuntimeException("Module non trouvé"));
        return llamaAiService.summarizeModule(module);
    }

    @GetMapping("/{id}/quiz")
    public String generateQuiz(@PathVariable Integer id) {
        Module module = llamaAiService.getModule(id).orElseThrow(() -> new RuntimeException("Module non trouvé"));
        return llamaAiService.generateQuizForModule(module);
    }

    @GetMapping("/{id}/quizD")
    public String generateQuizWithDifficulty(@PathVariable Integer id, @RequestParam(defaultValue = "Facile") String difficulty) {
        Module module = llamaAiService.getModule(id).orElseThrow(() -> new RuntimeException("Module non trouvé"));
        return llamaAiService.generateQuizWithDifficulty(module, difficulty);
    }

    @PostMapping("/{id}/ask")
    public String askQuestion(@PathVariable Integer id, @RequestBody String question) {
        Module module = llamaAiService.getModule(id).orElseThrow(() -> new RuntimeException("Module non trouvé"));
        return llamaAiService.askModuleQuestion(module, question);
    }
}