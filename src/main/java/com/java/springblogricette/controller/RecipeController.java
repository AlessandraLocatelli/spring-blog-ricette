package com.java.springblogricette.controller;

import com.java.springblogricette.model.Recipe;
import com.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping
    public String index(Model model) {
        List<Recipe> recipeList = recipeRepository.findAll();
        model.addAttribute("recipe", recipeList);
        return "recipes/index";
    }

    @GetMapping("/show/{recipeId}")
    public String show(@PathVariable("recipeId") Integer id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipeFromDB = recipeOptional.get();
            model.addAttribute("recipe", recipeFromDB);
            return "recipes/details";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


}


