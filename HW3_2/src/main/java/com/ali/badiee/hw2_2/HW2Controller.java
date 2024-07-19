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
import static com.ali.badiee.hw2_2.SubsetProblem.nodes;
import static com.ali.badiee.hw2_2.SubsetProblem.subSetProblem;
import static com.ali.badiee.hw2_2.SubsetProblem.results;
import static com.ali.badiee.hw2_2.SubsetProblem.mainPrefix;

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
    private TextField weightInput;

    @FXML
    private ChoiceBox<String> taskSelection;

    @FXML
    private TextField targetInput;

    @FXML
    private MenuItem quit;

    @FXML
    private Label resultLabel;


    /**
     * addButton method for adding a task to the program list and the choiceBox
     */
    @FXML
    void addButton(ActionEvent event) {
        if (!weightInput.getText().isEmpty() && isNumeric(weightInput.getText())) { // in case of begin all fine
            nodes.add(new Node(Integer.parseInt(weightInput.getText())));
            taskSelection.getItems().add(String.valueOf(nodes.get(nodes.size() - 1)));

            taskSelection.getSelectionModel().selectLast();

            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText("Successfully added !");

        } else { // if there is any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Please fill the 'Weight' correctly !");

        }
    }

    /**
     * calculateFKPS method for calculating the Job Sequencing Problem with Deadlines
     */
    @FXML
    void calculateSSPBS(ActionEvent event) {
        if (!taskSelection.getItems().isEmpty() && !targetInput.getText().isEmpty()) {
            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            results = new StringBuilder();
            mainPrefix = "";
            resultLabel.setText("Answer > [ " + subSetProblem(Integer.parseInt(targetInput.getText())) + " ]");

        } else { // in case of having any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("'Target' and 'Weights' can't be empty !");

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
            nodes.remove(selectedIndex);

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

