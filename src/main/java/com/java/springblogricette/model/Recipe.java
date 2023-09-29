package com.java.springblogricette.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Insert the name of your recipe.")
    @Size(max = 255)
    private String name;
    @NotBlank(message = "Insert the list of the ingredients.")
    @Size(max = 600)
    @Column(columnDefinition = "tinytext", length = 600)
    private String ingredients;
    @URL(message = "Insert an url.")
    @NotBlank(message = "Insert web url image.")
    private String image;

    @NotBlank(message = "Insert cooking time. ex. 1hr 20min.")
    @Size(max = 50)
    private String cookingTime;


    @Min(value = 1, message = "Insert a number between 1-20.")
    @Max(value = 20, message = "Insert a number between 1-20.")
    private Integer servings;

    @NotBlank(message = "I don't know how to prepare it. Give me some directions, please.")
    @Size(max = 1000)
    @Column(columnDefinition = "tinytext", length = 1000)
    private String text;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }
}

