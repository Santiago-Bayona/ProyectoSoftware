package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.*;
import co.edu.uniquindio.poo.demo.Controller.FacturaController;
import co.edu.uniquindio.poo.demo.Controller.OrdenController;
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

public class FacturaVC {
    
    App app;
    FacturaController facturaController;
    ObservableList<Factura> listafacturas = FXCollections.observableArrayList();
    ObservableList<Pago> listapagos = FXCollections.observableArrayList();
    ObservableList<Servicio> listaServicio = FXCollections.observableArrayList();
    private Factura selectedFactura;
    private Pago selectedPago;
    private Servicio selectedServicio;
    
    

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Factura, String> tbcID;

    @FXML
    private Button bttEliminarFactura;

    @FXML
    private TableColumn<Factura, String> tbcPago;

    @FXML
    private Button bttLimpiarFactura;

    @FXML
    private TableColumn<Servicio, String> tbcIdServicio;

    @FXML
    private TableColumn<Factura, Double> tbcPrecio;

    @FXML
    private TableView<Servicio> tbServicio;

    @FXML
    private TableColumn<Factura, String> tbcServicio;

    @FXML
    private Button bttVolver;

    @FXML
    private TextField txtID;

    @FXML
    private TableView<Factura> tbFactura;

    @FXML
    private Button bttModificarFactura;

    @FXML
    private Button bttAgregarFactura;

    @FXML
    private TableView<Pago> tbPago;

    @FXML
    private TableColumn<Pago, String> tbcIdPago;

    @FXML
    void AgregarFactura(ActionEvent event) {
        agregarFactura();
    }

    @FXML
    void EliminarFactura(ActionEvent event) {
        eliminarFactura();
    }

    @FXML
    void ModificarFactura(ActionEvent event) {
        actualizarCliente();
    }

    @FXML
    void LimpiarFactura(ActionEvent event) {
        limpiarCampos();
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
        assert tbcID != null : "fx:id=\"tbcID\" was not injected: check your FXML file 'Factura.fxml'.";
        assert bttEliminarFactura != null : "fx:id=\"bttEliminarFactura\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbcPago != null : "fx:id=\"tbcPago\" was not injected: check your FXML file 'Factura.fxml'.";
        assert bttLimpiarFactura != null : "fx:id=\"bttLimpiarFactura\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbcIdServicio != null : "fx:id=\"tbcIdServicio\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbcPrecio != null : "fx:id=\"tbcPrecio\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbServicio != null : "fx:id=\"tbServicio\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbcServicio != null : "fx:id=\"tbcServicio\" was not injected: check your FXML file 'Factura.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Factura.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbFactura != null : "fx:id=\"tbFactura\" was not injected: check your FXML file 'Factura.fxml'.";
        assert bttModificarFactura != null : "fx:id=\"bttModificarFactura\" was not injected: check your FXML file 'Factura.fxml'.";
        assert bttAgregarFactura != null : "fx:id=\"bttAgregarFactura\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbPago != null : "fx:id=\"tbPago\" was not injected: check your FXML file 'Factura.fxml'.";
        assert tbcIdPago != null : "fx:id=\"tbcIdPago\" was not injected: check your FXML file 'Factura.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        facturaController = new FacturaController(App.taller);
        obtenerFactura();
        obtenerPago();
        obtenerServicios();
        initView();

    }

    private void obtenerPago() {
        if (listapagos == null) {
            listapagos = FXCollections.observableArrayList();
        }

        Collection<Pago> pagos = facturaController.obtenerListaPagos();
        if (pagos != null) {
            listapagos.setAll(pagos);
        } else {
            System.out.println("No se encontraron pagos.");
        }
    }

    private void obtenerServicios() {
        if (listaServicio == null) {
            listaServicio = FXCollections.observableArrayList();
        }

        Collection<Servicio> servicios = facturaController.obtenerListaServicio();
        if (servicios != null) {
            listaServicio.setAll(servicios);
        } else {
            System.out.println("No se encontraron servicios.");
        }
    }


    /**
     *Metodo que permite obtener los datos de paciente
     */
    private void obtenerFactura() {
        if (facturaController != null) {
            listafacturas.addAll(facturaController.obtenerListafactura());
        } else {
            System.err.println("FacturaController no está inicializado.");
        }
    }


    /**
     *Metodo que inicializa la vista en javaFX
     */
    private void initView() {
        initDataBinding();
        tbFactura.getItems().clear();
        tbFactura.setItems(listafacturas);
        tbPago.setItems(listapagos);
        tbServicio.setItems(listaServicio);
        listenerSelectionServicio();
        listenerSelectionFactura();
        listenerSelectionPago();
    }

    /**
     *Metodo que configura la vinculacion de datos en las tablas
     */
    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdFactura()));
        tbcPago.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPago().getIdPago()));
        tbcServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getIdServicio()));
        tbcPrecio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().CalcularTotal()));
        tbcIdPago.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdPago()));
        tbcIdServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdServicio()));

    }

    /**
     *Metodo que escucha los cambios en la selecion de paciente y actualiza la variable selectedPaciente
     */
    private void listenerSelectionFactura() {
        tbFactura.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedFactura = newSelection;
            mostarInformacionFactura(selectedFactura);
        });
    }

    /**
     *Metodo que detecta cuando el usuario selecciona un historial medico en la tabla
     */
    private void listenerSelectionPago() {
        tbPago.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedPago = newSelection;
        });
    }

    private void listenerSelectionServicio() {
        tbServicio.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedServicio = newSelection;
        });
    }


    /**
     *Metodo que muestra la información del paciente sleccionado en los campos de la interfaz
     * @param Factura
     */
    private void mostarInformacionFactura(Factura Factura) {
        if (Factura != null) {
            txtID.setText(Factura.getIdFactura());
        }
    }

    /**
     *Metodo que agrega un paciente
     */
    private void agregarFactura() {
        Factura Factura = buildFactura();
        if(facturaController.crearfactura(Factura)){
            listafacturas.add(Factura);
            limpiarCampos();
        }
    }

    /**
     *Metodo que crea una instancia de Paciente con los datos ingresados en la interfaz
     * @return
     */
    private Factura buildFactura() {
        Factura Factura = new Factura(
                txtID.getText(),
                selectedPago,
                selectedServicio
        );
        return Factura;

    }

    private void actualizarCliente() {

        if (selectedFactura != null &&
                facturaController.actualizarfactura(selectedFactura.getIdFactura(), buildFactura())) {

            int index = listafacturas.indexOf(selectedFactura);
            if (index >= 0) {
                listafacturas.set(index, buildFactura());
            }

            tbFactura.refresh();
            limpiarSeleccion();
            limpiarCampos();
        }
    }


    /**
     *Metodo que elimina un paciente
     */
    private void eliminarFactura() {
        Factura Factura = buildFactura();
        if (facturaController.eliminarfactura(Factura)){
            listafacturas.remove(selectedFactura);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que limpia la seleccion de la tabla
     */
    private void limpiarSeleccion() {
        tbFactura.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     *Metodo qi¿ue limpia los acmpos del paciente seleccionado
     */
    private void limpiarCampos() {
        txtID.clear();
        tbPago.getSelectionModel().clearSelection();
        tbServicio.getSelectionModel().clearSelection();
    }

    /**
     *metodo que seta APP
     * @param app
     */
    public void setApp(App app) {
        this.app = app;

    }
}

