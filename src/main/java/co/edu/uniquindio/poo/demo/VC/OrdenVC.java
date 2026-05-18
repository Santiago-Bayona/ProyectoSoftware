package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.*;
import co.edu.uniquindio.poo.demo.Back.Vehiculo;
import co.edu.uniquindio.poo.demo.Controller.OrdenController;
import co.edu.uniquindio.poo.demo.Controller.VehiculoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OrdenVC {

    App app;
    OrdenController ordenController;
    private ObservableList<Orden> listaOrden = FXCollections.observableArrayList();
    private ObservableList<Vehiculo> listaVehiculo = FXCollections.observableArrayList();
    private ObservableList<Mecanico> listaMecanicos = FXCollections.observableArrayList();
    private Orden selectedOrden;
    private Vehiculo selectedVehiculo;
    private Mecanico selectedMecanico;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttAgregarOrden;

    @FXML
    private Button bttEliminarOrden;

    @FXML
    private Button bttLimpiarOrden;

    @FXML
    private Button bttModificarOrden;

    @FXML
    private Button bttVolver;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TableView<Mecanico> tbMecanico;

    @FXML
    private TableView<Orden> tbOrden;

    @FXML
    private TableColumn<Vehiculo, String> tbPlaca;

    @FXML
    private TableView<Vehiculo> tbVehiculo;

    @FXML
    private TableColumn<Orden, String> tbcCliente;

    @FXML
    private TableColumn<Orden, String> tbcFecha;

    @FXML
    private TableColumn<Orden, String> tbcID;

    @FXML
    private TableColumn<Orden, String> tbcMecanicos;

    @FXML
    private TableColumn<Mecanico, String> tbcNombreMecanico;

    @FXML
    private TableColumn<Orden, String> tbcVehiculo;

    @FXML
    private TextField txtID;

    @FXML
    void AgregarOrden(ActionEvent event) {
        agregarorden();
    }

    @FXML
    void EliminarOrden(ActionEvent event) {
        eliminarorden();
    }

    @FXML
    void LimpiarOrden(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void ModificarOrden(ActionEvent event) {
        modificarOrden();
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
        assert bttAgregarOrden != null : "fx:id=\"bttAgregarOrden\" was not injected: check your FXML file 'Orden.fxml'.";
        assert bttEliminarOrden != null : "fx:id=\"bttEliminarOrden\" was not injected: check your FXML file 'Orden.fxml'.";
        assert bttLimpiarOrden != null : "fx:id=\"bttLimpiarOrden\" was not injected: check your FXML file 'Orden.fxml'.";
        assert bttModificarOrden != null : "fx:id=\"bttModificarOrden\" was not injected: check your FXML file 'Orden.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Orden.fxml'.";
        assert dpFecha != null : "fx:id=\"dpFecha\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbMecanico != null : "fx:id=\"tbMecanico\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbOrden != null : "fx:id=\"tbOrden\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbPlaca != null : "fx:id=\"tbPlaca\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbVehiculo != null : "fx:id=\"tborden\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbcCliente != null : "fx:id=\"tbcCliente\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbcFecha != null : "fx:id=\"tbcFecha\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbcID != null : "fx:id=\"tbcID\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbcMecanicos != null : "fx:id=\"tbcMecanicos\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbcNombreMecanico != null : "fx:id=\"tbcNombreMecanico\" was not injected: check your FXML file 'Orden.fxml'.";
        assert tbcVehiculo != null : "fx:id=\"tbcorden\" was not injected: check your FXML file 'Orden.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'Orden.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        ordenController = new OrdenController(App.taller);
        obtenerorden();
        obtenerVehiculon();
        obtenerMecanicos();
        initView();
    }

    private void obtenerMecanicos() {
        if (listaMecanicos == null) {
            listaMecanicos = FXCollections.observableArrayList();
        }

        Collection<Mecanico> mecanicos = ordenController.obtenerListaMecanicos();
        if (mecanicos != null) {
            listaMecanicos.setAll(mecanicos);
        } else {
            System.out.println("No se encontraron mecánicos.");
        }
    }


    private void obtenerVehiculon() {
        if (listaVehiculo == null) {
            listaVehiculo = FXCollections.observableArrayList();
        }

        Collection<Vehiculo> vehiculos = ordenController.obtenerListaVehiculo();
        if (vehiculos != null) {
            listaVehiculo.setAll(vehiculos);
        } else {
            System.out.println("No se encontraron jugadpres.");
        }
    }


    /**
     *Metodo que permite obtener los datos de paciente
     */
    private void obtenerorden() {
        if (ordenController != null) {
            Collection<Orden> ords = ordenController.obtenerListaOrden();
            if (ords != null) {
                listaOrden.clear();
                listaOrden.addAll(ords);
            }
        } else {
            System.err.println("PacienteController no está inicializado.");
        }
    }


    /**
     *Metodo que inicializa la vista en javaFX
     */
    private void initView() {
        initDataBinding();
        tbOrden.getItems().clear();
        tbOrden.setItems(listaOrden);
        tbVehiculo.setItems(listaVehiculo);
        tbMecanico.setItems(listaMecanicos);
        tbMecanico.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listenerSelectionorden();
        listenerSelectionVehiculo();
        listenerSelectionMecanico();
    }


    /**
     *Metodo que configura la vinculacion de datos en las tablas
     */
    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdOrden()));
        tbcFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                )
        );
        tbcCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehiculo().getDuenio().getNombre()));
        tbcVehiculo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehiculo().getPlaca()));
        tbPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
        tbcNombreMecanico.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcMecanicos.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombresMecanicos())
        );


    }

    /**
     *Metodo que escucha los cambios en la selecion de paciente y actualiza la variable selectedPaciente
     */
    private void listenerSelectionorden() {
        tbOrden.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedOrden = newSelection;
            mostarInformacionorden(selectedOrden);
        });
    }


    private void listenerSelectionVehiculo() {
        tbVehiculo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedVehiculo = newSelection;
        });
    }

    private void listenerSelectionMecanico() {
        tbMecanico.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedMecanico = newSelection;
        });
    }


    /**
     *Metodo que muestra la información del paciente sleccionado en los campos de la interfaz
     * @param orden
     */
    private void mostarInformacionorden(Orden orden) {
        if (orden != null) {
            txtID.setText(orden.getIdOrden());
            dpFecha.setValue(orden.getFecha());

            // Selecciona automáticamente los mecánicos de la orden en la tabla
            tbMecanico.getSelectionModel().clearSelection();
            for (Mecanico mecanico : orden.getMecanicos()) {
                int index = listaMecanicos.indexOf(mecanico);
                if (index >= 0) {
                    tbMecanico.getSelectionModel().select(index);
                }
            }
        }
    }

    /**
     *Metodo que agrega un paciente
     */
    private void agregarorden() {
        // Validar que se haya seleccionado un vehículo
        if (selectedVehiculo == null) {
            System.err.println("Error: Debe seleccionar un vehículo.");
            return;
        }

        // Validar que se haya ingresado un ID
        if (txtID.getText().isEmpty()) {
            System.err.println("Error: Debe ingresar un ID de orden.");
            return;
        }

        // Validar que se haya seleccionado una fecha
        if (dpFecha.getValue() == null) {
            System.err.println("Error: Debe seleccionar una fecha.");
            return;
        }

        Orden orden = buildorden();
        if (orden != null && ordenController.crearOrden(orden)) {
            listaOrden.add(orden);
            limpiarCampos();
        }
    }

    /**
     *Metodo que crea una instancia de Paciente con los datos ingresados en la interfaz
     * @return
     */
    private Orden buildorden() {
        if (selectedVehiculo == null || txtID.getText().isEmpty() || dpFecha.getValue() == null) {
            System.err.println("Error: Datos incompletos.");
            return null;
        }

        Orden orden = new Orden(
                txtID.getText(),
                selectedVehiculo,
                dpFecha.getValue()
        );

        // Agregar los mecánicos seleccionados en la tabla de mecánicos
        // El usuario puede seleccionar múltiples mecánicos con Ctrl+Click
        ObservableList<Mecanico> mecanicosSeleccionados = tbMecanico.getSelectionModel().getSelectedItems();
        for (Mecanico mecanico : mecanicosSeleccionados) {
            orden.agregarMecanico(mecanico);
        }

        return orden;
    }


    /**
     *Metodo que elimina un paciente
     */
    private void eliminarorden() {
        if (selectedOrden == null) {
            System.err.println("Error: Debe seleccionar una orden para eliminar.");
            return;
        }

        if (ordenController.eliminarOrden(selectedOrden)) {
            listaOrden.remove(selectedOrden);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void modificarOrden() {
        if (selectedOrden == null) {
            System.err.println("Error: Debe seleccionar una orden para modificar.");
            return;
        }

        Orden ordenActualizada = buildorden();
        if (ordenActualizada != null && ordenController.actualizarOrden(selectedOrden.getIdOrden(), ordenActualizada)) {
            int index = listaOrden.indexOf(selectedOrden);
            listaOrden.set(index, ordenActualizada);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que limpia la seleccion de la tabla
     */
    private void limpiarSeleccion() {
        tbMecanico.getSelectionModel().clearSelection();
        tbVehiculo.getSelectionModel().clearSelection();
        tbOrden.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     *Metodo qi¿ue limpia los acmpos del paciente seleccionado
     */
    private void limpiarCampos() {
        txtID.clear();
        dpFecha.setValue(null);
        tbVehiculo.getSelectionModel().clearSelection();
        tbMecanico.getSelectionModel().clearSelection();
    }

    /**
     *metodo que seta APP
     * @param app
     */
    public void setApp(App app) {
        this.app = app;
    }

}

