package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.Order;
import org.example.ServiceApp;

public class OrderDetailsController {


    @FXML
    private TextField textName;
    @FXML
    private TextField textAdress;
    @FXML
    private TextField textCity;
    @FXML
    private TextField textCountry;
    @FXML
    private TextField textPostalCode;
    @FXML
    private Button buttonDone;

    ServiceApp serviceApp;

    Order comanda;

    public OrderDetailsController() {
    }

    public void setService(ServiceApp serviceApp) {
        this.serviceApp = serviceApp;
    }

    public void buttonDonePressed() {
        String name = textName.getText();
        String adress = textAdress.getText();
        String city = textCity.getText();
        String country = textCountry.getText();
        String postalCode = textPostalCode.getText();
        if (name.isEmpty() || adress.isEmpty() || city.isEmpty() || country.isEmpty() || postalCode.isEmpty()) {
            System.out.println("Please enter all the fields!");
        } else {
            comanda = new Order(name, adress, city, country, postalCode);
            buttonDone.getScene().getWindow().hide();
            textName.clear();
            textAdress.clear();
            textCity.clear();
            textCountry.clear();
            textPostalCode.clear();
        }
    }

    public Order getOrder(){
        return comanda;
    }

}
