/**
 * Controller Class File
 *
 * @author Ali Badiee
 * @version 1.0
 */

// packages
package com.ali.badiee.hw2_1;

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

import static com.ali.badiee.hw2_1.HW2Application.isNumeric;
import static com.ali.badiee.hw2_1.Knapsack.items;
import static com.ali.badiee.hw2_1.Knapsack.knapsack_find;

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
    private TextField capacityInput;

    @FXML
    private ChoiceBox<String> itemSelection;

    @FXML
    private TextField valueInput;

    @FXML
    private MenuItem quit;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField weightInput;

    /**
     * addButton method for adding an item to the program list and the choiceBox
     */
    @FXML
    void addButton(ActionEvent event) {
        if (!valueInput.getText().isEmpty() && !weightInput.getText().isEmpty() && isNumeric(valueInput.getText())
                && isNumeric(weightInput.getText())) { // in case of begin all fine
            items.add(new Item(Integer.parseInt(valueInput.getText()), Integer.parseInt(weightInput.getText())));
            itemSelection.getItems().add(valueInput.getText() + ", " + weightInput.getText());

            itemSelection.getSelectionModel().selectLast();

            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText("Successfully added !");

        } else { // if there is any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Please fill 'Value' and 'Weight' correctly !");

        }
    }

    /**
     * calculateFKPS method for calculating the fractional knapsack problem
     */
    @FXML
    void calculateFKP(ActionEvent event) {
        if (!capacityInput.getText().isEmpty() && isNumeric(capacityInput.getText()) && !itemSelection.getItems().isEmpty()) {
            resultLabel.setTextFill(Paint.valueOf("#50C878"));
            resultLabel.setText(knapsack_find(Integer.parseInt(capacityInput.getText())).toString());

        } else { // in case of having any problem
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Please fill 'Capacity' and 'Items' correctly !");

        }
    }

    /**
     * delButton method for deleting an item from the program list and the choiceBox
     */
    @FXML
    void delButton(ActionEvent event) {
        if (itemSelection.getItems().isEmpty()) { // if choiceBox is not empty
            resultLabel.setTextFill(Paint.valueOf("#CC397B"));
            resultLabel.setText("Nothing to delete !");

        } else {
            int selectedIndex = itemSelection.getSelectionModel().getSelectedIndex();
            itemSelection.getItems().remove(itemSelection.getValue());
            items.remove(selectedIndex);

            itemSelection.getSelectionModel().selectLast();

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


