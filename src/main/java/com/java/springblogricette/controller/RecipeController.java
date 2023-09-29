package com.java.springblogricette.controller;

import com.java.springblogricette.model.Recipe;
import com.java.springblogricette.repository.RecipeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping
    public String index(Model model, @RequestParam("keyword") Optional<String> searchKeyword) {
        List<Recipe> recipeList;
        String keyword = "";

        if (searchKeyword.isPresent()) {
            keyword = searchKeyword.get();
            recipeList = recipeRepository.findByNameContaining(keyword);
        } else {
            recipeList = recipeRepository.findAll();
        }

        model.addAttribute("recipe", recipeList);
        model.addAttribute("keyword", keyword);

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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("recipe", new Recipe());

        return "recipes/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("recipe") Recipe formRecipe,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "recipes/form";

        }

        recipeRepository.save(formRecipe);

        return "redirect:/recipes";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Recipe> recipeObj = recipeRepository.findById(id);

        if (recipeObj.isPresent()) {
            model.addAttribute("recipe", recipeObj.get());
            return "recipes/form";
        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping("/edit/{id}")
    public String doEdit(@Valid @ModelAttribute("recipe") Recipe formRecipe, @PathVariable("id")
    Integer id, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "recipes/form";

        }

        recipeRepository.save(formRecipe);

        return "redirect:/recipes";

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        recipeRepository.deleteById(id);

        return "redirect:/recipes";
    }


}


