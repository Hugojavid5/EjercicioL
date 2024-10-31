package org.hugo.ejerciciol;
import BBDD.ConexionBBDD;
import Dao.DaoAeropuerto;
import Dao.DaoAeropuertoPrivado;
import Dao.DaoAeropuertoPublico;
import Model.ModelAeropuertoPublico;
import Model.ModelAeropuertoPrivado;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.stage.Modality;
import java.sql.SQLException;
public class ListarAeropuertoController {

    @FXML
    private ToggleGroup grupoRB;

    @FXML
    private MenuItem menuActivarDesactivarAvion;

    @FXML
    private MenuItem menuAniadirAeropuerto;

    @FXML
    private MenuItem menuAniadirAvion;

    @FXML
    private MenuItem menuBorrarAeropuerto;

    @FXML
    private MenuItem menuEditarAeropuerto;

    @FXML
    private MenuItem menuEliminarAvion;

    @FXML
    private MenuItem menuInformacionAeropuerto;

    @FXML
    private RadioButton rb_privados;

    @FXML
    private RadioButton rb_publicos;

    @FXML
    private TableView<ModelAeropuertoPrivado> tablaPrivado;

    @FXML
    private TableView<ModelAeropuertoPublico> tablaPublico;

    @FXML
    private TableColumn<ModelAeropuertoPrivado, Integer> tcIdPrivado;
    @FXML
    private TableColumn<ModelAeropuertoPublico, Integer> tcIdPublico;

    @FXML
    private TableColumn<ModelAeropuertoPrivado, String> tcNombrePrivado;
    @FXML
    private TableColumn<ModelAeropuertoPublico, String> tcNombrePublico;
    @FXML
    private TableColumn<ModelAeropuertoPrivado, String> tcPaisPrivado;
    @FXML
    private TableColumn<ModelAeropuertoPublico, String> tcPaisPublico;
    @FXML
    private TableColumn<ModelAeropuertoPrivado, String> tcCiudadPrivado;
    @FXML
    private TableColumn<ModelAeropuertoPublico, String> tcCiudadPublico;
    @FXML
    private TableColumn<ModelAeropuertoPrivado, String> tcCallePrivado;
    @FXML
    private TableColumn<ModelAeropuertoPublico, String> tcCallePublico;
    @FXML
    private TableColumn<ModelAeropuertoPrivado, Integer> tcNumeroPrivado;
    @FXML
    private TableColumn<ModelAeropuertoPublico, Integer> tcNumeroPublico;
    @FXML
    private TableColumn<ModelAeropuertoPrivado, Integer> tcAnioPrivado;
    @FXML
    private TableColumn<ModelAeropuertoPublico, Integer> tcAnioPublico;
    @FXML
    private TableColumn<ModelAeropuertoPrivado, Integer> tcCapacidadPrivado;

    @FXML
    private TableColumn<ModelAeropuertoPublico, Integer> tcCapacidadPublico;
    @FXML
    private TableColumn<ModelAeropuertoPrivado, Integer> tcNumeroSocios;
    @FXML
    private TableColumn<ModelAeropuertoPublico, Integer> tcNumeroTrabajadores;
    @FXML
    private TableColumn<ModelAeropuertoPublico, Double> tcFinanciacion;
    @FXML
    private TextField txt_nombre;

    private ObservableList<ModelAeropuertoPrivado> listaTodasPrivado;
    private ObservableList<ModelAeropuertoPublico> listaTodasPublico;
    private FilteredList<ModelAeropuertoPrivado> filtroPrivado;
    private FilteredList<ModelAeropuertoPublico> filtroPublico;

