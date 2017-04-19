/******************************************
 Programmer: Javier Valerio
 Date Originally Submitted: February 10th, 2017
 Last Modified on: February 20th, 2017
 Dr. Shrivastava
 CS 481 - Menu Ordering System - Part Two
 ******************************************/

package com.example.jvalerio.menuorderingsystem;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    // Instance variables
    private Map<String, MenuItem> menu = new HashMap();

    // Default constructor
    public Menu () {
        populateMenu();
    }


    /* PURPOSE: This method populates the Menu object with the corresponding items data */
    public void populateMenu() {
        MenuItem item = new MenuItem("Bagel", 2.5, 0, R.drawable.bagel_cc, "htt://www.chick-fil-a.com/Menu-Items/Sunflower-Multigrain-Bagel", 3);
        menu.put(item.getName(), item);

        item = new MenuItem("Biscuit", 2.5, 0, R.drawable.biscuit, "http://www.chick-fil-a.com/Menu-Items/Chick-fil-A-Chicken-Biscuit", 2);
        menu.put(item.getName(), item);

        item = new MenuItem("Chicken Sandwich", 4.5, 0, R.drawable.chicken_sandwich, "http://www.chick-fil-a.com/Menu-Items/Chick-fil-A-Chicken-Sandwich", 3);
        menu.put(item.getName(), item);

        item = new MenuItem("Chicken Wrap", 5.25, 0, R.drawable.chicken_wrap, "http://www.chick-fil-a.com/Menu-Items/Grilled-Chicken-Cool-Wrap", 4);
        menu.put(item.getName(), item);

        item = new MenuItem("Chocolate Cookie", 1.5, 0, R.drawable.chocolate_cookie, "http://www.chick-fil-a.com/Menu-Items/Chocolate-Chunk-Cookie", 3);
        menu.put(item.getName(), item);

        item = new MenuItem("Cobb Salad", 7.75, 0, R.drawable.cobb_salad, "http://www.chick-fil-a.com/Menu-Items/Cobb-Salad", 3);
        menu.put(item.getName(), item);

        item = new MenuItem("Coke", 1, 0, R.drawable.coke, "http://www.chick-fil-a.com/Menu-Items/Coca-Cola", 2);
        menu.put(item.getName(), item);

        item = new MenuItem("Deluxe Sandwich", 5.75, 0, R.drawable.deluxe_sandwich, "http://www.chick-fil-a.com/Menu-Items/Chick-fil-A-Deluxe-Sandwich", 5);
        menu.put(item.getName(), item);

        item = new MenuItem("Fruit Cup", 3.0, 0, R.drawable.fruit_cup, "http://www.chick-fil-a.com/Menu-Items/Fruit-Cup", 4);
        menu.put(item.getName(), item);

        item = new MenuItem("Grilled Market Salad", 7.25, 0, R.drawable.grilled_market_salad, "http://www.chick-fil-a.com/Menu-Items/Grilled-Market-Salad", 3);
        menu.put(item.getName(), item);

        item = new MenuItem("Hot Coffee", 1.25, 0, R.drawable.hot_coffee, "hot coffee: http://www.chick-fil-a.com/Menu-Items/Hot-Coffee", 2);
        menu.put(item.getName(), item);

        item = new MenuItem("Iced Coffee", 2.25, 0, R.drawable.iced_coffee, "http://www.chick-fil-a.com/Menu-Items/Frosted-Coffee", 4);
        menu.put(item.getName(), item);

        item = new MenuItem("Lemonade", 1.75, 0, R.drawable.lemonade, "http://www.chick-fil-a.com/Menu-Items/Fresh-Squeezed-Lemonade", 5);
        menu.put(item.getName(), item);

        item = new MenuItem("Milkshake", 2, 0, R.drawable.milkshake, "http://www.chick-fil-a.com/Menu-Items/Chocolate-Milkshake", 2);
        menu.put(item.getName(), item);

        item = new MenuItem("Noodle Soup", 5.25, 0, R.drawable.noodle_soup, "http://www.chick-fil-a.com/Menu-Items/Chicken-Noodle-Soup", 3);
        menu.put(item.getName(), item);

        item = new MenuItem("Tortilla Soup", 4.75, 0, R.drawable.tortilla_soup, "http://www.chick-fil-a.com/Menu-Items/Chicken-Tortilla-Soup", 3);
        menu.put(item.getName(), item);

        item = new MenuItem("Yogurt", 4.15, 0, R.drawable.yogurt, "http://www.chick-fil-a.com/Menu-Items/Greek-Yogurt-Parfait", 4);
        menu.put(item.getName(), item);

    }


    /*  PURPOSE: This method returns the current menu
    */
    public Map<String, MenuItem> getMenu () {
        return menu;
    }

}
