package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Agent;
import org.example.ServiceApp;

public class LoginController {


    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button loginButton;

    @FXML
    private CheckBox adminCheckBox;

    @FXML
    private Label errorLabel;

    ServiceApp serviceApp;

    public LoginController() {
    }

    public void setService(ServiceApp serviceApp) {
        this.serviceApp = serviceApp;
    }

    public void loginButtonPressed() throws Exception {
        errorLabel.setText("");
        String username = usernameText.getText();
        String password = passwordText.getText();
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Please enter username and password!");
        } else {
            if (adminCheckBox.isSelected()) {
                // login as admin
                try {
                    Stage AdminWindow = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 430);
                    AdminWindow.setTitle(username);
                    AdminWindow.setScene(scene);

                    serviceApp.loginAdmin(username, password);

                    AdminController adminController = fxmlLoader.getController();
                    adminController.setService(serviceApp);

                    AdminWindow.show();
                }
                catch (Exception e) {
                    errorLabel.setText("Wrong username or password!");
                }
                finally {
                    usernameText.setText("");
                    passwordText.setText("");
                }
            } else { // login as agent
                try {
                    Stage AgentWindow = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainProduse.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 833, 706);

                    AgentWindow.setTitle(username);
                    AgentWindow.setScene(scene);


                    MainController mainController = fxmlLoader.getController();
                    mainController.setService(serviceApp);

                    serviceApp.loginAgent(username, password, mainController);

                    mainController.setLoggedAgent(new Agent(username, password));

                    AgentWindow.show();
                }
                catch (Exception e) {
                    errorLabel.setText("Wrong username or password!");
                }
                finally {
                    usernameText.setText("");
                    passwordText.setText("");
                    adminCheckBox.setSelected(false);
                }
            }
        }
    }



}
