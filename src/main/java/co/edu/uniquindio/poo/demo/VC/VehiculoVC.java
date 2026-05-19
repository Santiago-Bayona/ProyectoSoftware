package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Cliente;
import co.edu.uniquindio.poo.demo.Back.Vehiculo;
import co.edu.uniquindio.poo.demo.Controller.VehiculoController;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class VehiculoVC {

    App app;
    VehiculoController vehiculoController;
    private ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();
    private ObservableList<Cliente>  listaCliente   = FXCollections.observableArrayList();
    private Vehiculo selectedVehiculo;
    private Cliente  selectedCliente;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton bttAgregarVehiculo;
    @FXML private MFXButton bttEliminarVehiculo;
    @FXML private MFXButton bttLimpiarVehiculo;
    @FXML private MFXButton bttModificarVehiculo;
    @FXML private MFXButton bttVolver;

    @FXML private MFXComboBox<Vehiculo.Tipo> cbxTipo;
    @FXML private MFXTableView<Vehiculo> tbVehiculo;
    @FXML private MFXTableView<Cliente>  tbCliente;

    @FXML private MFXTextField txtPlaca;
    @FXML private MFXTextField txtMarca;
    @FXML private MFXTextField txtModelo;
    @FXML private MFXTextField txtKilometraje;

    @FXML void AgregarVehiculo(ActionEvent event)  { agregarvehiculo(); }
    @FXML void EliminarVehiculo(ActionEvent event) { eliminarvehiculo(); }
    @FXML void LimpiarVehiculo(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarVehiculo(ActionEvent event){ modificarVehiculo(); }
    @FXML void Volver(ActionEvent event) {
        try { app.MenuAdmin(); } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    void initialize() {
        if (App.taller == null) { System.err.println("Taller null"); return; }
        vehiculoController = new VehiculoController(App.taller);
        obtenerCliente();
        setupColumnasVehiculo();
        setupColumnasCliente();
        setupFiltros();
        obtenerVehiculo();
        tbVehiculo.setItems(listaVehiculos);
        tbCliente .setItems(listaCliente);
        listenerSelectionVehiculo();
        listenerSelectionCliente();
    }

    private void setupColumnasVehiculo() {
        MFXTableColumn<Vehiculo> colPlaca = new MFXTableColumn<>("Placa",       true, Comparator.comparing(Vehiculo::getPlaca));
        MFXTableColumn<Vehiculo> colMarca = new MFXTableColumn<>("Marca",       true, Comparator.comparing(Vehiculo::getMarca));
        MFXTableColumn<Vehiculo> colMod   = new MFXTableColumn<>("Modelo",      true, Comparator.comparing(Vehiculo::getModelo));
        MFXTableColumn<Vehiculo> colKm    = new MFXTableColumn<>("Kilometraje", true, Comparator.comparing(Vehiculo::getKilometraje));
        MFXTableColumn<Vehiculo> colTipo  = new MFXTableColumn<>("Tipo",        true, Comparator.comparing(v -> v.getTipo().toString()));
        MFXTableColumn<Vehiculo> colDuenio= new MFXTableColumn<>("Cliente",     true, Comparator.comparing(v -> v.getDuenio().getNombre()));

        colPlaca .setRowCellFactory(v -> new MFXTableRowCell<>(Vehiculo::getPlaca));
        colMarca .setRowCellFactory(v -> new MFXTableRowCell<>(Vehiculo::getMarca));
        colMod   .setRowCellFactory(v -> new MFXTableRowCell<>(Vehiculo::getModelo));
        colKm    .setRowCellFactory(v -> new MFXTableRowCell<>(Vehiculo::getKilometraje));
        colTipo  .setRowCellFactory(v -> new MFXTableRowCell<>(ve -> ve.getTipo() != null ? ve.getTipo().toString() : ""));
        colDuenio.setRowCellFactory(v -> new MFXTableRowCell<>(ve -> ve.getDuenio() != null ? ve.getDuenio().getNombre() : ""));

        colPlaca .setPrefWidth(90);
        colMarca .setPrefWidth(100);
        colMod   .setPrefWidth(80);
        colKm    .setPrefWidth(100);
        colTipo  .setPrefWidth(90);
        colDuenio.setPrefWidth(120);

        tbVehiculo.getTableColumns().addAll(colPlaca, colMarca, colMod, colKm, colTipo, colDuenio);
    }

    private void setupColumnasCliente() {
        MFXTableColumn<Cliente> colNom = new MFXTableColumn<>("Nombre",   true, Comparator.comparing(Cliente::getNombre));
        MFXTableColumn<Cliente> colDoc = new MFXTableColumn<>("Documento", true, Comparator.comparing(Cliente::getDocumento));

        colNom.setRowCellFactory(c -> new MFXTableRowCell<>(Cliente::getNombre));
        colDoc.setRowCellFactory(c -> new MFXTableRowCell<>(Cliente::getDocumento));

        colNom.setPrefWidth(200);
        colDoc.setPrefWidth(150);

        tbCliente.getTableColumns().addAll(colNom, colDoc);
    }

    private void setupFiltros() {
        tbVehiculo.getFilters().addAll(
                new StringFilter<>("Placa",  Vehiculo::getPlaca),
                new StringFilter<>("Marca",  Vehiculo::getMarca)
        );
    }

    private void listenerSelectionVehiculo() {
        tbVehiculo.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                selectedVehiculo = newVal.values().iterator().next();
                mostrarInformacionVehiculo(selectedVehiculo);
            }
        });
    }

    private void listenerSelectionCliente() {
        tbCliente.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty())
                selectedCliente = newVal.values().iterator().next();
        });
    }

    private void obtenerCliente() {
        Collection<Cliente> clientes = vehiculoController.obtenerListaCliente();
        if (clientes != null) listaCliente.setAll(clientes);
    }

    private void obtenerVehiculo() {
        if (vehiculoController != null)
            listaVehiculos.addAll(vehiculoController.obtenerListavehiculo());
    }

    private void mostrarInformacionVehiculo(Vehiculo v) {
        if (v == null) return;
        txtPlaca      .setText(v.getPlaca());
        txtMarca      .setText(v.getMarca());
        txtModelo     .setText(v.getModelo());
        txtKilometraje.setText(v.getKilometraje());
        cbxTipo.setValue(v.getTipo());
    }

    private void agregarvehiculo() {
        Vehiculo v = buildVehiculo();
        if (vehiculoController.crearVehiculo(v)) {
            listaVehiculos.add(v);
            limpiarCampos();
        }
    }

    private void eliminarvehiculo() {
        if (selectedVehiculo != null && vehiculoController.eliminarvehiculo(selectedVehiculo)) {
            listaVehiculos.remove(selectedVehiculo);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void modificarVehiculo() {
        if (selectedVehiculo != null &&
                vehiculoController.actualizarVehiculo(selectedVehiculo.getPlaca(), buildVehiculo())) {
            int index = listaVehiculos.indexOf(selectedVehiculo);
            if (index >= 0) listaVehiculos.set(index, buildVehiculo());
            tbVehiculo.update();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private Vehiculo buildVehiculo() {
        return new Vehiculo(
                txtPlaca.getText(), txtMarca.getText(),
                txtModelo.getText(), txtKilometraje.getText(),
                selectedCliente, cbxTipo.getValue()
        );
    }

    private void limpiarSeleccion() {
        tbVehiculo.getSelectionModel().clearSelection();
        tbCliente .getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtPlaca.clear(); txtMarca.clear();
        txtModelo.clear(); txtKilometraje.clear();
        cbxTipo.setValue(null);
        selectedVehiculo = null;
        selectedCliente  = null;
    }

    public void setApp(App app) {
        this.app = app;
        cbxTipo.setItems(FXCollections.observableArrayList(Vehiculo.Tipo.values()));
    }
}

