package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Cliente;
import co.edu.uniquindio.poo.demo.Back.Estado;
import co.edu.uniquindio.poo.demo.Controller.ClienteController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClienteVC {

    App app;
    ClienteController clienteController;
    private ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
    private Cliente selectedCliente;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttAgregarCliente;

    @FXML
    private Button bttEliminarCliente;

    @FXML
    private Button bttLimpiarCliente;

    @FXML
    private Button bttModificarCliente;

    @FXML
    private Button bttVolver;

    @FXML
    private ComboBox<Estado> cbxEstado;

    @FXML
    private TableView<Cliente> tbCliente;

    @FXML
    private TableColumn<Cliente, String> tbcDireccion;

    @FXML
    private TableColumn<Cliente, String> tbcDocumento;

    @FXML
    private TableColumn<Cliente, String> tbcEmail;

    @FXML
    private TableColumn<Cliente, String> tbcEstado;

    @FXML
    private TableColumn<Cliente, String> tbcNombre;

    @FXML
    private TableColumn<Cliente, String> tbcTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void AgregarCliente(ActionEvent event) {
        agregarCliente();
    }

    @FXML
    void EliminarCliente(ActionEvent event) {
        eliminarCliente();
    }

    @FXML
    void LimpiarCliente(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void ModificarCliente(ActionEvent event) {
        actualizarCliente();
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
        assert bttAgregarCliente != null : "fx:id=\"bttAgregarCliente\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert bttEliminarCliente != null : "fx:id=\"bttEliminarCliente\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert bttLimpiarCliente != null : "fx:id=\"bttLimpiarCliente\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert bttModificarCliente != null : "fx:id=\"bttModificarCliente\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert cbxEstado != null : "fx:id=\"cbxEstado\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert tbCliente != null : "fx:id=\"tbCliente\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert tbcDireccion != null : "fx:id=\"tbcDireccion\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert tbcDocumento != null : "fx:id=\"tbcDocumento\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert tbcEmail != null : "fx:id=\"tbcEmail\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert tbcEstado != null : "fx:id=\"tbcEstado\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert tbcNombre != null : "fx:id=\"tbcNombre\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert tbcTelefono != null : "fx:id=\"tbcTelefono\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert txtDireccion != null : "fx:id=\"txtDireccion\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert txtDocumento != null : "fx:id=\"txtDocumento\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'Cliente.fxml'.";
        assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'Cliente.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        clienteController = new ClienteController(App.taller);
        initView();

    }

    private void initView() {

        initDataBinding();
        obtenerCliente();
        tbCliente.getItems().clear();

        // Agregar los elementos a la tabla
        tbCliente.setItems(listaCliente);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    /**
     * Método que configura el enlace entre los datos y la vista.
     */

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initDataBinding() {
        tbcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        tbcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefonono()));
        tbcDocumento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDocumento()));
        tbcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcEstado.setCellValueFactory(celldata -> {
            Estado categoria = celldata.getValue().getEstado();
            String categoriaString = (categoria != null) ? categoria.toString() : "Sin Tipo";
            return new SimpleStringProperty(categoriaString);
        });

    }

    /**
     * Metodo que obtiene la lista de doctores del sistema.
     */

    private void obtenerCliente() {
        if (clienteController != null) {
            listaCliente.addAll(clienteController.obtenerListacliente());
        } else {
            System.err.println("doctorController no está inicializado.");
        }
    }

    /**
     * Método que configura un listener para la selección de elementos en la tabla de doctores.
     */

    private void listenerSelection() {
        tbCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedCliente = newSelection;
            mostrarInformacionCliente(selectedCliente);
        });
    }

    /**
     * Metodo que muestra la información del doctor  seleccionado en el campo de texto.
     */

    private void mostrarInformacionCliente(Cliente cliente) {
        if (cliente != null) {
            // Asignar valores a los campos de texto
            txtDireccion.setText(cliente.getDireccion());
            txtDocumento.setText(cliente.getDocumento());
            txtEmail.setText(cliente.getEmail());
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefonono());
            cbxEstado.getSelectionModel().select(cliente.getEstado());
        }
    }

    /**
     * Metodo que agrega un nuevo doctor a la lista y lo almacena en el controlador.
     * Se crea un nuevo doctor a partir de los datos ingresados y se añade a la lista si la operación es exitosa.
     */

    private void agregarCliente() {
        Cliente cliente = buildCliente();
        if(clienteController.crearCliente(cliente)){
            listaCliente.add(cliente);
            limpiarCampos();
        }
    }

    /**
     * Metodo que construye un objeto de tipo doctor con la información ingresada.
     * @return Un objeto Doctor con el código ingresado en el campo de texto.
     */

    private Cliente buildCliente() {
        Cliente cliente = new Cliente(
                txtDocumento.getText(),
                txtNombre.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                txtDireccion.getText(),
                cbxEstado.getValue()
        );
        return cliente;
    }


    /**
     * Metodo que elimina el doctor seleccionado de la lista y del controlador.
     * Si la eliminación es exitosa, también se eliminan los datos de la tabla y se limpian los campos de entrada.
     */

    private void eliminarCliente() {
        if (clienteController.eliminarcliente(selectedCliente)) {
            listaCliente.remove(selectedCliente);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que actualiza la información del doctor seleccionado.
     * Si el Doctor se encuentra en la lista y la actualización es exitosa,
     * se reemplaza por el nuevo objeto actualizado y se refresca la tabla.
     */

    private void actualizarCliente() {

        if (selectedCliente != null &&
                clienteController.actualizarCliente(selectedCliente.getDocumento(), buildCliente())) {

            int index = listaCliente.indexOf(selectedCliente);
            if (index >= 0) {
                listaCliente.set(index, buildCliente());
            }

            tbCliente.refresh();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    /**
     * Metodo que limpia la selección de la tabla de doctor y los campos de entrada.
     */
    private void limpiarSeleccion() {
        tbCliente.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     * Metodo que limpia los campos de entrada y la selección de la tabla de Doctor.
     */
    private void limpiarCampos() {
        txtDocumento.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtDireccion.clear();
        txtNombre.clear();
        cbxEstado.setValue(null);
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

        ObservableList<Estado> options = FXCollections.observableArrayList(Estado.values());
        cbxEstado.setItems((options));
    }

}
