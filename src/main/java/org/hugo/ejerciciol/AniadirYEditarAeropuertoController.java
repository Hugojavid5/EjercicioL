package org.hugo.ejerciciol;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
public class AniadirYEditarAeropuertoController {


    @FXML
    private RadioButton rb_privado;

    @FXML
    private RadioButton rb_publico;

    @FXML
    private ToggleGroup rb_tipoAeropuerto;

    @FXML
    private TextField txt_anioDeInauguracion;

    @FXML
    private TextField txt_calle;

    @FXML
    private TextField txt_capacidad;

    @FXML
    private TextField txt_ciudad;

    @FXML
    private TextField txt_financiacion;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_numero;

    @FXML
    private TextField txt_pais;

    @FXML
    private TextField txt_trabajadores;

    @FXML
    void cancelar(ActionEvent event) {

    }
    @FXML
    void guardar(ActionEvent event) {

    }
}
