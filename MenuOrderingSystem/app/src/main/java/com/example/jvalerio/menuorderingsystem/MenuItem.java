/******************************************
 Programmer: Javier Valerio
 Date Originally Submitted: February 10th, 2017
 Last Modified on: February 20th, 2017
 Dr. Shrivastava
 CS 481 - Menu Ordering System - Part Two
 ******************************************/

package com.example.jvalerio.menuorderingsystem;

import java.io.Serializable;

public class MenuItem implements Serializable {

    // Instance variables
    private String name;
    private double price;
    private int quantity;
    private int imageURL;
    private String size;
    private int rating;
    private String nutritionFacts;

    private double MEDIUM = 1.5;
    private double LARGE = 2.25;

    // Default constructor
    public MenuItem () {
        name = "";
        price = 0.0;
        quantity = 0;
        imageURL = 0;
        rating = 0;
        size = "Small";
        nutritionFacts = null;
    }

    /* Overloaded constructor which takes a string representing the name of the item, a double representing item's price,
       and two integers: one for the quantity and one representing the index of the item's image
    */
    public MenuItem (String name, double price, int quantity, int imageURL, String nutritionFacts, int rating) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.size = "Small";
        this.nutritionFacts = nutritionFacts;
        this.rating = rating;
    }


    // Getters
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity () {
        return quantity;
    }
    public int getImageURL () {
        return imageURL;
    }
    public String getSize () {
        return size;
    }
    public String getNutritionFactsLink () { return nutritionFacts; }
    public int getRating () { return rating; }

    // Setters
    public void setName(String newName) {
        name = newName;
    }
    public void setPrice (double newPrice) {
        price = newPrice;
    }
    public void setQuantity (int newQuantity) {
        quantity = newQuantity;
    }
    public void setImageURL (int newURL) {
        imageURL = newURL;
    }
    public void setSize (String newSize) {
        size = newSize;

        if (size.equals("Medium")) {
            price += MEDIUM;
        }
        else if (size.equals("Large")) {
            price += LARGE;
        }
    }
    public void setNutritionFactsLink (String newLink) {
        nutritionFacts = newLink;
    }
    public void setRating (int newRating) {
        rating = newRating;
    }
}
