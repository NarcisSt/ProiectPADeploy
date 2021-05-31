package com.project.deploy.controllers;

import com.project.deploy.algorithm.Algorithm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public void index(Model model){
        algorithmForm(model);
    }

    @GetMapping("/algorithm")
    public String algorithmForm(Model model){
        Algorithm algorithm = new Algorithm();
        model.addAttribute("algorithm", algorithm);
        return "algorithm";
    }

    @PostMapping("/algorithm")
    public String algorithmSubmit(@ModelAttribute Algorithm algorithm, Model model) {
        algorithm.start();
        model.addAttribute("algorithm", algorithm);
        return "result";
    }
}
