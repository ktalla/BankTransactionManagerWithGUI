package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton option3;

    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Label welcomeText;

    @FXML
    private CheckBox checkBox;

    @FXML
    private VBox secondButtonBox;
    @FXML
    private VBox thirdButtonBox;


        public void handleCollegeCheckingOption() {
            ToggleGroup toggleGroup = new ToggleGroup();
            radioButton2.setToggleGroup(toggleGroup);

            // Set up action for radioButton1
            radioButton2.setOnAction(event -> {
                if (radioButton2.isSelected()) {
                    secondButtonBox.setVisible(true);
                    secondButtonBox.setManaged(true);
                } else {
                    secondButtonBox.setVisible(false);
                    secondButtonBox.setManaged(false);
                }
            });
        }

    public void handleSavingsOption() {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton3.setToggleGroup(toggleGroup);

        // Set up action for radioButton1
        radioButton3.setOnAction(event -> {
            if (radioButton3.isSelected()) {
                secondButtonBox.setVisible(false);
                thirdButtonBox.setVisible(true);
                thirdButtonBox.setManaged(true);
            } else {
                thirdButtonBox.setVisible(false);
                thirdButtonBox.setManaged(false);
            }
        });
    }
    }



