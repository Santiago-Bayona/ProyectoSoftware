package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Cliente;
import co.edu.uniquindio.poo.demo.Back.Estado;
import co.edu.uniquindio.poo.demo.Back.Vehiculo;
import co.edu.uniquindio.poo.demo.Controller.MecanicoController;
import co.edu.uniquindio.poo.demo.Controller.VehiculoController;
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

public class VehiculoVC {

    App app;
    VehiculoController vehiculoController;
    private ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();
    private ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
    private Vehiculo selectedVehiculo;
    private Cliente selectedCliente;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttAgregarVehiculo;

    @FXML
    private Button bttEliminarVehiculo;

    @FXML
    private Button bttLimpiarVehiculo;

    @FXML
    private Button bttModificarVehiculo;

    @FXML
    private Button bttVolver;

    @FXML
    private ComboBox<Vehiculo.Tipo> cbxTipo;

    @FXML
    private TableView<Vehiculo> tbVehiculo;

    @FXML
    private TableColumn<Vehiculo, String> tbcCliente;

    @FXML
    private TableColumn<Vehiculo, String> tbcKilometraje;

    @FXML
    private TableColumn<Vehiculo, String> tbcMarca;

    @FXML
    private TableColumn<Vehiculo, String> tbcModelo;

    @FXML
    private TableView<Cliente> tbCliente;

    @FXML
    private TableColumn<Cliente, String> tbcNombreCliente;

    @FXML
    private TableColumn<Vehiculo, String> tbcPlaca;

    @FXML
    private TableColumn<Vehiculo, String> tbcTipo;

    @FXML
    private TextField txtKilometraje;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtModelo;

    @FXML
    void AgregarVehiculo(ActionEvent event) {
        agregarvehiculo();
    }

    @FXML
    void EliminarVehiculo(ActionEvent event) {
        eliminarvehiculo();
    }

