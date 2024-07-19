/**
 * Controller Class File
 *
 * @author Ali Badiee
 * @version 1.0
 */

// packages
package com.ali.badiee.hw2_3;

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
 * This class represents a Job.
 */
class Job {
    public int taskId, deadline, profit;

    /**
     * Initialize Constructor method by taskID, deadline and profit
     *
     * @param taskId   id for task
     * @param deadline int value of deadline time
     * @param profit   int value of profit
     */
    public Job(int taskId, int deadline, int profit) {
        this.taskId = taskId;
        this.deadline = deadline;
        this.profit = profit;
    }
}

/**
 * This class represents Job Sequencing Problem with Deadlines.
 */
class JobSequencingDeadlines {
    // items array list for keeping all items
    static ArrayList<Job> jobs = new ArrayList<>();
    static String results;

    /**
     * Method to schedule jobs to maximize profit
     *
     * @param maxDeadline int value of maximum deadline that can be associated with a job
     * @return profit int value
     */
    public static int scheduleJobs(int maxDeadline) {
        // stores the maximum profit that can be earned by scheduling jobs
        int profit = 0;

        // array to store used and unused slots info
        int[] slot = new int[maxDeadline];
        Arrays.fill(slot, -1);

        // arrange the jobs in decreasing order of their profits
        jobs.sort((a, b) -> b.profit - a.profit);

        // consider each job in decreasing order of their profits
        for (Job job : jobs) {
            // search for the next free slot and map the task to that slot
            for (int j = job.deadline - 1; j >= 0; j--) {
                if (j < maxDeadline && slot[j] == -1) {
                    slot[j] = job.taskId;
                    profit += job.profit;
                    break;
                }
            }
        }

        results = Arrays.stream(slot).filter(val -> val != -1).boxed().toList().toString();

        return profit;
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
        FXMLLoader fxmlLoader = new FXMLLoader(HW2Application.class.getResource("hw2_3.fxml"));

        // create and set scene
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("JSPDS !");
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




