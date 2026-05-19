package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Orden;
import co.edu.uniquindio.poo.demo.Back.Repuesto;
import co.edu.uniquindio.poo.demo.Back.Servicio;
import co.edu.uniquindio.poo.demo.Controller.ServicioController;

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
import javafx.scene.control.TextArea;

public class ServicioVC {

    App app;
    ServicioController servicioController;
    private ObservableList<Servicio> listaServicio  = FXCollections.observableArrayList();
    private ObservableList<Orden>    listaOrden     = FXCollections.observableArrayList();
    private ObservableList<Repuesto> listaRepuestos = FXCollections.observableArrayList();
    private Servicio selectedServicio;
    private Orden    selectedOrden;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton bttAgregarServicio;
    @FXML private MFXButton bttEliminarServicio;
    @FXML private MFXButton bttLimpiarServicio;
    @FXML private MFXButton bttModificarServicio;
    @FXML private MFXButton bttVolver;

    @FXML private MFXComboBox<Servicio.EstadoServicio> cbxEstado;
    @FXML private MFXTextField txtID;
    @FXML private MFXTextField txtPrecioMO;
    @FXML private TextArea     txtDescripcion;

    @FXML private MFXTableView<Servicio> tbServicio;
    @FXML private MFXTableView<Orden>    tbOrden;
    @FXML private MFXTableView<Repuesto> tbRepuestos;

    @FXML void AgregarServicio(ActionEvent event)  { agregarServicio(); }
    @FXML void EliminarServicio(ActionEvent event) { eliminarServicio(); }
    @FXML void LimpiarServicio(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarServicio(ActionEvent event){ modificarServicio(); }

    @FXML
    void Volver(ActionEvent event) {
        try { if (app.esAdmin()) {
            app.MenuAdmin();
        } else {
            app.MenuEmpleado();
        }
        } catch (Exception e) {
            e.printStackTrace(); }
    }

    @FXML
    void initialize() {
        if (App.taller == null) { System.err.println("Taller null"); return; }
        servicioController = new ServicioController(App.taller);
        obtenerOrdenes();
        obtenerRepuestos();
        obtenerServicios();
        setupColumnasServicio();
        setupColumnasOrden();
        setupColumnasRepuestos();
        setupFiltros();
        tbServicio .setItems(listaServicio);
        tbOrden    .setItems(listaOrden);
        tbRepuestos.setItems(listaRepuestos);
        tbRepuestos.getSelectionModel().setAllowsMultipleSelection(true);
        listenerSelectionServicio();
        listenerSelectionOrden();
    }

    // ─── Columnas ────────────────────────────────────────────────────────────────

    private void setupColumnasServicio() {
        MFXTableColumn<Servicio> colID      = new MFXTableColumn<>("ID",               true, Comparator.comparing(Servicio::getIdServicio));
        MFXTableColumn<Servicio> colMO      = new MFXTableColumn<>("Precio MO",        true, Comparator.comparingDouble(Servicio::getPrecioManoObra));
        MFXTableColumn<Servicio> colEstado  = new MFXTableColumn<>("Estado",           true, Comparator.comparing(s -> s.getEstadoServicio().toString()));
        MFXTableColumn<Servicio> colOrden   = new MFXTableColumn<>("Orden",            true, Comparator.comparing(s -> s.getOrden().getIdOrden()));
        MFXTableColumn<Servicio> colReps    = new MFXTableColumn<>("Repuestos",        false, null);
        MFXTableColumn<Servicio> colPrecioR = new MFXTableColumn<>("Precio Repuestos", true, Comparator.comparingDouble(Servicio::getPrecioRepuestos));

        colID     .setRowCellFactory(s -> new MFXTableRowCell<>(Servicio::getIdServicio));
        colMO     .setRowCellFactory(s -> new MFXTableRowCell<>(sv -> String.valueOf(sv.getPrecioManoObra())));
        colEstado .setRowCellFactory(s -> new MFXTableRowCell<>(sv -> sv.getEstadoServicio() != null ? sv.getEstadoServicio().toString() : ""));
        colOrden  .setRowCellFactory(s -> new MFXTableRowCell<>(sv -> sv.getOrden().getIdOrden()));
        colReps   .setRowCellFactory(s -> new MFXTableRowCell<>(Servicio::getNombresRepuestos));
        colPrecioR.setRowCellFactory(s -> new MFXTableRowCell<>(sv -> String.valueOf(sv.getPrecioRepuestos())));

        colID     .setPrefWidth(70);
        colMO     .setPrefWidth(90);
        colEstado .setPrefWidth(110);
        colOrden  .setPrefWidth(80);
        colReps   .setPrefWidth(160);
        colPrecioR.setPrefWidth(120);

        tbServicio.getTableColumns().addAll(colID, colMO, colEstado, colOrden, colReps, colPrecioR);
    }

    private void setupColumnasOrden() {
        MFXTableColumn<Orden> colID    = new MFXTableColumn<>("ID Orden", true, Comparator.comparing(Orden::getIdOrden));
        MFXTableColumn<Orden> colPlaca = new MFXTableColumn<>("Vehículo", true, Comparator.comparing(o -> o.getVehiculo().getPlaca()));
        MFXTableColumn<Orden> colCliente = new MFXTableColumn<>("Cliente", true, Comparator.comparing(o -> o.getCliente().getNombre()));

        colID    .setRowCellFactory(o -> new MFXTableRowCell<>(Orden::getIdOrden));
        colPlaca .setRowCellFactory(o -> new MFXTableRowCell<>(or -> or.getVehiculo().getPlaca()));
        colCliente.setRowCellFactory(o -> new MFXTableRowCell<>(or -> or.getCliente().getNombre()));

        colID    .setPrefWidth(100);
        colPlaca .setPrefWidth(100);
        colCliente.setPrefWidth(120);

        tbOrden.getTableColumns().addAll(colID, colPlaca, colCliente);
    }

    private void setupColumnasRepuestos() {
        MFXTableColumn<Repuesto> colNom   = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Repuesto::getNombre));
        MFXTableColumn<Repuesto> colStock = new MFXTableColumn<>("Stock",  true, Comparator.comparingInt(Repuesto::getStock));

