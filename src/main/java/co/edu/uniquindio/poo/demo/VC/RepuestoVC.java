package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Repuesto;
import co.edu.uniquindio.poo.demo.Back.Estado;
import co.edu.uniquindio.poo.demo.Back.Repuesto;
import co.edu.uniquindio.poo.demo.Controller.RepuestoController;
import co.edu.uniquindio.poo.demo.Controller.RepuestoController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RepuestoVC {

    App app;
    RepuestoController repuestoController;
    private ObservableList<Repuesto> listaRepuesto = FXCollections.observableArrayList();
    private Repuesto selectedRepuesto;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttAgregarRepuesto;

    @FXML
    private Button bttEliminarRepuesto;

    @FXML
    private Button bttLimpiarRepuesto;

    @FXML
    private Button bttModificarRepuesto;

    @FXML
    private Button bttVolver;

    @FXML
    private TableView<Repuesto> tbRepuesto;

    @FXML
    private TableColumn<Repuesto, String> tbcID;

    @FXML
    private TableColumn<Repuesto, String> tbcMarca;

    @FXML
    private TableColumn<Repuesto, Double> tbcPrecio;

    @FXML
    private TableColumn<Repuesto, String> tbcNombre;

    @FXML
    private TableColumn<Repuesto, Integer> tbcStock;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    void AgregarRepuesto(ActionEvent event) {
        agregarRepuesto();
    }

    @FXML
    void EliminarRepuesto(ActionEvent event) {
        eliminarRepuesto();
    }

    @FXML
    void LimpiarRepuesto(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void ModificarRepuesto(ActionEvent event) {
        actualizarRepuesto();
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
        assert bttAgregarRepuesto != null : "fx:id=\"bttAgregarRepuesto\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert bttEliminarRepuesto != null : "fx:id=\"bttEliminarRepuesto\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert bttLimpiarRepuesto != null : "fx:id=\"bttLimpiarRepuesto\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert bttModificarRepuesto != null : "fx:id=\"bttModificarRepuesto\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert tbRepuesto != null : "fx:id=\"tbRepuesto\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert tbcID != null : "fx:id=\"tbcDireccion\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert tbcMarca != null : "fx:id=\"tbcDocumento\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert tbcPrecio != null : "fx:id=\"tbcEmail\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert tbcNombre != null : "fx:id=\"tbcNombre\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert tbcStock != null : "fx:id=\"tbcTelefono\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert txtPrecio != null : "fx:id=\"txtPrecio\" was not injected: check your FXML file 'Repuesto.fxml'.";
        assert txtStock != null : "fx:id=\"txtStock\" was not injected: check your FXML file 'Repuesto.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        repuestoController = new RepuestoController(App.taller);
        initView();
    }

    private void initView() {

        initDataBinding();
        obtenerRepuesto();
        tbRepuesto.getItems().clear();

        // Agregar los elementos a la tabla
        tbRepuesto.setItems(listaRepuesto);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    /**
     * Método que configura el enlace entre los datos y la vista.
     */

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initDataBinding() {
        tbcPrecio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        tbcStock.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStock()));
        tbcMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        tbcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdRepuesto()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
    }

    /**
     * Metodo que obtiene la lista de doctores del sistema.
     */

    private void obtenerRepuesto() {
        if (repuestoController != null) {
            listaRepuesto.addAll(repuestoController.obtenerListarespuesto());
        } else {
            System.err.println("doctorController no está inicializado.");
        }
    }

    /**
     * Método que configura un listener para la selección de elementos en la tabla de doctores.
     */

    private void listenerSelection() {
        tbRepuesto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedRepuesto = newSelection;
            mostrarInformacionRepuesto(selectedRepuesto);
        });
    }

    /**
     * Metodo que muestra la información del doctor  seleccionado en el campo de texto.
     */

    private void mostrarInformacionRepuesto(Repuesto Repuesto) {
        if (Repuesto != null) {
            txtID.setText(Repuesto.getIdRepuesto());
            txtMarca.setText(Repuesto.getMarca());
            txtStock.setText(String.valueOf(Repuesto.getStock()));
            txtNombre.setText(Repuesto.getNombre());
            txtPrecio.setText(String.valueOf(Repuesto.getPrecio()));
        }
    }

    /**
     * Metodo que agrega un nuevo doctor a la lista y lo almacena en el controlador.
     * Se crea un nuevo doctor a partir de los datos ingresados y se añade a la lista si la operación es exitosa.
     */

    private void agregarRepuesto() {
        Repuesto Repuesto = buildRepuesto();
        if(repuestoController.crearrespuesto(Repuesto)){
            listaRepuesto.add(Repuesto);
            limpiarCampos();
        }
    }

    /**
     * Metodo que construye un objeto de tipo doctor con la información ingresada.
     * @return Un objeto Doctor con el código ingresado en el campo de texto.
     */

    private Repuesto buildRepuesto() {
        double precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());

        Repuesto repuesto = new Repuesto(
                txtNombre.getText(),
                txtID.getText(),
                txtMarca.getText(),
                precio,
                stock
        );
        return repuesto;
    }


    /**
     * Metodo que elimina el doctor seleccionado de la lista y del controlador.
     * Si la eliminación es exitosa, también se eliminan los datos de la tabla y se limpian los campos de entrada.
     */

    private void eliminarRepuesto() {
        if (repuestoController.eliminarrespuesto(selectedRepuesto)) {
            listaRepuesto.remove(selectedRepuesto);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que actualiza la información del doctor seleccionado.
     * Si el Doctor se encuentra en la lista y la actualización es exitosa,
     * se reemplaza por el nuevo objeto actualizado y se refresca la tabla.
     */

    private void actualizarRepuesto() {

        if (selectedRepuesto != null &&
                repuestoController.actualizarRepuesto(selectedRepuesto.getIdRepuesto(), buildRepuesto())) {

            int index = listaRepuesto.indexOf(selectedRepuesto);
            if (index >= 0) {
                listaRepuesto.set(index, buildRepuesto());
            }

            tbRepuesto.refresh();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    /**
     * Metodo que limpia la selección de la tabla de doctor y los campos de entrada.
     */
    private void limpiarSeleccion() {
        tbRepuesto.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     * Metodo que limpia los campos de entrada y la selección de la tabla de Doctor.
     */
    private void limpiarCampos() {
        txtStock.clear();
        txtPrecio.clear();
        txtMarca.clear();
        txtID.clear();
        txtNombre.clear();
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

    }

}

