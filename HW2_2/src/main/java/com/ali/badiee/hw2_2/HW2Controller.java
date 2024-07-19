/**
 * Controller Class File
 *
 * @author Ali Badiee
 * @version 1.0
 */

// packages
package com.ali.badiee.hw2_2;

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
import static com.ali.badiee.hw2_2.HW2Application.isNumeric;
import static com.ali.badiee.hw2_2.TaskScheduler.taskSchedule;
import static com.ali.badiee.hw2_2.TaskScheduler.tasks;

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
    private TextField endInput;

    @FXML
    private ChoiceBox<String> taskSelection;

    @FXML
    private TextField nameInput;

    @FXML
    private MenuItem quit;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField startInput;

    /**
     * addButton method for adding a task to the program list and the choiceBox
     */
    @FXML
    void addButton(ActionEvent event) {
        if (!nameInput.getText().isEmpty() && !startInput.getText().isEmpty() && !endInput.getText().isEmpty() && isNumeric(startInput.getText())
                && isNumeric(endInput.getText())) { // in case of begin all fine
            tasks.add(new Task(nameInput.getText(), Integer.parseInt(startInput.getText()), Integer.parseInt(endInput.getText())));
            taskSelection.getItems().add(String.valueOf(tasks.get(tasks.size() - 1)));

            taskSelection.getSelectionModel().selectLast();

            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText("Successfully added !");

        } else { // if there is any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Please fill \'Name\', \'Start\' and \'End\' correctly !");

        }
    }

    /**
     * calculateFKPS method for calculating the Job Sequencing Problem with Deadlines
     */
    @FXML
    void calculateTSP(ActionEvent event) {
        if (!taskSelection.getItems().isEmpty()) {
            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText("Answer > [ " + taskSchedule() + " ]");

        } else { // in case of having any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("\'Tasks\' can't be empty !");

        }
    }


    /**
     * delButton method for deleting a job from the program list and the choiceBox
     */
    @FXML
    void delButton(ActionEvent event) {
        if (taskSelection.getItems().isEmpty()) { // if choiceBox is not empty
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Nothing to delete !");

        } else {
            int selectedIndex = taskSelection.getSelectionModel().getSelectedIndex();
            taskSelection.getItems().remove(taskSelection.getValue());
            tasks.remove(selectedIndex);

            taskSelection.getSelectionModel().selectLast();

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

