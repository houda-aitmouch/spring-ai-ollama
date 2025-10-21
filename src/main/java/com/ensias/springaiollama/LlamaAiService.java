package com.ensias.springaiollama;


import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LlamaAiService {

    @Autowired
    private OllamaChatModel chatModel;

    @Autowired
    private ModuleRepository moduleRepo;

    public String generateResult(String prompt) {
        ChatResponse response = chatModel.call(
                new Prompt(prompt, OllamaOptions.builder()
                        .model(OllamaModel.LLAMA3)
                        .temperature(0.4)
                        .build())
        );
        return response.getResult().getOutput().getText();
    }

    public String summarizeModule(Module module) {
        String prompt = "Fais un résumé pédagogique de ce module : " + module.getNom() + " - " + module.getDescription();
        ChatResponse response = chatModel.call(new Prompt(prompt, OllamaOptions.builder().model(OllamaModel.LLAMA3).build()));
        return response.getResult().getOutput().getText();
    }

    public String askModuleQuestion(Module module, String question) {
        String prompt = String.format(
                "Tu es un assistant pédagogique.\nModule : %s - %s\nRéponds à la question : %s",
                module.getNom(), module.getDescription(), question
        );
        ChatResponse response = chatModel.call(new Prompt(prompt, OllamaOptions.builder().model(OllamaModel.LLAMA3).build()));
        return response.getResult().getOutput().getText();
    }

    public String generateQuizForModule(Module module) {
        String prompt = String.format(
                "Crée un petit quiz basé sur le module : %s - %s\n3 questions à choix multiples avec 3 options et la bonne réponse.",
                module.getNom(), module.getDescription()
        );
        ChatResponse response = chatModel.call(new Prompt(prompt, OllamaOptions.builder().model(OllamaModel.LLAMA3).build()));
        return response.getResult().getOutput().getText();
    }

    public String generateQuizWithDifficulty(Module module, String difficulty) {
        String prompt = String.format(
                "Crée un quiz de difficulté %s pour le module : %s - %s\n3 questions à choix multiples avec 3 options et la bonne réponse.",
                difficulty, module.getNom(), module.getDescription()
        );
        ChatResponse response = chatModel.call(new Prompt(prompt, OllamaOptions.builder().model(OllamaModel.LLAMA3).build()));
        return response.getResult().getOutput().getText();
    }

    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        moduleRepo.findAll().forEach(modules::add);
        return modules;
    }

    public Optional<Module> getModule(Integer id) {
        return moduleRepo.findById(id);
    }

    public void ajouterModule(Module module) {
        moduleRepo.save(module);
    }
}