package org.hugo.ejerciciol;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;

public class ActivarYDesactivarAvionController {

    @FXML
    private ComboBox<?> cb_aeropuertos;

    @FXML
    private ComboBox<?> cb_aviones;

    @FXML
    private RadioButton rb_activado;

    @FXML
    private RadioButton rb_desactivado;

    @FXML
    private ToggleGroup rb_grupo;

    @FXML
    void cancelar(ActionEvent event) {
    }

    @FXML
    public void guardar(ActionEvent actionEvent) {
    }



}
