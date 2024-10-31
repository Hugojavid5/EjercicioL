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

    private TableView<ModelAeropuertoPrivado> tablaPrivado;
    private TableView<ModelAeropuertoPublico> tablaPublico;
    public void setTablaPrivado(TableView<ModelAeropuertoPrivado> tablaPrivado) {
        this.tablaPrivado = tablaPrivado;
    }

    public void setTablaPublico(TableView<ModelAeropuertoPublico> tablaPublico) {
        this.tablaPublico = tablaPublico;
    }
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

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
        error = validarStrings(error);
        if (txt_numero.getText().isEmpty()) {
            error += "El campo numero es obligatorio\n";
        } else {
            try {
                numero = Integer.parseInt(txt_numero.getText());
                if (numero <= 0) {
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                error += "El numero de la calle debe ser un numero\n";
            } catch (Exception e) {
                error += "El numero de la calle no puede ser menor que 1\n";
            }
        }
        if (txt_anioDeInauguracion.getText().isEmpty()) {
            error += "El campo año de inauguracion es obligatorio\n";
        } else {
            try {
                anioInauguracion = Integer.parseInt(txt_anioDeInauguracion.getText());
                if (anioInauguracion < 1900) {
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                error += "El año de inauguracion debe ser un numero\n";
            } catch (Exception e) {
                error += "El año de inauguracion no puede ser menor al 1900\n";
            }
        }
        if (txt_capacidad.getText().isEmpty()) {
            error += "El campo capacidad es obligatorio\n";
        } else {
            try {
                capacidad = Integer.parseInt(txt_capacidad.getText());
                if (capacidad <= 0) {
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                error += "La capacidad debe ser un numero\n";
            } catch (Exception e) {
                error += "La capacidad no puede ser menor a 1\n";
            }
        }
        if (esPublico) {
            if (txt_financiacion.getText().isEmpty()) {
                error += "El campo financiacion es obligatorio\n";
            } else {
                if (!txt_financiacion.getText().matches("^\\d{1,10}(\\.\\d{1,2})?$")) {
                    error += "La financiacion puede tener como mucho 10 digitos antes del punto y 2 despues\n";
                } else {
                    try {
                        financiacion = Float.parseFloat(txt_financiacion.getText());
                        if (financiacion <= 0) {
                            throw new Exception();
                        }
                    } catch (NumberFormatException e) {
                        error += "La financiacion debe ser un numero\n";
                    } catch (Exception e) {
                        error += "La financiacion no puede ser menor que 1\n";
                    }
                }
            }
        }
        if (esPublico) {
            if (txt_trabajadores.getText().isEmpty()) {
                error += "El campo numero de trabajadores es obligatorio\n";
            } else {
                try {
                    numTrabajadores = Integer.parseInt(txt_trabajadores.getText());
                    if (numTrabajadores <= 0) {
                        throw new Exception();
                    }
                } catch (NumberFormatException e) {
                    error += "El numero de trabajadores debe ser un numero\n";
                } catch (Exception e) {
                    error += "El numero de trabajadores no puede ser menor que 1\n";
                }
            }
        } else {
            if (txt_numeroDeSocios.getText().isEmpty()) {
                error += "El campo Nº socios es obligatorio\n";
            } else {
                try {
                    numSocios = Integer.parseInt(txt_numeroDeSocios.getText());
                    if (numSocios <= 0) {
                        throw new Exception();
                    }
                } catch (NumberFormatException e) {
                    error += "El numero de socios debe ser un numero\n";
                } catch (Exception e) {
                    error += "El numero de socios no puede ser menor que 1\n";
                }
            }
        }
        // Una vez validado
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setHeaderText(null);
        if (ListarAeropuertoController.isEsAniadir()) {
            aniadirAeropuerto(error, nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
                    financiacion, numTrabajadores, numSocios, existe, al);
        } else {
            modificarAeropuerto(error, nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
                    financiacion, numTrabajadores, numSocios, existe, al);
        }
        al.showAndWait();
        tablaPrivado.getSelectionModel().clearSelection();
        tablaPublico.getSelectionModel().clearSelection();
        ListarAeropuertoController.getS().close();
    }

    private void modificarAeropuerto(String error, String nombre, String pais, String ciudad, String calle, int numero, int anioInauguracion, int capacidad, boolean esPublico, float financiacion, int numTrabajadores, int numSocios, boolean existe, Alert al) {
    }

    void aniadirAeropuerto(String error, String nombre, String pais, String ciudad, String calle, int numero,
                           int anioInauguracion, int capacidad, boolean esPublico, float financiacion, int numTrabajadores,
                           int numSocios, boolean existe, Alert al) {
        existe = validarExistencia(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
                financiacion, numTrabajadores, numSocios, existe);
        if (error.equals("") && !existe) {
            Integer idDireccion = DaoDireccion.conseguirID(pais, ciudad, calle, numero);
            if (idDireccion == null) {
                DaoDireccion.aniadir(pais, ciudad, calle, numero);
                idDireccion = DaoDireccion.conseguirID(pais, ciudad, calle, numero);
            }
            Integer idAeropuerto = DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad, idDireccion, null);
            if (idAeropuerto == null) {
                DaoAeropuerto.aniadir(nombre, anioInauguracion, capacidad, idDireccion, null);
                idAeropuerto = DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad, idDireccion, null);
            }
            if (esPublico) {
                DaoAeropuertoPublico.aniadir(idAeropuerto, financiacion, numTrabajadores);
                ListarAeropuertoController.setListaTodasPublico(DaoAeropuertoPublico.cargarListaAeropuertosPublicos());
                tablaPublico.refresh();
            } else {
                DaoAeropuertoPrivado.aniadir(idAeropuerto, numSocios);
                ListarAeropuertoController.setListaTodasPrivado(DaoAeropuertoPrivado.cargarListaAeropuertosPrivados());
                tablaPrivado.refresh();
            }
            al.setAlertType(AlertType.INFORMATION);
            al.setContentText("Se ha añadido el aeropuerto correctamente");
        } else if (existe) {
            al.setAlertType(AlertType.ERROR);
            al.setContentText("El aeropuerto ya existe");
        } else {
            al.setAlertType(AlertType.ERROR);
            al.setContentText(error);
        }
    }

    public String validarStrings(String error) {
        if (txt_nombre.getText().isEmpty()) {
            error += "El campo nombre es obligatorio\n";
        }
        if (txt_pais.getText().isEmpty()) {
            error += "El campo pais es obligatorio\n";
        }
        if (txt_ciudad.getText().isEmpty()) {
            error += "El campo ciudad es obligatorio\n";
        }
        if (txt_calle.getText().isEmpty()) {
            error += "El campo calle es obligatorio\n";
        }
        return error;
    }

    private boolean validarExistencia(String nombre, String pais, String ciudad, String calle, int numero,
                                      int anioInauguracion, int capacidad, boolean esPublico, float financiacion,
                                      int numTrabajadores, int numSocios, boolean existe) {
        // Implementación de la validación de existencia aquí
        return existe;
    }
}
