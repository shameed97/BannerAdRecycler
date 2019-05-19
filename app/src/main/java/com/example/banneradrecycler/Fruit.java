package com.example.banneradrecycler;

public class Fruit {

    private String fruitName,fruitCalories;

    public Fruit(String fruitName, String fruitCalories) {
        this.fruitName = fruitName;
        this.fruitCalories = fruitCalories;
    }

    public String getFruitName() {
        return fruitName;
    }

    public String getFruitCalories() {
        return fruitCalories;
    }
}