        colNom  .setRowCellFactory(r -> new MFXTableRowCell<>(Repuesto::getNombre));
        colStock.setRowCellFactory(r -> new MFXTableRowCell<>(re -> String.valueOf(re.getStock())));

        colNom  .setPrefWidth(170);
        colStock.setPrefWidth(70);

        tbRepuestos.getTableColumns().addAll(colNom, colStock);
    }

    private void setupFiltros() {
        tbServicio.getFilters().addAll(new StringFilter<>("ID", Servicio::getIdServicio));
    }

    private void listenerSelectionServicio() {
        tbServicio.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                selectedServicio = newVal.values().iterator().next();
                mostrarInformacionServicio(selectedServicio);
            }
        });
    }

    private void listenerSelectionOrden() {
        tbOrden.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty())
                selectedOrden = newVal.values().iterator().next();
        });
    }

    private void obtenerServicios() {
        Collection<Servicio> svs = servicioController.obtenerListaServicio();
        if (svs != null) listaServicio.setAll(svs);
    }

    private void obtenerOrdenes() {
        Collection<Orden> ords = servicioController.obtenerListaOrden();
        if (ords != null) listaOrden.setAll(ords);
    }

    private void obtenerRepuestos() {
        Collection<Repuesto> reps = servicioController.obtenerListaRepuestos();
        if (reps != null) listaRepuestos.setAll(reps);
    }

    private void mostrarInformacionServicio(Servicio s) {
        if (s == null) return;
        txtID         .setText(s.getIdServicio());
        txtPrecioMO   .setText(String.valueOf(s.getPrecioManoObra()));
        txtDescripcion.setText(s.getDescripcion());
        cbxEstado.setValue(s.getEstadoServicio());
        tbRepuestos.getSelectionModel().clearSelection();
        for (Repuesto r : s.getRepuestos()) {
            int index = listaRepuestos.indexOf(r);
            if (index >= 0) tbRepuestos.getSelectionModel().selectIndex(index);
        }
    }

    // ─── CRUD ────────────────────────────────────────────────────────────────────

    private void agregarServicio() {
        if (selectedOrden == null || txtID.getText().isEmpty() || cbxEstado.getValue() == null) {
            System.err.println("Datos incompletos."); return;
        }
        Servicio s = buildServicio();
        if (s != null) {
            s.calcularPrecioRepuestos();
            s.descontarStockRepuestos();
            if (servicioController.crearServicio(s)) {
                listaServicio.add(s);
                refrescarTabla();
                limpiarCampos();
            }
        }
    }

    private void eliminarServicio() {
        if (selectedServicio != null && servicioController.eliminarServicio(selectedServicio)) {
            listaServicio.remove(selectedServicio);
            refrescarTabla();
            limpiarSeleccion();
        }
    }

    private void modificarServicio() {
        if (selectedServicio == null) return;
        Servicio actualizado = buildServicio();
        if (actualizado != null && servicioController.actualizarServicio(selectedServicio.getIdServicio(), actualizado)) {
            App.taller.finalizaServicio();
            actualizado.calcularPrecioRepuestos();
            actualizado.descontarStockRepuestos();
            int index = listaServicio.indexOf(selectedServicio);
            if (index >= 0) listaServicio.set(index, actualizado);
            refrescarTabla();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private void refrescarTabla() {
        tbServicio.setItems(null);
        tbServicio.setItems(listaServicio);
    }

    private Servicio buildServicio() {
        if (selectedOrden == null || txtID.getText().isEmpty() || cbxEstado.getValue() == null) return null;
        Servicio s = new Servicio(
                txtID.getText(), txtDescripcion.getText(),
                Double.parseDouble(txtPrecioMO.getText()),
                cbxEstado.getValue(), selectedOrden
        );
        for (Repuesto r : List.copyOf(tbRepuestos.getSelectionModel().getSelectedValues()))
            s.agregarRepuesto(r);
        return s;
    }

    private void limpiarSeleccion() {
        tbServicio .getSelectionModel().clearSelection();
        tbOrden    .getSelectionModel().clearSelection();
        tbRepuestos.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtID.clear(); txtPrecioMO.clear(); txtDescripcion.clear();
        cbxEstado.setValue(null);
        selectedServicio = null;
        selectedOrden    = null;
    }

    public void setApp(App app) {
        this.app = app;
        cbxEstado.setItems(FXCollections.observableArrayList(Servicio.EstadoServicio.values()));
    }
}

