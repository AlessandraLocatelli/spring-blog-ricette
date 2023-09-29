package com.java.springblogricette.controller;

import com.java.springblogricette.model.Category;
import com.java.springblogricette.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("categoryObj", new Category());

        return "categories/index";

    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("categoryObj") Category categoryForm) {
        categoryRepository.save(categoryForm);

        return "redirect:/categories";


    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        categoryRepository.deleteById(id);

        return "redirect:/categories";

    }


}