    private boolean esPublico = true;
    private static Stage s;
    @FXML
    private void initialize() {
        try {
            ConexionBBDD con=new ConexionBBDD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txt_nombre.setOnKeyReleased(event -> filtrarPorNombre());
        //Tabla publico
        listaTodasPublico= DaoAeropuertoPublico.cargarListaAeropuertosPublicos();
        tablaPublico.setItems(listaTodasPublico);
        tcAnioPublico.setCellValueFactory(new PropertyValueFactory<>("anioInauguracion"));
        tcCallePublico.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getCalle()));
        tcCapacidadPublico.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        tcCiudadPublico.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getCiudad()));
        tcFinanciacion.setCellValueFactory(new PropertyValueFactory<>("financiacion"));
        tcIdPublico.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNombrePublico.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcNumeroPublico.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getDireccion().getNumero()).asObject());
        tcPaisPublico.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getPais()));
        tcNumeroTrabajadores.setCellValueFactory(new PropertyValueFactory<>("numTrabajadores"));
        filtroPublico=new FilteredList<ModelAeropuertoPublico>(listaTodasPublico);

        tcAnioPrivado.setCellValueFactory(new PropertyValueFactory<>("anioInauguracion"));
        tcCallePrivado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getCalle()));
        tcCapacidadPrivado.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        tcCiudadPrivado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getCiudad()));
        tcIdPrivado.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNombrePrivado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcNumeroPrivado.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getDireccion().getNumero()).asObject());
        tcPaisPrivado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDireccion().getPais()));
        tcNumeroSocios.setCellValueFactory(new PropertyValueFactory<>("numSocios"));
        listaTodasPrivado= DaoAeropuertoPrivado.cargarListaAeropuertosPrivados();
        filtroPrivado=new FilteredList<ModelAeropuertoPrivado>(listaTodasPrivado);
        tablaPrivado.setItems(listaTodasPrivado);
    }
    @FXML
    private void filtrarPorNombre() {
        String filtroTexto = txt_nombre.getText().toLowerCase();
        if (esPublico) {
            filtroPublico.setPredicate(aeropuerto -> {
                if (filtroTexto == null || filtroTexto.isEmpty()) {
                    return true;
                }
                String nombreAeropuerto = aeropuerto.getNombre().toLowerCase();
                return nombreAeropuerto.contains(filtroTexto);
            });
            tablaPublico.setItems(filtroPublico);
        } else {
            filtroPrivado.setPredicate(aeropuerto -> {
                if (filtroTexto == null || filtroTexto.isEmpty()) {
                    return true;
                }
                String nombreAeropuerto = aeropuerto.getNombre().toLowerCase();
                return nombreAeropuerto.contains(filtroTexto);
            });
            tablaPrivado.setItems(filtroPrivado);
        }
    }
    // Métodos de acción del menú
    @FXML
    void activarDesactivarAvion(ActionEvent event) {
        // Lógica para activar o desactivar un avión
    }

    @FXML
    void aniadirAeropuerto(ActionEvent event) {
        try {
            // Carga el archivo FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AniadirYEditarAeropuertos.fxml"));
            Parent root = fxmlLoader.load();
            // Configura el nuevo Stage para la ventana
            Stage stage = new Stage();
            stage.setTitle("Añadir o Editar Aeropuerto");
            stage.setScene(new Scene(root));
            // Muestra la ventana y espera a que el usuario la cierre antes de volver a la ventana principal
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void aniadirAvion(ActionEvent event) {
        // Lógica para añadir un nuevo avión
    }
    @FXML
    void borrarAeropuerto(ActionEvent event) {
        // Lógica para borrar un aeropuerto
    }
    @FXML
    void editarAeropuerto(ActionEvent event) {
        // Lógica para editar un aeropuerto
    }

    @FXML
    void eliminarAvion(ActionEvent event) {
        // Lógica para eliminar un avión
    }

    @FXML
    void informacionAeropuerto(ActionEvent event) {
        // Lógica para mostrar información detallada de un aeropuerto
    }
    public void cargarTabla(ActionEvent actionEvent) {
        esPublico=rb_publicos.isSelected();
        if(rb_publicos.isSelected()) {
            tablaPublico.setVisible(true);
            tablaPrivado.setVisible(false);
        }else {
            tablaPublico.setVisible(false);
            tablaPrivado.setVisible(true);;
        }
    }
}
