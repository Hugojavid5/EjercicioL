package org.hugo.ejerciciol;

import BBDD.ConexionBBDD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {
    private static Stage stage;
    @Override
    public void start(Stage s) throws IOException {
        stage=s;
        Properties connConfig = ConexionBBDD.loadProperties();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 150);
        stage.setResizable(false);
        stage.setTitle("Login de aviones");
        stage.setScene(scene);
        stage.show();
    }
    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}