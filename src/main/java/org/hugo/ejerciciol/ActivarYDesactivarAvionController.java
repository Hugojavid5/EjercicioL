package org.hugo.ejerciciol;
import Dao.DaoAeropuerto;
import Model.ModelAeropuerto;
import Model.ModelAvion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import Dao.DaoAvion;

public class ActivarYDesactivarAvionController {

    @FXML
    private ComboBox<ModelAeropuerto> cb_aeropuertos;

    @FXML
    private ComboBox<ModelAvion> cb_aviones;

    @FXML
    private RadioButton rb_activado;

    @FXML
    private RadioButton rb_desactivado;

    @FXML
    private ToggleGroup rb_grupo;

    @FXML
    private void initialize() {
        this.cb_aeropuertos.setItems(DaoAeropuerto.listaTodas());
        rb_activado.setVisible(!ListarAeropuertoController.isBorrar());
        rb_desactivado.setVisible(!ListarAeropuertoController.isBorrar());
    }

    @FXML
    void actualizarCB(ActionEvent event) {
        this.cb_aviones.setItems(DaoAvion.listaAviones(this.cb_aeropuertos.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    void cancelar(ActionEvent event) {
        ListarAeropuertoController.getS().close();
    }

    @FXML
    void guardar(ActionEvent event) {
        Alert al=new Alert(Alert.AlertType.CONFIRMATION);
        al.setHeaderText(null);
        if(ListarAeropuertoController.isBorrar()) {
            al.setContentText("¿Estas seguro de que deseas eliminar el avion?");
            al.showAndWait();
            if(al.getResult().getButtonData().name().equals("OK_DONE")) {
                DaoAvion.delete(cb_aviones.getSelectionModel().getSelectedItem().getModelo(),cb_aviones.getSelectionModel().getSelectedItem().getIdAeropuerto());
                cb_aviones.setItems(DaoAvion.listaAviones(cb_aeropuertos.getSelectionModel().getSelectedItem().getId()));
            }
        }else {
            al.setAlertType(Alert.AlertType.INFORMATION);
            DaoAvion.update(cb_aviones.getSelectionModel().getSelectedItem().getModelo(), cb_aviones.getSelectionModel().getSelectedItem().getIdAeropuerto(),
                    rb_activado.isSelected());
            al.setContentText("Avion modificado correctamente");
            al.showAndWait();
        }
    }

}
