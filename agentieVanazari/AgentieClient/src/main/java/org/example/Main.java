package org.example;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.database.AdminDBRepository;
import org.example.database.AgentDBRepository;
import org.example.database.ProdusDBRepository;
import org.example.interfaces.AdminRepository;
import org.example.interfaces.AgentRepository;
import org.example.interfaces.ProdusRepository;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage loginWindow) throws Exception {

        AgentRepository agenti = new AgentDBRepository();
        AdminRepository admini = new AdminDBRepository();
        ProdusRepository produse = new ProdusDBRepository();
        ServiceApp serviceApp = new ServiceApp(agenti, admini, produse);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 443, 512);

        LoginController loginController = fxmlLoader.getController();
        loginController.setService(serviceApp);

        loginWindow.setTitle("Login");
        loginWindow.setScene(scene);
        loginWindow.show();
    }

    public static void main(String[] args) {
        launch();
    }
}