    @FXML
    void LimpiarVehiculo(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void ModificarVehiculo(ActionEvent event) {

    }

    @FXML
    void Volver(ActionEvent event) {
        try{
            app.openViewPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert bttAgregarVehiculo != null : "fx:id=\"bttAgregarVehiculo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert bttEliminarVehiculo != null : "fx:id=\"bttEliminarVehiculo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert bttLimpiarVehiculo != null : "fx:id=\"bttLimpiarVehiculo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert bttModificarVehiculo != null : "fx:id=\"bttModificarVehiculo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert cbxTipo != null : "fx:id=\"cbxTipo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbVehiculo != null : "fx:id=\"tbVehiculo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbcCliente != null : "fx:id=\"tbcCliente\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbcKilometraje != null : "fx:id=\"tbcKilometraje\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbcMarca != null : "fx:id=\"tbcMarca\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbcModelo != null : "fx:id=\"tbcModelo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbcPlaca != null : "fx:id=\"tbcPlaca\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbcTipo != null : "fx:id=\"tbcTipo\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert txtKilometraje != null : "fx:id=\"txtDocumento\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert txtMarca != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert txtModelo != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert txtPlaca != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbCliente != null : "fx:id=\"tbCliente\" was not injected: check your FXML file 'Vehiculo.fxml'.";
        assert tbcNombreCliente != null : "fx:id=\"tbcNombreCliente\" was not injected: check your FXML file 'Vehiculo.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        vehiculoController = new VehiculoController(App.taller);
        obtenerCliente();
        initView();
    }

    private void obtenerCliente() {
        if (listaCliente == null) {
            listaCliente = FXCollections.observableArrayList();
        }

        Collection<Cliente> clientes = vehiculoController.obtenerListaCliente();
        if (clientes != null) {
            listaCliente.setAll(clientes);
        } else {
            System.out.println("No se encontraron jugadpres.");
        }
    }


    /**
     *Metodo que permite obtener los datos de paciente
     */
    private void obtenervehiculo() {
        if (vehiculoController != null) {
            listaVehiculos.addAll(vehiculoController.obtenerListavehiculo());
        } else {
            System.err.println("PacienteController no está inicializado.");
        }
    }


    /**
     *Metodo que inicializa la vista en javaFX
     */
    private void initView() {
        initDataBinding();
        //obtenervehiculo();
        tbVehiculo.getItems().clear();
        tbVehiculo.setItems(listaVehiculos);
        tbCliente.setItems(listaCliente);
        listenerSelectionvehiculo();
        listenerSelectionCliente();
    }

    /**
     *Metodo que configura la vinculacion de datos en las tablas
     */
    private void initDataBinding() {
        tbcPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
        tbcKilometraje.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKilometraje()));
        tbcCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuenio().getNombre()));
        tbcMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        tbcModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        tbcNombreCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcTipo.setCellValueFactory(celldata -> {
            Vehiculo.Tipo categoria = celldata.getValue().getTipo();
            String categoriaString = (categoria != null) ? categoria.toString() : "Sin Tipo";
            return new SimpleStringProperty(categoriaString);
        });


    }

    /**
     *Metodo que escucha los cambios en la selecion de paciente y actualiza la variable selectedPaciente
     */
    private void listenerSelectionvehiculo() {
        tbVehiculo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedVehiculo = newSelection;
            mostarInformacionvehiculo(selectedVehiculo);
        });
    }

    /**
     *Metodo que detecta cuando el usuario selecciona un historial medico en la tabla
     */
    private void listenerSelectionCliente() {
        tbCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedCliente = newSelection;
        });
    }


    /**
     *Metodo que muestra la información del paciente sleccionado en los campos de la interfaz
     * @param Vehiculo
     */
    private void mostarInformacionvehiculo(Vehiculo Vehiculo) {
        if (Vehiculo != null) {
            txtModelo.setText(Vehiculo.getModelo());
            txtPlaca.setText(Vehiculo.getPlaca());
            txtMarca.setText(Vehiculo.getMarca());
            txtKilometraje.setText(Vehiculo.getKilometraje());
            cbxTipo.getSelectionModel().select(Vehiculo.getTipo());
        }
    }

    /**
     *Metodo que agrega un paciente
     */
    private void agregarvehiculo() {
        Vehiculo Vehiculo = buildvehiculo();
        if(vehiculoController.crearVehiculo(Vehiculo)){
            listaVehiculos.add(Vehiculo);
            limpiarCampos();
        }
    }

    /**
     *Metodo que crea una instancia de Paciente con los datos ingresados en la interfaz
     * @return
     */
    private Vehiculo buildvehiculo() {
        Vehiculo Vehiculo = new Vehiculo(
                txtPlaca.getText(),
                txtMarca.getText(),
                txtModelo.getText(),
                txtKilometraje.getText(),
                selectedCliente,
                cbxTipo.getValue()
        );
        return Vehiculo;

    }


    /**
     *Metodo que elimina un paciente
     */
    private void eliminarvehiculo() {
        Vehiculo Vehiculo = buildvehiculo();
        if (vehiculoController.eliminarvehiculo(Vehiculo)){
            listaVehiculos.remove(selectedVehiculo);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que limpia la seleccion de la tabla
     */
    private void limpiarSeleccion() {
        tbVehiculo.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     *Metodo qi¿ue limpia los acmpos del paciente seleccionado
     */
    private void limpiarCampos() {
        txtKilometraje.clear();
        txtPlaca.clear();
        txtMarca.clear();
        txtModelo.clear();
        cbxTipo.setValue(null);
        tbCliente.getSelectionModel().clearSelection();
    }

    /**
     *metodo que seta APP
     * @param app
     */
    public void setApp(App app) {
        this.app = app;

        ObservableList<Vehiculo.Tipo> options = FXCollections.observableArrayList(Vehiculo.Tipo.values());
        cbxTipo.setItems((options));
    }

}

