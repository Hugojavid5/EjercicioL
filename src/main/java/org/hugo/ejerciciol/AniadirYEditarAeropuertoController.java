package org.hugo.ejerciciol;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
public class AniadirYEditarAeropuertoController {

    @FXML
    private Label lbl_Financiacion;
    @FXML
    private Label lbl_NumeroDeSocios;
    @FXML
    private Label lbL_NumeroDeTrabajadores;
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
    private TextField txt_numeroDeSocios;

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void guardar(ActionEvent event) {

    }
    // Método para mostrar/ocultar campos según el tipo de aeropuerto seleccionado
    @FXML
    void generarCampos(ActionEvent event) {
        if (rb_privado.isSelected()) {
            // Mostrar campos para aeropuerto privado
            lbl_NumeroDeSocios.setVisible(true);
            txt_numeroDeSocios.setVisible(true);
            // Ocultar campos para aeropuerto público
            lbl_Financiacion.setVisible(false);
            txt_financiacion.setVisible(false);
            lbl_NumeroDeSocios.setVisible(false);
            txt_trabajadores.setVisible(false);
        } else if (rb_publico.isSelected()) {
            // Mostrar campos para aeropuerto público
            lbl_Financiacion.setVisible(true);
            txt_financiacion.setVisible(true);
            lbl_NumeroDeSocios.setVisible(true);
            txt_trabajadores.setVisible(true);
            // Ocultar campos para aeropuerto privado
            lbl_NumeroDeSocios.setVisible(false);
            txt_numeroDeSocios.setVisible(false);
        }
    }
}
