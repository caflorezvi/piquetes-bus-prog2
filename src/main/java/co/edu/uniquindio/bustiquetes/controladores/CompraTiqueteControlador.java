package co.edu.uniquindio.bustiquetes.controladores;

import co.edu.uniquindio.bustiquetes.modelo.EmpresaTransporte;
import co.edu.uniquindio.bustiquetes.modelo.RutaBus;
import co.edu.uniquindio.bustiquetes.modelo.Tiquete;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase que representa el controlador de la vista de compra de tiquetes
 */
public class CompraTiqueteControlador implements Initializable {

    @FXML
    public TableView<RutaBus> tablaRutas;
    @FXML
    public TableColumn<RutaBus, String> colOrigen;
    @FXML
    public TableColumn<RutaBus, String> colDestino;
    @FXML
    public TableColumn<RutaBus, String> colTipoBus;
    @FXML
    public TableColumn<RutaBus, String> colPrecio;
    @FXML
    public TableColumn<RutaBus, String> colAsientosDisponibles;
    @FXML
    public TableColumn<RutaBus, String> colFechaSalida;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtDocumento;
    @FXML
    public TextField txtEdad;
    @FXML
    public TextField txtTelefono;
    @FXML
    public TextField txtNumeroAsiento;

    private ObservableList<RutaBus> observableRutasBuses;

    private final EmpresaTransporte empresaTransporte;

    public CompraTiqueteControlador(){
        empresaTransporte = new EmpresaTransporte();
    }

    public void comprarTiquete(ActionEvent actionEvent) {

        try {

            // Validar que se haya seleccionado una ruta en la tabla
            RutaBus rutaBus = tablaRutas.getSelectionModel().getSelectedItem();

            if(rutaBus == null){
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe seleccionar una ruta");
                return;
            }

            // Obtener los datos del formulario
            String cedula = txtDocumento.getText();
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String telefono = txtTelefono.getText();
            int numeroAsiento = Integer.parseInt(txtNumeroAsiento.getText());
            String idRuta = rutaBus.getId();

            // Realizar la compra del tiquete por medio de la empresa de transporte
            Tiquete tiquete = empresaTransporte.realizarCompra(
                    cedula,
                    nombre,
                    telefono,
                    edad,
                    idRuta,
                    numeroAsiento

            );

            // Actualizar la tabla
            actualizarTabla();

            // Mostrar mensaje de compra exitosa
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Compra exitosa",
                    "La compra se realizo con exito, esta es la información del tiquete:\n\n"+tiquete);

        }catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colOrigen.setCellValueFactory(rutaBus -> new SimpleStringProperty(rutaBus.getValue().getRuta().getOrigen()));
        colDestino.setCellValueFactory(rutaBus -> new SimpleStringProperty(rutaBus.getValue().getRuta().getDestino()));
        colTipoBus.setCellValueFactory(rutaBus -> new SimpleStringProperty(rutaBus.getValue().getBus().getTipo()));
        colFechaSalida.setCellValueFactory(rutaBus -> new SimpleStringProperty(rutaBus.getValue().getFechaSalida().toString()));
        colPrecio.setCellValueFactory(rutaBus -> new SimpleStringProperty("$"+rutaBus.getValue().calcularValorTiquete()));
        colAsientosDisponibles.setCellValueFactory(rutaBus -> new SimpleStringProperty(""+rutaBus.getValue().obtenerAsientosDisponibles()));

        observableRutasBuses = FXCollections.observableArrayList();
        tablaRutas.setItems(observableRutasBuses);

        actualizarTabla();

    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void actualizarTabla(){
        observableRutasBuses.setAll(empresaTransporte.listarRutasBuses());
    }

}