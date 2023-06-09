package org.example.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.Agent;
import org.example.ServiceApp;

public class ViewAgentsController {

    @FXML
    private TableView<Agent> tabelAgenti;

    @FXML
    private TableColumn<Agent, Long> idColumn;

    @FXML
    private TableColumn<Agent, String> usernameColumn;

    @FXML
    private TableColumn<Agent, String> passwordColumn;


    ServiceApp serviceApp;

    ObservableList<Agent> modelAgent = FXCollections.observableArrayList();

    public ViewAgentsController() {
    }

    public void setService(ServiceApp service) {
        this.serviceApp = service;
    }

    public void initializeTable() {
        modelAgent.clear();
        modelAgent.addAll(serviceApp.showWorkingAgents());

        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));
        usernameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        passwordColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));
        tabelAgenti.setItems(modelAgent);
    }

}
