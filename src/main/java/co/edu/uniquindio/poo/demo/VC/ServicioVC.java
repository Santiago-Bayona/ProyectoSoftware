package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.*;
import co.edu.uniquindio.poo.demo.Controller.ServicioController;
import co.edu.uniquindio.poo.demo.Controller.ServicioController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ServicioVC {

    App app;
    ServicioController servicioController;
    private ObservableList<Servicio> listaServicio = FXCollections.observableArrayList();
    private ObservableList<Orden> listaOrden = FXCollections.observableArrayList();
    private ObservableList<Repuesto> listaRepuestos = FXCollections.observableArrayList();
    private Servicio selectedServicio;
    private Repuesto selectedRepuesto;
    private Orden selectedOrden;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttAgregarServicio;

    @FXML
    private Button bttEliminarServicio;

    @FXML
    private Button bttLimpiarServicio;

    @FXML
    private Button bttModificarServicio;

    @FXML
    private Button bttVolver;

    @FXML
    private ComboBox<Servicio.EstadoServicio> cbxEstado;

    @FXML
    private TableColumn<Orden, String> tbIDOrden;

    @FXML
    private TableColumn<Repuesto, String> tbNombreRepuestos;

    @FXML
    private TableView<Servicio> tbServicio;

    @FXML
    private TableView<Repuesto> tbRepuestos;

    @FXML
    private TableView<Orden> tbOrden;

    @FXML
    private TableColumn<Servicio, String> tbcEstado;

    @FXML
    private TableColumn<Servicio, String > tbcID;

    @FXML
    private TableColumn<Servicio, String> tbcOrden;

    @FXML
    private TableColumn<Servicio, Double> tbcPrecioMO;

    @FXML
    private TableColumn<Servicio, Double> tbcPrecioR;

    @FXML
    private TableColumn<Servicio, String> tbcRepuestos;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtPrecioMO;

    @FXML
    void AgregarServicio(ActionEvent event) {
        agregarServicio();
    }

    @FXML
    void EliminarServicio(ActionEvent event) {
            eliminarServicio();
    }

    @FXML
    void LimpiarServicio(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void ModificarServicio(ActionEvent event) {
        modificarServicio();
    }

    @FXML
    void Volver(ActionEvent event) {
        try{
            app.openViewPrincipal();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert bttAgregarServicio != null : "fx:id=\"bttAgregarServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert bttEliminarServicio != null : "fx:id=\"bttEliminarServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert bttLimpiarServicio != null : "fx:id=\"bttLimpiarServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert bttModificarServicio != null : "fx:id=\"bttModificarServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert cbxEstado != null : "fx:id=\"cbxEstado\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbOrden != null : "fx:id=\"tbIDServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbNombreRepuestos != null : "fx:id=\"tbNombreRepuestos\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbServicio != null : "fx:id=\"tbServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbRepuestos != null : "fx:id=\"tbRepuestos\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbIDOrden != null : "fx:id=\"tbServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbcEstado != null : "fx:id=\"tbcEstado\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbcID != null : "fx:id=\"tbcID\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbcOrden != null : "fx:id=\"tbcServicio\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbcPrecioMO != null : "fx:id=\"tbcPrecioMO\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbcPrecioR != null : "fx:id=\"tbcPrecioR\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert tbcRepuestos != null : "fx:id=\"tbcRepuestos\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert txtDescripcion != null : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'Servicio.fxml'.";
        assert txtPrecioMO != null : "fx:id=\"txtPrecioMO\" was not injected: check your FXML file 'Servicio.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        servicioController = new ServicioController(App.taller);
        obtenerOrden();
        obtenerServicio();
        obtenerRepuestos();
        initView();
        
    }

    private void obtenerRepuestos() {
        if (listaRepuestos == null) {
            listaRepuestos = FXCollections.observableArrayList();
        }

        Collection<Repuesto> repuestos = servicioController.obtenerListaRepuestos();
        if (repuestos != null) {
            listaRepuestos.setAll(repuestos);
        } else {
            System.out.println("No se encontraron mecánicos.");
        }
    }


    private void obtenerOrden() {
        if (listaOrden == null) {
            listaOrden = FXCollections.observableArrayList();
        }

        Collection<Orden> ordenes = servicioController.obtenerListaOrden();
        if (ordenes != null) {
            listaOrden.setAll(ordenes);
        } else {
            System.out.println("No se encontraron jugadpres.");
        }
    }


    /**
     *Metodo que permite obtener los datos de paciente
     */
    private void obtenerServicio() {
        if (servicioController != null) {
            Collection<Servicio> ords = servicioController.obtenerListaServicio();
            if (ords != null) {
                listaServicio.clear();
                listaServicio.addAll(ords);
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
        tbServicio.getItems().clear();
        tbServicio.setItems(listaServicio);
        tbOrden.setItems(listaOrden);
        tbRepuestos.setItems(listaRepuestos);
        tbRepuestos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listenerSelectionServicio();
        listenerSelectionRepuesto();
        listenerSelectionOrden();
    }


    /**
     *Metodo que configura la vinculacion de datos en las tablas
     */
    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdServicio()));
        tbcOrden.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrden().getIdOrden()));
        tbcPrecioMO.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrecioManoObra()));
        tbcPrecioR.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().calcularPrecioRepuestos()));
        tbNombreRepuestos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcRepuestos.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombresRepuestos())
        );
        tbIDOrden.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdOrden()));
        tbcEstado.setCellValueFactory(celldata -> {
            Servicio.EstadoServicio categoria = celldata.getValue().getEstadoServicio();
            String categoriaString = (categoria != null) ? categoria.toString() : "Sin Tipo";
            return new SimpleStringProperty(categoriaString);
        });


    }

    /**
     *Metodo que escucha los cambios en la selecion de paciente y actualiza la variable selectedPaciente
     */
    private void listenerSelectionServicio() {
        tbServicio.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedServicio = newSelection;
            mostarInformacionServicio(selectedServicio);
        });
    }


    private void listenerSelectionOrden() {
        tbOrden.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedOrden = newSelection;
        });
    }

    private void listenerSelectionRepuesto() {
        tbRepuestos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedRepuesto = newSelection;
        });
    }


    /**
     *Metodo que muestra la información del paciente sleccionado en los campos de la interfaz
     * @param Servicio
     */
    private void mostarInformacionServicio(Servicio Servicio) {
        if (Servicio != null) {
            txtID.setText(Servicio.getIdServicio());
            txtDescripcion.setText(Servicio.getDescripcion());
            txtPrecioMO.setText(String.valueOf(Servicio.getPrecioManoObra()));
            cbxEstado.setValue(Servicio.getEstadoServicio());

            // Selecciona automáticamente los mecánicos de la Servicio en la tabla
            tbRepuestos.getSelectionModel().clearSelection();
            for (Repuesto repuesto : Servicio.getRepuestos()) {
                int index = listaRepuestos.indexOf(repuesto);
                if (index >= 0) {
                    tbRepuestos.getSelectionModel().select(index);
                }
            }
        }
    }

    /**
     *Metodo que agrega un paciente
     */
    private void agregarServicio() {
        // Validar que se haya seleccionado un vehículo
        if (selectedOrden == null) {
            System.err.println("Error: Debe seleccionar un vehículo.");
            return;
        }

        // Validar que se haya ingresado un ID
        if (txtID.getText().isEmpty()) {
            System.err.println("Error: Debe ingresar un ID de Servicio.");
            return;
        }

        // Validar que se haya seleccionado una fecha
        if (cbxEstado.getValue() == null) {
            System.err.println("Error: Debe seleccionar una fecha.");
            return;
        }

        Servicio servicio = buildServicio();

        if (servicio != null) {

            servicio.descontarStockRepuestos();

            if (servicioController.crearServicio(servicio)) {
                listaServicio.add(servicio);
                limpiarCampos();
            }
        }
    }

    /**
     *Metodo que crea una instancia de Paciente con los datos ingresados en la interfaz
     * @return
     */
    private Servicio buildServicio() {
        if (selectedOrden == null || txtID.getText().isEmpty() || cbxEstado.getValue() == null) {
            System.err.println("Error: Datos incompletos.");
            return null;
        }
        Double precio = Double.parseDouble(txtPrecioMO.getText());

        Servicio Servicio = new Servicio(
                txtID.getText(),
                txtDescripcion.getText(),
                precio,
                cbxEstado.getValue(),
                selectedOrden
        );

        // Agregar los mecánicos seleccionados en la tabla de mecánicos
        // El usuario puede seleccionar múltiples mecánicos con Ctrl+Click
        ObservableList<Repuesto> repuestosSeleccionados = tbRepuestos.getSelectionModel().getSelectedItems();
        for (Repuesto repuesto : repuestosSeleccionados) {
            Servicio.agregarRepuesto(repuesto);
        }

        return Servicio;
    }


    /**
     *Metodo que elimina un paciente
     */
    private void eliminarServicio() {
        if (selectedServicio == null) {
            System.err.println("Error: Debe seleccionar una Servicio para eliminar.");
            return;
        }

        if (servicioController.eliminarServicio(selectedServicio)) {
            listaServicio.remove(selectedServicio);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void modificarServicio() {
        if (selectedServicio == null) {
            System.err.println("Error: Debe seleccionar una Servicio para modificar.");
            return;
        }

        Servicio ServicioActualizada = buildServicio();


        if (ServicioActualizada != null && servicioController.actualizarServicio(selectedServicio.getIdServicio(), ServicioActualizada)) {
            ServicioActualizada.descontarStockRepuestos();
            int index = listaServicio.indexOf(selectedServicio);
            listaServicio.set(index, ServicioActualizada);
            limpiarCampos();
            limpiarSeleccion();
        }
    }



    /**
     * Metodo que limpia la seleccion de la tabla
     */
    private void limpiarSeleccion() {
        tbRepuestos.getSelectionModel().clearSelection();
        tbOrden.getSelectionModel().clearSelection();
        tbServicio.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     *Metodo qi¿ue limpia los acmpos del paciente seleccionado
     */
    private void limpiarCampos() {
        txtID.clear();
        txtPrecioMO.clear();
        cbxEstado.setValue(null);
        txtDescripcion.clear();
        tbOrden.getSelectionModel().clearSelection();
        tbRepuestos.getSelectionModel().clearSelection();
    }

    /**
     *metodo que seta APP
     * @param app
     */
    public void setApp(App app) {
        this.app = app;

        ObservableList<Servicio.EstadoServicio> options = FXCollections.observableArrayList(Servicio.EstadoServicio.values());
        cbxEstado.setItems((options));
    }

}

