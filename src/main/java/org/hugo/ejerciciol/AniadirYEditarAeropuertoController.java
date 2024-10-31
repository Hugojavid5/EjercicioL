package org.hugo.ejerciciol;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import Dao.DaoAeropuerto;
import Dao.DaoAeropuertoPrivado;
import Dao.DaoAeropuertoPublico;
import Dao.DaoDireccion;
import javafx.scene.control.Alert.AlertType;
import Model.ModelAeropuertoPrivado;
import Model.ModelAeropuertoPublico;
import Model.ModelDireccion;

/**
 * Controlador para la gestión de la adición y edición de aeropuertos en la aplicación.
 * Este controlador maneja la entrada del usuario a través de un formulario para crear
 * o modificar aeropuertos, tanto públicos como privados.
 */
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

    public RadioButton getRbPublico() {
        return rb_publico;
    }

    public RadioButton getRbPrivado() {
        return rb_privado;
    }

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

    private TableView<ModelAeropuertoPrivado> tablaPrivado;
    private TableView<ModelAeropuertoPublico> tablaPublico;

    /**
     * Establece el texto del campo de año de inauguración.
     * @param anio El año a establecer en el campo.
     */
    public void setTxtAnioInauguracionText(String anio) {
        txt_anioDeInauguracion.setText(anio);
    }

    /**
     * Establece el texto del campo de calle.
     * @param calle La calle a establecer en el campo.
     */
    public void setTxtCalleText(String calle) {
        txt_calle.setText(calle);
    }

    /**
     * Establece el texto del campo de capacidad.
     * @param capacidad La capacidad a establecer en el campo.
     */
    public void setTxtCapacidadText(String capacidad) {
        txt_capacidad.setText(capacidad);
    }

    /**
     * Establece el texto del campo de ciudad.
     * @param ciudad La ciudad a establecer en el campo.
     */
    public void setTxtCiudadText(String ciudad) {
        txt_ciudad.setText(ciudad);
    }

    /**
     * Establece el texto del campo de financiación.
     * @param financiacion La financiación a establecer en el campo.
     */
    public void setTxtFinanciacionText(String financiacion) {
        txt_financiacion.setText(financiacion);
    }

    /**
     * Establece el texto del campo de nombre.
     * @param nombre El nombre a establecer en el campo.
     */
    public void setTxtNombreText(String nombre) {
        txt_nombre.setText(nombre);
    }

    /**
     * Establece el texto del campo de número.
     * @param numero El número a establecer en el campo.
     */
    public void setTxtNumeroText(String numero) {
        txt_numero.setText(numero);
    }

    /**
     * Establece el texto del campo de número de trabajadores.
     * @param numTrabajadores El número de trabajadores a establecer en el campo.
     */
    public void setTxtNumTrabajadoresText(String numTrabajadores) {
        txt_trabajadores.setText(numTrabajadores);
    }

    /**
     * Establece el texto del campo de país.
     * @param pais El país a establecer en el campo.
     */
    public void setTxtPaisText(String pais) {
        txt_pais.setText(pais);
    }

    /**
     * Establece el texto del campo de número de socios.
     * @param numSocios El número de socios a establecer en el campo.
     */
    public void setTxtNumSociosText(String numSocios) {
        txt_numeroDeSocios.setText(numSocios);
    }

    /**
     * Establece la tabla de aeropuertos privados.
     * @param tablaPrivado La tabla de aeropuertos privados.
     */
    public void setTablaPrivado(TableView<ModelAeropuertoPrivado> tablaPrivado) {
        this.tablaPrivado = tablaPrivado;
    }

    /**
     * Establece la tabla de aeropuertos públicos.
     * @param tablaPublico La tabla de aeropuertos públicos.
     */
    public void setTablaPublico(TableView<ModelAeropuertoPublico> tablaPublico) {
        this.tablaPublico = tablaPublico;
    }

    /**
     * Cancela la acción actual y cierra el formulario.
     * @param event El evento de acción.
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Guarda los datos del aeropuerto, ya sea añadiendo un nuevo aeropuerto o modificando uno existente.
     * @param event El evento de acción.
     */
    @FXML
    void guardar(ActionEvent event) {
        String error = "";
        String nombre = txt_nombre.getText();
        String pais = txt_pais.getText();
        String ciudad = txt_ciudad.getText();
        String calle = txt_calle.getText();
        int numero = -1;
        int anioInauguracion = -1;
        int capacidad = -1;
        boolean esPublico = rb_publico.isSelected();
        float financiacion = -1;
        int numTrabajadores = -1;
        int numSocios = -1;
        boolean existe = false;

        // Validación
        error = validarStrings(error); // Asumo que este método está definido en otra parte
        if (txt_numero.getText().isEmpty()) {
            error += "El campo número es obligatorio\n";
        } else {
            try {
                numero = Integer.parseInt(txt_numero.getText());
                if (numero <= 0) {
                    error += "El número de la calle no puede ser menor que 1\n"; // Cambié la forma de manejar el error
                }
            } catch (NumberFormatException e) {
                error += "El número de la calle debe ser un número\n";
            }
        }
        if (txt_anioDeInauguracion.getText().isEmpty()) {
            error += "El campo año de inauguración es obligatorio\n";
        } else {
            try {
                anioInauguracion = Integer.parseInt(txt_anioDeInauguracion.getText());
                if (anioInauguracion < 1900) {
                    error += "El año de inauguración no puede ser menor al 1900\n"; // Cambié la forma de manejar el error
                }
            } catch (NumberFormatException e) {
                error += "El año de inauguración debe ser un número\n";
            }
        }
        if (txt_capacidad.getText().isEmpty()) {
            error += "El campo capacidad es obligatorio\n";
        } else {
            try {
                capacidad = Integer.parseInt(txt_capacidad.getText());
                if (capacidad <= 0) {
                    error += "La capacidad no puede ser menor a 1\n"; // Cambié la forma de manejar el error
                }
            } catch (NumberFormatException e) {
                error += "La capacidad debe ser un número\n";
            }
        }
        if (esPublico) {
            if (txt_financiacion.getText().isEmpty()) {
                error += "El campo financiación es obligatorio\n";
            } else {
                if (!txt_financiacion.getText().matches("^\\d{1,10}(\\.\\d{1,2})?$")) {
                    error += "La financiación puede tener como mucho 10 dígitos antes del punto y 2 después\n";
                } else {
                    try {
                        financiacion = Float.parseFloat(txt_financiacion.getText());
                        if (financiacion <= 0) {
                            error += "La financiación no puede ser menor que 1\n"; // Cambié la forma de manejar el error
                        }
                    } catch (NumberFormatException e) {
                        error += "La financiación debe ser un número\n";
                    }
                }
            }
            if (txt_trabajadores.getText().isEmpty()) {
                error += "El campo número de trabajadores es obligatorio\n";
            } else {
                try {
                    numTrabajadores = Integer.parseInt(txt_trabajadores.getText());
                    if (numTrabajadores <= 0) {
                        error += "El número de trabajadores no puede ser menor que 1\n"; // Cambié la forma de manejar el error
                    }
                } catch (NumberFormatException e) {
                    error += "El número de trabajadores debe ser un número\n";
                }
            }
        } else {
            if (txt_numeroDeSocios.getText().isEmpty()) {
                error += "El campo número de socios es obligatorio\n";
            } else {
                try {
                    numSocios = Integer.parseInt(txt_numeroDeSocios.getText());
                    if (numSocios <= 0) {
                        error += "El número de socios no puede ser menor que 1\n"; // Cambié la forma de manejar el error
                    }
                } catch (NumberFormatException e) {
                    error += "El número de socios debe ser un número\n";
                }
            }
        }
        // Si hay errores, mostrar mensaje y detener el proceso
        if (!error.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(error);
            alert.showAndWait();
            return;
        }
        // Crear el aeropuerto correspondiente
        DaoAeropuerto daoAeropuerto = new DaoAeropuerto();
        if (esPublico) {
            // Crear objeto aeropuerto público
            ModelAeropuertoPublico aeropuertoPublico = new ModelAeropuertoPublico(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, financiacion, numTrabajadores);

            // Verificar si el aeropuerto ya existe
            existe = daoAeropuerto.existeAeropuerto(aeropuertoPublico);

            if (existe) {
                // Se edita el aeropuerto existente
                daoAeropuerto.editarAeropuerto(aeropuertoPublico);
            } else {
                // Se añade un nuevo aeropuerto
                daoAeropuerto.anadirAeropuerto(aeropuertoPublico);
            }

            // Actualizar la tabla de aeropuertos públicos
            tablaPublico.getItems().clear();
            tablaPublico.getItems().addAll(daoAeropuerto.leerAeropuertosPublicos());
        } else {
            // Crear objeto aeropuerto privado
            ModelAeropuertoPrivado aeropuertoPrivado = new ModelAeropuertoPrivado(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, numSocios);
            DaoAeropuertoPrivado daoAeropuertoPrivado = new DaoAeropuertoPrivado();

            // Verificar si el aeropuerto privado ya existe
            existe = daoAeropuertoPrivado.existeAeropuerto(aeropuertoPrivado);

            if (existe) {
                // Se edita el aeropuerto privado existente
                daoAeropuertoPrivado.editarAeropuerto(aeropuertoPrivado);
            } else {
                // Se añade un nuevo aeropuerto privado
                daoAeropuertoPrivado.anadirAeropuerto(aeropuertoPrivado);
            }
            // Actualizar la tabla de aeropuertos privados
            tablaPrivado.getItems().clear();
            tablaPrivado.getItems().addAll(daoAeropuertoPrivado.leerAeropuertosPrivados());
        }
        // Cierra el formulario
        cancelar(event);
    }


        /**
         * Valida los campos de texto para asegurar que no estén vacíos.
         * @param error Mensaje de error acumulado.
         * @return Mensaje de error actualizado.
         */
    private String validarStrings(String error) {
        if (txt_nombre.getText().isEmpty()) {
            error += "El campo nombre es obligatorio\n";
        }
        if (txt_pais.getText().isEmpty()) {
            error += "El campo país es obligatorio\n";
        }
        if (txt_ciudad.getText().isEmpty()) {
            error += "El campo ciudad es obligatorio\n";
        }
        return error;
    }
}
