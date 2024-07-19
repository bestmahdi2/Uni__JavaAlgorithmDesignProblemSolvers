/**
 * Controller Class File
 *
 * @author Ali Badiee
 * @version 1.0
 */

// packages
package com.ali.badiee.hw2_2;

// libraries

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class represents a Node.
 */
class Node {
    public int weight;

    /**
     * Initialize Constructor method !
     *
     * @param weight name for node
     */
    public Node(int weight) {
        this.weight = weight;
    }

    /**
     * toString method for task !
     */
    @Override
    public String toString() {
        return weight + "";
    }

}

/**
 * This class represents Task Subset Problem.
 */
class SubsetProblem {
    static List<Node> nodes = new ArrayList<>();
    static int[] weights;
    static String mainPrefix = "";
    static StringBuilder results = new StringBuilder();

    // prints subset found
    static void saveSubset(int[] A, int size) {
        StringBuilder result = new StringBuilder("{");
        String prefix = "";

        for (int i = 0; i < size; i++) {
            result.append(prefix).append(A[i]);
            prefix = ", ";
        }

        result.append("}");

        results.append(mainPrefix).append(result);
        mainPrefix = " - ";
    }

    // inputs
    // s		 - set vector
    // t		 - tuplet vector
    // s_size	 - set size
    // t_size	 - tuplet size so far
    // sum		 - sum so far
    // ite		 - nodes count
    // target_sum - sum to be found
    static void subset_sum(int[] s, int[] t, int s_size, int t_size, int sum, int ite, int target_sum) {
        if (target_sum == sum) {

            // We found sum
            saveSubset(t, t_size);

            // constraint check
            if (ite + 1 < s_size && sum - s[ite] + s[ite + 1] <= target_sum) {

                // Exclude previous added item and consider next candidate
                subset_sum(s, t, s_size, t_size - 1, sum - s[ite], ite + 1, target_sum);
            }

        } else {

            // constraint check
            if (ite < s_size && sum + s[ite] <= target_sum) {

                // generate nodes along the breadth
                for (int i = ite; i < s_size; i++) {

                    t[t_size] = s[i];
                    if (sum + s[i] <= target_sum) {

                        // consider next level node (along depth)
                        subset_sum(s, t, s_size, t_size + 1, sum + s[i], i + 1, target_sum);
                    }
                }
            }
        }
    }

    // Wrapper that prints subsets that sum to target_sum
    static void generateSubsets(int[] s, int size, int target_sum) {
        int[] tuplet_vector = new int[size];
        int total = 0;

        // sort the set
        Arrays.sort(s);

        for (int i = 0; i < size; i++) {
            total = total + s[i];
        }

        if (s[0] <= target_sum && total >= target_sum) {
            subset_sum(s, tuplet_vector, size, 0, 0, 0, target_sum);
        }
    }

    public static StringBuilder subSetProblem(int target) {
        weights = new int[nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
            weights[i] = nodes.get(i).weight;
        }

        int size = weights.length;
        generateSubsets(weights, size, target);

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
        FXMLLoader fxmlLoader = new FXMLLoader(HW2Application.class.getResource("hw2_2.fxml"));

        // create and set scene
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("SSPBS !");
        stage.setScene(scene);

        // add icon to program
        stage.getIcons().add(new Image(HW2Application.class.getResource("icon.png").openStream()));
        stage.show();
    }

    /**
     * The main method
     */
    public static void main1(String[] args) {
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




