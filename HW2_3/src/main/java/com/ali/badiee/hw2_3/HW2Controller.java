/**
 * Controller Class File
 *
 * @author Ali Badiee
 * @version 1.0
 */

// packages
package com.ali.badiee.hw2_3;

// libraries

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

// used classes
import static com.ali.badiee.hw2_3.HW2Application.isNumeric;
import static com.ali.badiee.hw2_3.JobSequencingDeadlines.*;

/**
 * This class represents HW2Controller.
 */
public class HW2Controller {
    @FXML
    private Button AddButton;

    @FXML
    private Button DelButton;

    @FXML
    private Button calculateButton;

    @FXML
    private TextField deadlineInput;

    @FXML
    private ChoiceBox<String> jobSelection;

    @FXML
    private TextField maxDeadlineInput;

    @FXML
    private TextField profitInput;

    @FXML
    private MenuItem quit;

    @FXML
    private Label resultLabel;

    /**
     * addButton method for adding a job to the program list and the choiceBox
     */
    @FXML
    void addButton(ActionEvent event) {
        if (!profitInput.getText().isEmpty() && !deadlineInput.getText().isEmpty() && isNumeric(profitInput.getText())
                && isNumeric(deadlineInput.getText())) { // in case of begin all fine
            jobs.add(new Job(jobs.size() + 1, Integer.parseInt(deadlineInput.getText()), Integer.parseInt(profitInput.getText())));
            jobSelection.getItems().add(jobs.size() + ") " + profitInput.getText() + ", " + deadlineInput.getText());

            jobSelection.getSelectionModel().selectLast();

            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText("Successfully added !");

        } else { // if there is any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Please fill \'Profit\' and \'Deadline\' correctly !");

        }
    }

    /**
     * calculateFKPS method for calculating the Job Sequencing Problem with Deadlines
     */
    @FXML
    void calculateJSPD(ActionEvent event) {
        if (!maxDeadlineInput.getText().isEmpty() && isNumeric(maxDeadlineInput.getText()) && !jobSelection.getItems().isEmpty()) {
            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText( "Answer is > [ " + scheduleJobs(Integer.parseInt(maxDeadlineInput.getText())) + " ]" + " --- Jobs > " + results);

        } else { // in case of having any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Please fill \'Max Time\' and \'Jobs\' correctly !");

        }
    }

    /**
     * delButton method for deleting a job from the program list and the choiceBox
     */
    @FXML
    void delButton(ActionEvent event) {
        if (jobSelection.getItems().isEmpty()) { // if choiceBox is not empty
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Nothing to delete !");

        } else {
            int selectedIndex = jobSelection.getSelectionModel().getSelectedIndex();
            jobSelection.getItems().remove(jobSelection.getValue());
            jobs.remove(selectedIndex);

            jobSelection.getSelectionModel().selectLast();

            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText("Successfully deleted !");
        }

    }

    /**
     * exitProgram method for exiting the program by clicking the Quit button.
     */
    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
    }
}


