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
     * @param value  integer numeric value of the item
     * @param weight int value of weight of the item
     */
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class Knapsack {
    static ArrayList<Item> items = new ArrayList<Item>();
    static int[] weights; // Weights of the items
    static int[] values; // Values of the items
//    static int n = items.size(); // Number of items

    static int maxProfit;
    static int[] solution ;

    public static void knapsack(int i, int weight, int profit, int[] selected, int capacity) {
        if (i == items.size()) {
            if (profit > maxProfit && weight <= capacity) {
                maxProfit = profit;
                solution = Arrays.copyOf(selected, items.size());
            }
            return;
        }

        selected[i] = 0;
        knapsack(i + 1, weight, profit, selected, capacity);

        selected[i] = 1;
        knapsack(i + 1, weight + weights[i], profit + values[i], selected, capacity);
    }

    static StringBuilder knapsack_find(int capacity) {
        weights = new int[items.size()];
        values = new int[items.size()];
        solution = new int[items.size()];

        for (int i = 0; i < items.size(); i++){
            weights[i] = items.get(i).weight;
            values[i] = items.get(i).value;
        }

        knapsack(0, 0, 0, new int[items.size()], capacity);

        StringBuilder result = new StringBuilder();
        StringBuilder results = new StringBuilder();
        results.append("Maximum Profit: [ ").append(maxProfit).append(" ]");

        for (int i = 0; i < solution.length; i++)
            if (solution[i] == 1)
                result.append(weights[i]).append(", ");

        results.append(" - Solution: [").append(result.substring(0, result.length() - 2)).append("]");

        return results;
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
        stage.setTitle("KPB !");
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