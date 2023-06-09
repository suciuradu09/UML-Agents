package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.ServiceApp;

public class UpdateAgentController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button buttonUpdate;

    ServiceApp serviceApp;

    public void setService(ServiceApp service) {
        this.serviceApp = service;
    }

    public UpdateAgentController() {
    }

    public void onUpdateButtonPressed() {
        String username = usernameText.getText();
        String password = passwordText.getText();
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill all the fields!");
        } else {
            try {
                serviceApp.updateAgent(username, password);
                errorLabel.setStyle("-fx-text-fill: green;");
                errorLabel.setText("Agent updated successfully!");
                Thread.sleep(2000);
                buttonUpdate.getScene().getWindow().hide();
            } catch (Exception e) {
                errorLabel.setText("Username does not exist!");
            } finally {
                errorLabel.setText("");
            }
        }
    }

}
