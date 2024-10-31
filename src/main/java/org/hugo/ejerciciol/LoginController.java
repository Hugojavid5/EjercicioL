package org.hugo.ejerciciol;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import BBDD.ConexionBBDD;
import Dao.DaoUsuario;
import Model.ModelUsuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private Button btt_login;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_usuario;

    private DaoUsuario daoUsuario;
    @FXML
    void login(ActionEvent event) {
        String usuario = txt_usuario.getText();
        String password = txt_password.getText();

        ModelUsuario usuarioModel = daoUsuario.getUsuario(usuario);
        if (usuarioModel != null && usuarioModel.getPassword().equals(password)) {
            // Usuario encontrado, cargar la pantalla de lista de aeropuertos
            loadAeropuertosScreen();
        } else {
            // Usuario no encontrado, mostrar alerta y limpiar campos
            mostrarAlerta("Usuario no encontrado", "No hay un usuario así en la base de datos.");
            txt_usuario.clear();
            txt_password.clear();
        }
    }
    private void mostrarAlerta(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void loadAeropuertosScreen() {
        try {
            // Cargar la nueva pantalla
            Parent root = FXMLLoader.load(getClass().getResource("ListarAeropuerto.fxml")); // Asegúrate de que la ruta sea correcta
            Stage stage = (Stage) btt_login.getScene().getWindow(); // Obtener la ventana actual
            stage.setScene(new Scene(root)); // Cambiar la escena
            stage.setTitle("AVIONES - AEROPUERTOS");
            stage.show(); // Mostrar la nueva escena
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de errores al cargar la pantalla
            mostrarAlerta("Error", "No se pudo cargar la pantalla de lista de aeropuertos.");
        }
    }
}
