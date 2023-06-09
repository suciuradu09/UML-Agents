package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Agent;
import org.example.ServiceApp;

import java.security.Provider;

public class AddAgentController {

    @FXML
    private Label errorLabel;

    @FXML
    private Button buttonSubmit;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private PasswordField repeatPassword;

    ServiceApp serviceApp;

    public AddAgentController() {
    }

    public void setService(ServiceApp service) {
        this.serviceApp = service;
    }

    public void submitButtonPressed() throws Exception {
        String username = usernameText.getText();
        String password = passwordText.getText();
        String repeat = repeatPassword.getText();
        if (username.isEmpty() || password.isEmpty() || repeat.isEmpty()) {
            errorLabel.setText("Please fill all the fields!");
        } else {
            if (password.equals(repeat)) {
                try {
                    Agent agent = new Agent(username, password);
                    serviceApp.saveAgent(agent);
                    errorLabel.setText("Agent added successfully!");
                    errorLabel.setStyle("-fx-text-fill: green;");
                    Thread.sleep(2000);
                    // hide the stage
                    buttonSubmit.getScene().getWindow().hide();
                }
                catch (Exception e) {
                    errorLabel.setText("Username already exists!");
                }
                finally {
                    usernameText.setText("");
                    passwordText.setText("");
                    repeatPassword.setText("");
                }
            }
            else {
                errorLabel.setText("Passwords don't match!");
            }
        }
    }

}
