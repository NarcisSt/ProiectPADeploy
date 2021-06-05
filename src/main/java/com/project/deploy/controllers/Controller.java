package com.project.deploy.controllers;

import com.project.deploy.algorithm.Algorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @GetMapping("/")
    public String index(Model model){

        Algorithm algorithm = new Algorithm();
        model.addAttribute("algorithm", algorithm);
        return "algorithm";
    }

    @GetMapping("/algorithm")
    public String algorithmForm(Model model){
        Algorithm algorithm = new Algorithm();
        model.addAttribute("algorithm", algorithm);
        return "algorithm";
    }

    @PostMapping("/algorithm")
    public String algorithmSubmit(@ModelAttribute Algorithm algorithm, Model model) {
        logger.info("\n NEW ADDRESS: ");
        logger.info(algorithm.toString());
        algorithm.start();
        model.addAttribute("algorithm", algorithm);
        logger.info(algorithm.getCorrectAddress().toString());
        return "result";
    }
}
