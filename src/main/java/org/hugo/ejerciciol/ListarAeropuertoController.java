package org.hugo.ejerciciol;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
public class ListarAeropuertoController {

    @FXML
    private ToggleGroup grupoRB;

    @FXML
    private RadioButton rb_privados;

    @FXML
    private RadioButton rb_publicos;

    @FXML
    private TableView<?> tabla;

    @FXML
    private TextField txt_nombre;

    @FXML
    void activarDesactivarAvion(ActionEvent event) {
    }

    @FXML
    void aniadirAeropuerto(ActionEvent event) {
    }

    @FXML
    void aniadirAvion(ActionEvent event) {
    }

    @FXML
    void borrarAeropuerto(ActionEvent event) {
    }

    @FXML
    void eliminarAvion(ActionEvent event) {
    }

    @FXML
    void informacionAeropuerto(ActionEvent event) {
    }
    @FXML
    void filtrarPorNombre(ActionEvent event)
    {

    }

        @FXML
    void editarAeropuerto(ActionEvent event) {
    }
}
