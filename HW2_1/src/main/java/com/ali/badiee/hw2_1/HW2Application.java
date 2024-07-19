/**
 * Controller Class File
 *
 * @author Ali Badiee
 * @version 1.0
 */

// packages
package com.ali.badiee.hw2_1;

// libraries
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents an item.
 */
class Item {
    int value, weight;

    /**
     * Initialize Constructor method by name and id
     *
     * @param value integer numeric value of the item
     * @param weight int value of weight of the item
     */
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

/**
 * This class represents Fractional Knapsack.
 */
class FractionalKnapsack {
    // items array list for keeping all items
    static ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Method to find the maximum value
     *
     * @param capacity int value of capacity of the knapsack
     */
    static double getMaxValue(int capacity) {
        Item[] arr = items.toArray(new Item[0]);

        // Sorting items by value/weight ratio
        Arrays.sort(arr, (item1, item2) -> {
            double cpr1 = (double) item1.value / (double) item1.weight;
            double cpr2 = (double) item2.value / (double) item2.weight;

            return Double.compare(cpr2, cpr1);
        });

        double totalValue = 0d;

        for (Item i : arr) {
            if (capacity - i.weight >= 0) {
                // This weight can be picked whole
                capacity -= i.weight;
                totalValue += i.value;

            } else {
                // Item cant be picked whole
                double fraction = ((double) capacity / (double) i.weight);
                totalValue += (i.value * fraction);
                break;
            }
        }

        return totalValue;
    }
}

/**
 * Main Class
 */
public class HW2Application extends Application {
    /**
     * The start method
     *
     * @param stage -
     */
    @Override
    public void start(Stage stage) throws IOException {
        // fxml loader
        FXMLLoader fxmlLoader = new FXMLLoader(HW2Application.class.getResource("hw2_1.fxml"));

        // create and set scene
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("FKPS !");
        stage.setScene(scene);

        // add icon to program
        stage.getIcons().add(new Image(HW2Application.class.getResource("icon.png").openStream()));
        stage.show();
    }

    /**
     * The main method
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * A method to figure out if a string is all number or not
     *
     * @param strNum String value of number to be checked
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}