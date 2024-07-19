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
import java.util.Collections;
import java.util.List;


/**
 * This class represents a Task.
 */
class Task implements Comparable<Task> {
    public String name;
    public int startTime, endTime;

    /**
     * Initialize Constructor method !
     *
     * @param name      name for task
     * @param startTime int value of start time for task
     * @param endTime   int value of end time for task
     */
    public Task(String name, int startTime, int endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * A method to compare two task with end time !
     *
     * @param other Another task to be compared
     */
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.endTime, other.endTime);
    }

    /**
     * toString method for task !
     */
    @Override
    public String toString() {
        return name + " (" + startTime + "-" + endTime + ")";
    }


}

/**
 * This class represents Task Scheduling Problem.
 */
class TaskScheduler {
    static List<Task> tasks = new ArrayList<>();
    static String results = "";

    static String taskSchedule() {
        Collections.sort(tasks);

        List<Task> schedule = new ArrayList<>();
        schedule.add(tasks.get(0));

        for (int i = 1; i < tasks.size(); i++) {
            if (tasks.get(i).startTime >= schedule.get(schedule.size() - 1).endTime) {
                schedule.add(tasks.get(i));
            }
        }

        results = "";
        for (Task sch: schedule)
            results += sch.name + ", ";

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
        stage.setTitle("TSPS !");
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




