package org.example.controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.Agent;
import org.example.Order;
import org.example.Produs;
import org.example.ServiceApp;
import org.example.interfaces.ObserverInterface;

import java.io.IOException;


public class MainController implements ObserverInterface {

    Agent loggedAgent;

    @FXML
    private Label totalCost;

    @FXML
    private TableView<Produs> tableProduse;

    @FXML
    private TableColumn<Produs, Long> idColumn;

    @FXML
    private TableColumn<Produs, String> denumireColumn;

    @FXML
    private TableColumn<Produs, Double> pretColumn;

    @FXML
    private TableColumn<Produs, Integer> cantitateColumn;

    @FXML
    private TextField searchText;

    @FXML
    private Button buttonAdd;

    @FXML
    private TextField cantitateText;

    @FXML
    private ListView<Produs> listViewComanda;

    @FXML
    private Button buttonPlaceOrder;

    @FXML
    private Button buttonOrderDetails;

    @FXML
    private Button ButtonViewProducts;

    Order comanda;

    @FXML
    private Button logoutButton;

    ServiceApp serviceApp;

    ObservableList<Produs> modelProdus = FXCollections.observableArrayList();

    public MainController() {
    }

    public void setService(ServiceApp service) {
        this.serviceApp = service;
    }

    public void setLoggedAgent(Agent agent) {
        this.loggedAgent = agent;
    }

    public void populateProduse() {
        modelProdus.clear();

        for (Produs produs : serviceApp.getAllProducts()) {
            modelProdus.add(produs);
        }

        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));
        denumireColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDenumire()));
        pretColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPret()));
        cantitateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCantitate()));
        tableProduse.setItems(modelProdus);

    }

    public void onLogoutPressed() throws Exception {
        serviceApp.logoutAgent(loggedAgent.getUsername());
        logoutButton.getScene().getWindow().hide();
    }


    public void findProdus(KeyEvent keyEvent) {
        modelProdus.clear();
        for ( Produs produs : serviceApp.getAllProducts()) {
            if (produs.getDenumire().contains(searchText.getText())) {
                modelProdus.add(produs);
            }
        }
        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));
        denumireColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDenumire()));
        pretColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPret()));
        cantitateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCantitate()));
        tableProduse.setItems(modelProdus);
    }


    public void buttonAddPressed() {
        Produs produs = tableProduse.getSelectionModel().getSelectedItem();
        if (produs != null ) {
            Integer cantitate = Integer.parseInt(cantitateText.getText());
            // Add to list
            Integer cantitateNoua = produs.getCantitate() - cantitate;
            if (cantitateNoua >= 0) {
                // Add the product to the list
                produs.setCantitate(cantitate);
                listViewComanda.getItems().add(produs);
                listViewComanda.refresh();
                String text = serviceApp.getTotalCost(listViewComanda.getItems());
                totalCost.setText(text);
                cantitateText.clear();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not enough products in stock");
                alert.setContentText("There are not enough products in stock");
                alert.showAndWait();
            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product from the table");
            alert.showAndWait();
        }
    }

    public void buttonEnterDetailsPressed() throws IOException {
        Stage DetailsWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/orderDetails.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 456, 366);
        DetailsWindow.setTitle("Order details");
        DetailsWindow.setScene(scene);

        OrderDetailsController orderDetailsController = fxmlLoader.getController();
        orderDetailsController.setService(serviceApp);

        DetailsWindow.show();

        comanda = orderDetailsController.getOrder();

    }

    public void buttonPlaceOrderPressed() {
        for (Produs produs : listViewComanda.getItems()) {
            Produs prod = serviceApp.findByDenumire(produs.getDenumire());
            produs.setId(prod.getId());
            produs.setCantitate(prod.getCantitate() - produs.getCantitate());
            serviceApp.updateProduct(produs);
        }
        if (comanda == null) {
            comanda = new Order("", "", "", "", "");
        }
        comanda.setProductList(listViewComanda.getItems());
        serviceApp.addOrder(comanda);
        listViewComanda.getItems().clear();
        listViewComanda.refresh();
        totalCost.setText("0");
    }

    @Override
    public void produsRecieved(Produs produs) {
        Platform.runLater(() -> {
            try {
                populateProduse();
                //searchText.setText(produs.getDenumire());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
