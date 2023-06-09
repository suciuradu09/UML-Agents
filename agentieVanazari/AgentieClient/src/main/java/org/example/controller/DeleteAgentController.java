package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.ServiceApp;

import java.security.Provider;


public class DeleteAgentController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField usernameText;

    @FXML
    private Button buttonDelete;

    ServiceApp serviceApp;

    public void setService(ServiceApp service) {
        this.serviceApp = service;
    }

    public void onButtonDeletePressed(){
        String username = usernameText.getText();

        if (username.isEmpty()) {
            errorLabel.setText("Please fill all the fields!");
        }
        else {
            try {
                serviceApp.deleteAgent(username);
                errorLabel.setStyle("-fx-text-fill: green;");
                errorLabel.setText("Agent deleted successfully!");
                Thread.sleep(2000);
                buttonDelete.getScene().getWindow().hide();
            }
            catch ( Exception e) {
                errorLabel.setText("Username does not exist!");
            }
            finally {
                errorLabel.setText("");
            }
        }

    }
}
