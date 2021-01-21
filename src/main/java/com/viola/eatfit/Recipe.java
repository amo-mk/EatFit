package com.viola.eatfit;

public class Recipe {
    String name;
    String ingredients;
    String cookingProcedure;
    String calorieAmount;
    String fatsAmount;
    String vitaminsAmount;

    public Recipe(String name, String ingredients, String cookingProcedure, String calorieAmount, String fatsAmount, String vitaminsAmount) {
        this.name = name;
        this.ingredients = ingredients;
        this.cookingProcedure = cookingProcedure;
        this.calorieAmount = calorieAmount;
        this.fatsAmount = fatsAmount;
        this.vitaminsAmount = vitaminsAmount;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getCookingProcedure() {
        return cookingProcedure;
    }

    public String getCalorieAmount() {
        return calorieAmount;
    }

    public String getFatsAmount() {
        return fatsAmount;
    }

    public String getVitaminsAmount() {
        return vitaminsAmount;
    }
}