package org.hugo.ejerciciol;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AniadirAvionController {

    @FXML
    private ComboBox<?> cb_aeropuerto;

    @FXML
    private RadioButton rb_activado;

    @FXML
    private RadioButton rb_desactivado;

    @FXML
    private ToggleGroup rb_grupo;

    @FXML
    private TextField txt_asientos;
    @FXML
    private TextField txt_modelo;
    @FXML
    private TextField txt_velMaxima;

    @FXML
    void cancelar(ActionEvent event) {
    }

    @FXML
    public void guardar(ActionEvent actionEvent) {
    }

}
