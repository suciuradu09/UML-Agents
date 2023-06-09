package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.Agent;
import org.example.ServiceApp;

public class AdminController {


    @FXML
    private Button buttonAdaugare;

    @FXML
    private Button buttonStergere;

    @FXML
    private Button buttonModificare;

    @FXML
    private Button buttonVizualizare;

    @FXML
    private Button logoutButton;

    ServiceApp serviceApp;

    public AdminController() {
    }

    public void setService(ServiceApp service) {
        this.serviceApp = service;
    }


    public void adaugareButtonPressed() throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addAgent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 341, 400);

        AddAgentController addAgentController = fxmlLoader.getController();
        addAgentController.setService(serviceApp);

        stage.setTitle("Adaugare agent");
        stage.setScene(scene);
        stage.show();
    }

    public void stergereButtonPressed() throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/delAgent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 300);

        DeleteAgentController deleteAgentController = fxmlLoader.getController();
        deleteAgentController.setService(serviceApp);

        stage.setTitle("Stergere agent");
        stage.setScene(scene);
        stage.show();
    }

    public void modificareButtonPressed() throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/updateAgent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 298, 400);

        UpdateAgentController updateAgentController = fxmlLoader.getController();
        updateAgentController.setService(serviceApp);

        stage.setTitle("Modificare agent");
        stage.setScene(scene);
        stage.show();
    }

    public void vizualizareButtonPressed() throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewAgents.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 467);

        ViewAgentsController viewAgentsController = fxmlLoader.getController();
        viewAgentsController.setService(serviceApp);
        viewAgentsController.initializeTable();

        stage.setTitle("Vizualizare agenti");
        stage.setScene(scene);
        stage.show();
    }

    public void onLogoutButtonPressed() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        System.out.println("Admin logged out");
        stage.close();
    }




}
