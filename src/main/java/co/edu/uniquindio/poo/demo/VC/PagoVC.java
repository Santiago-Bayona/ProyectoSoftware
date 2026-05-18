package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Pago;
import co.edu.uniquindio.poo.demo.Back.Estado;
import co.edu.uniquindio.poo.demo.Back.Pago;
import co.edu.uniquindio.poo.demo.Controller.PagoController;
import co.edu.uniquindio.poo.demo.Controller.PagoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PagoVC {

    App app;
    PagoController pagocontroller;
    private ObservableList<Pago> listaPago = FXCollections.observableArrayList();
    private Pago selectedPago;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttAgregarPago;

    @FXML
    private Button bttEliminarPago;

    @FXML
    private Button bttLimpiarPago;

    @FXML
    private Button bttModificarPago;

    @FXML
    private Button bttVolver;

    @FXML
    private ComboBox<Pago.EstadoPago> cbxEstado;

    @FXML
    private ComboBox<Pago.MetodoPago> cbxMetodo;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TableView<Pago> tbPago;

    @FXML
    private TableColumn<Pago, String> tbcEstado;

    @FXML
    private TableColumn<Pago, String> tbcFecha;

    @FXML
    private TableColumn<Pago, String> tbcID;

    @FXML
    private TableColumn<Pago, String> tbcMetodo;

    @FXML
    private TextField txtID;

    @FXML
    void AgregarPago(ActionEvent event) {
        agregarPago();
    }

    @FXML
    void EliminarPago(ActionEvent event) {
        eliminarPago();
    }

    @FXML
    void LimpiarPago(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void ModificarPago(ActionEvent event) {
        actualizarPago();
    }

    @FXML
    void Volver(ActionEvent event) {
        try {
            app.openViewPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert bttAgregarPago != null : "fx:id=\"bttAgregarPago\" was not injected: check your FXML file 'Pago.fxml'.";
        assert bttEliminarPago != null : "fx:id=\"bttEliminarPago\" was not injected: check your FXML file 'Pago.fxml'.";
        assert bttLimpiarPago != null : "fx:id=\"bttLimpiarPago\" was not injected: check your FXML file 'Pago.fxml'.";
        assert bttModificarPago != null : "fx:id=\"bttModificarPago\" was not injected: check your FXML file 'Pago.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Pago.fxml'.";
        assert cbxEstado != null : "fx:id=\"cbxEstado\" was not injected: check your FXML file 'Pago.fxml'.";
        assert cbxMetodo != null : "fx:id=\"cbxMetodo\" was not injected: check your FXML file 'Pago.fxml'.";
        assert dpFecha != null : "fx:id=\"dpFecha\" was not injected: check your FXML file 'Pago.fxml'.";
        assert tbPago != null : "fx:id=\"tbPago\" was not injected: check your FXML file 'Pago.fxml'.";
        assert tbcEstado != null : "fx:id=\"tbcEstado\" was not injected: check your FXML file 'Pago.fxml'.";
        assert tbcFecha != null : "fx:id=\"tbcFecha\" was not injected: check your FXML file 'Pago.fxml'.";
        assert tbcID != null : "fx:id=\"tbcID\" was not injected: check your FXML file 'Pago.fxml'.";
        assert tbcMetodo != null : "fx:id=\"tbcMetodo\" was not injected: check your FXML file 'Pago.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'Pago.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        pagocontroller = new PagoController(App.taller);
        initView();

    }

    private void initView() {

        initDataBinding();
        obtenerPago();
        tbPago.getItems().clear();

        // Agregar los elementos a la tabla
        tbPago.setItems(listaPago);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    /**
     * Método que configura el enlace entre los datos y la vista.
     */

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdPago()));
        tbcEstado.setCellValueFactory(celldata -> {
            Pago.EstadoPago categoria = celldata.getValue().getEstado();
            String categoriaString = (categoria != null) ? categoria.toString() : "Sin Tipo";
            return new SimpleStringProperty(categoriaString);
        });
        tbcMetodo.setCellValueFactory(celldata -> {
            Pago.MetodoPago categoria = celldata.getValue().getMetodo();
            String categoriaString = (categoria != null) ? categoria.toString() : "Sin Tipo";
            return new SimpleStringProperty(categoriaString);
        });
        tbcFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getFechaPago().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                )
        );

    }

    /**
     * Metodo que obtiene la lista de doctores del sistema.
     */

    private void obtenerPago() {
        if (pagocontroller != null) {
            listaPago.addAll(pagocontroller.obtenerListapago());
        } else {
            System.err.println("doctorController no está inicializado.");
        }
    }

    /**
     * Método que configura un listener para la selección de elementos en la tabla de doctores.
     */

    private void listenerSelection() {
        tbPago.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedPago = newSelection;
            mostrarInformacionPago(selectedPago);
        });
    }

    /**
     * Metodo que muestra la información del doctor  seleccionado en el campo de texto.
     */

    private void mostrarInformacionPago(Pago Pago) {
        if (Pago != null) {
            // Asignar valores a los campos de texto
            txtID.setText(Pago.getIdPago());
            dpFecha.setValue(Pago.getFechaPago());
            cbxEstado.getSelectionModel().select(Pago.getEstado());
            cbxMetodo.getSelectionModel().select(Pago.getMetodo());
        }
    }

    /**
     * Metodo que agrega un nuevo doctor a la lista y lo almacena en el controlador.
     * Se crea un nuevo doctor a partir de los datos ingresados y se añade a la lista si la operación es exitosa.
     */

    private void agregarPago() {
        Pago Pago = buildPago();
        if(pagocontroller.crearpago(Pago)){
            listaPago.add(Pago);
            limpiarCampos();
        }
    }

    /**
     * Metodo que construye un objeto de tipo doctor con la información ingresada.
     * @return Un objeto Doctor con el código ingresado en el campo de texto.
     */

    private Pago buildPago() {
        Pago Pago = new Pago(
                txtID.getText(),
                dpFecha.getValue(),
                cbxMetodo.getValue(),
                cbxEstado.getValue()
        );
        return Pago;
    }


    /**
     * Metodo que elimina el doctor seleccionado de la lista y del controlador.
     * Si la eliminación es exitosa, también se eliminan los datos de la tabla y se limpian los campos de entrada.
     */

    private void eliminarPago() {
        if (pagocontroller.eliminarpago(selectedPago)) {
            listaPago.remove(selectedPago);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que actualiza la información del doctor seleccionado.
     * Si el Doctor se encuentra en la lista y la actualización es exitosa,
     * se reemplaza por el nuevo objeto actualizado y se refresca la tabla.
     */

    private void actualizarPago() {

        if (selectedPago != null &&
                pagocontroller.actualizarpago(selectedPago.getIdPago(), buildPago())) {

            int index = listaPago.indexOf(selectedPago);
            if (index >= 0) {
                listaPago.set(index, buildPago());
            }

            tbPago.refresh();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    /**
     * Metodo que limpia la selección de la tabla de doctor y los campos de entrada.
     */
    private void limpiarSeleccion() {
        tbPago.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     * Metodo que limpia los campos de entrada y la selección de la tabla de Doctor.
     */
    private void limpiarCampos() {
        txtID.clear();
        dpFecha.setValue(null);
        cbxEstado.setValue(null);
        cbxMetodo.setValue(null);
    }

    /**
     * Establece la instancia de la aplicación para acceder a sus métodos.
     * @param app
     */

    public void setApp(App app) {
        this.app = app;

        /**
         * Metodo que inicializa la comboBox con las opciones de la especialidad del objeto Doctor
         */

        ObservableList<Pago.EstadoPago> options = FXCollections.observableArrayList(Pago.EstadoPago.values());
        cbxEstado.setItems((options));

        ObservableList<Pago.MetodoPago> options2 = FXCollections.observableArrayList(Pago.MetodoPago.values());
        cbxMetodo.setItems((options2));


    }

}

