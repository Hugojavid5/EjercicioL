package org.hugo.ejerciciol;
import Model.ModelAeropuertoPrivado;
import Model.ModelAeropuertoPublico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    public void initialize() {
        // Inicializa las listas de datos y carga la tabla según el tipo de aeropuerto seleccionado
        cargarTabla();
    }
    @FXML
    void cargarTabla() {
        if (rb_publicos.isSelected()) {
            esPublico = true;
            cargarTablaPublico();
        } else {
            esPublico = false;
            cargarTablaPrivado();
        }
    }
    private void cargarTablaPublico() {
        // Realiza la consulta de datos y asigna los datos a la tabla de aeropuertos públicos
        listaTodasPublico = FXCollections.observableArrayList( /* consulta a la base de datos */ );
        filtroPublico = new FilteredList<>(listaTodasPublico, p -> true);
        tablaPublico.setItems(filtroPublico);
        tablaPublico.setVisible(true);
        tablaPrivado.setVisible(false);
    }
    private void cargarTablaPrivado() {
        // Realiza la consulta de datos y asigna los datos a la tabla de aeropuertos privados
        listaTodasPrivado = FXCollections.observableArrayList( /* consulta a la base de datos */ );
        filtroPrivado = new FilteredList<>(listaTodasPrivado, p -> true);
        tablaPrivado.setItems(filtroPrivado);
        tablaPublico.setVisible(false);
        tablaPrivado.setVisible(true);
    }

    @FXML
    void filtrarPorNombre(ActionEvent event) {
        String filtroNombre = txt_nombre.getText().toLowerCase();
        if (esPublico) {
            filtroPublico.setPredicate(aeropuerto -> aeropuerto.getNombre().toLowerCase().contains(filtroNombre));
        } else {
            filtroPrivado.setPredicate(aeropuerto -> aeropuerto.getNombre().toLowerCase().contains(filtroNombre));
        }
    }
    // Métodos de acción del menú
    @FXML
    void activarDesactivarAvion(ActionEvent event) {
        // Lógica para activar o desactivar un avión
    }

    @FXML
    void aniadirAeropuerto(ActionEvent event) {
        // Lógica para añadir un nuevo aeropuerto
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
}
