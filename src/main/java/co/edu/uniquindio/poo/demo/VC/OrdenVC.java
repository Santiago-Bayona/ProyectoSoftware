package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Mecanico;
import co.edu.uniquindio.poo.demo.Back.Orden;
import co.edu.uniquindio.poo.demo.Back.Vehiculo;
import co.edu.uniquindio.poo.demo.Controller.OrdenController;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OrdenVC {

    App app;
    OrdenController ordenController;
    private ObservableList<Orden>    listaOrden    = FXCollections.observableArrayList();
    private ObservableList<Vehiculo> listaVehiculo = FXCollections.observableArrayList();
    private ObservableList<Mecanico> listaMecanicos= FXCollections.observableArrayList();
    private Orden    selectedOrden;
    private Vehiculo selectedVehiculo;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton bttAgregarOrden;
    @FXML private MFXButton bttEliminarOrden;
    @FXML private MFXButton bttLimpiarOrden;
    @FXML private MFXButton bttModificarOrden;
    @FXML private MFXButton bttVolver;

    @FXML private MFXDatePicker          dpFecha;
    @FXML private MFXTextField           txtID;
    @FXML private MFXTableView<Orden>    tbOrden;
    @FXML private MFXTableView<Vehiculo> tbVehiculo;
    @FXML private MFXTableView<Mecanico> tbMecanico;

    @FXML void AgregarOrden(ActionEvent event)  { agregarorden(); }
    @FXML void EliminarOrden(ActionEvent event) { eliminarorden(); }
    @FXML void LimpiarOrden(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarOrden(ActionEvent event){ modificarOrden(); }

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
        ordenController = new OrdenController(App.taller);
        obtenerVehiculos();
        obtenerMecanicos();
        obtenerOrdenes();
        setupColumnasOrden();
        setupColumnasVehiculo();
        setupColumnasMecanico();
        setupFiltros();
        tbOrden   .setItems(listaOrden);
        tbVehiculo.setItems(listaVehiculo);
        tbMecanico.setItems(listaMecanicos);
        tbMecanico.getSelectionModel().setAllowsMultipleSelection(true);
        listenerSelectionOrden();
        listenerSelectionVehiculo();
    }

    private void setupColumnasOrden() {
        MFXTableColumn<Orden> colID       = new MFXTableColumn<>("ID",        true, Comparator.comparing(Orden::getIdOrden));
        MFXTableColumn<Orden> colFecha    = new MFXTableColumn<>("Fecha",     true, Comparator.comparing(Orden::getFecha));
        MFXTableColumn<Orden> colCliente  = new MFXTableColumn<>("Cliente",   true, Comparator.comparing(o -> o.getCliente().getNombre()));
        MFXTableColumn<Orden> colVehiculo = new MFXTableColumn<>("Vehículo",  true, Comparator.comparing(o -> o.getVehiculo().getPlaca()));
        MFXTableColumn<Orden> colMecs     = new MFXTableColumn<>("Mecánicos", false, null);

        colID      .setRowCellFactory(o -> new MFXTableRowCell<>(Orden::getIdOrden));
        colFecha   .setRowCellFactory(o -> new MFXTableRowCell<>(or -> or.getFecha().format(FORMATTER)));
        colCliente .setRowCellFactory(o -> new MFXTableRowCell<>(or -> or.getCliente().getNombre()));
        colVehiculo.setRowCellFactory(o -> new MFXTableRowCell<>(or -> or.getVehiculo().getPlaca()));
        colMecs    .setRowCellFactory(o -> new MFXTableRowCell<>(Orden::getNombresMecanicos));

        colID      .setPrefWidth(80);
        colFecha   .setPrefWidth(100);
        colCliente .setPrefWidth(130);
        colVehiculo.setPrefWidth(100);
        colMecs    .setPrefWidth(220);

        tbOrden.getTableColumns().addAll(colID, colFecha, colCliente, colVehiculo, colMecs);
    }

    private void setupColumnasVehiculo() {
        MFXTableColumn<Vehiculo> colPlaca  = new MFXTableColumn<>("Placa",  true, Comparator.comparing(Vehiculo::getPlaca));
        MFXTableColumn<Vehiculo> colMarca  = new MFXTableColumn<>("Marca",  true, Comparator.comparing(Vehiculo::getMarca));
        MFXTableColumn<Vehiculo> colDuenio = new MFXTableColumn<>("Dueño",  true, Comparator.comparing(v -> v.getDuenio().getNombre()));

        colPlaca .setRowCellFactory(v -> new MFXTableRowCell<>(Vehiculo::getPlaca));
        colMarca .setRowCellFactory(v -> new MFXTableRowCell<>(Vehiculo::getMarca));
        colDuenio.setRowCellFactory(v -> new MFXTableRowCell<>(ve -> ve.getDuenio().getNombre()));

        colPlaca .setPrefWidth(100);
        colMarca .setPrefWidth(100);
        colDuenio.setPrefWidth(130);

        tbVehiculo.getTableColumns().addAll(colPlaca, colMarca, colDuenio);
    }

    private void setupColumnasMecanico() {
        MFXTableColumn<Mecanico> colNom = new MFXTableColumn<>("Nombre",       true, Comparator.comparing(Mecanico::getNombre));
        MFXTableColumn<Mecanico> colEsp = new MFXTableColumn<>("Especialidad", true, Comparator.comparing(Mecanico::getEspcialidad));

        colNom.setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getNombre));
        colEsp.setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getEspcialidad));

        colNom.setPrefWidth(150);
        colEsp.setPrefWidth(140);

        tbMecanico.getTableColumns().addAll(colNom, colEsp);
    }

    private void setupFiltros() {
        tbOrden.getFilters().addAll(new StringFilter<>("ID", Orden::getIdOrden));
    }

    private void listenerSelectionOrden() {
        tbOrden.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                selectedOrden = newVal.values().iterator().next();
                mostrarInformacionOrden(selectedOrden);
            }
        });
    }

    private void listenerSelectionVehiculo() {
        tbVehiculo.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty())
                selectedVehiculo = newVal.values().iterator().next();
        });
    }

    private void obtenerOrdenes() {
        Collection<Orden> ords = ordenController.obtenerListaOrden();
        if (ords != null) listaOrden.setAll(ords);
    }

    private void obtenerVehiculos() {
        Collection<Vehiculo> vehiculos = ordenController.obtenerListaVehiculo();
        if (vehiculos != null) listaVehiculo.setAll(vehiculos);
    }

    private void obtenerMecanicos() {
        Collection<Mecanico> mecs = ordenController.obtenerListaMecanicos();
        if (mecs != null) listaMecanicos.setAll(mecs);
    }

    private void mostrarInformacionOrden(Orden orden) {
        if (orden == null) return;
        txtID.setText(orden.getIdOrden());
        dpFecha.setValue(orden.getFecha());
        tbMecanico.getSelectionModel().clearSelection();
        for (Mecanico m : orden.getMecanicos()) {
            int index = listaMecanicos.indexOf(m);
            if (index >= 0) tbMecanico.getSelectionModel().selectIndex(index);
        }
    }

    // ─── CRUD ────────────────────────────────────────────────────────────────────

    private void agregarorden() {
        if (selectedVehiculo == null || txtID.getText().isEmpty() || dpFecha.getValue() == null) {
            System.err.println("Datos incompletos."); return;
        }
        List<Mecanico> mecs = List.copyOf(tbMecanico.getSelectionModel().getSelectedValues());
        for (Mecanico m : mecs) {
            if (App.taller.contarServiciosPorMecanico(m.getDocumento()) >= 3) {
                System.err.println("Mecánico " + m.getNombre() + " ya tiene 3 órdenes."); return;
            }
        }
        Orden orden = buildOrden();
        if (orden != null && ordenController.crearOrden(orden)) {
            listaOrden.add(orden);
            refrescarTabla();
            limpiarCampos();
        }
    }

    private void eliminarorden() {
        if (selectedOrden != null && ordenController.eliminarOrden(selectedOrden)) {
            listaOrden.remove(selectedOrden);
            refrescarTabla();
            limpiarSeleccion();
        }
    }

    private void modificarOrden() {
        if (selectedOrden == null) return;
        Orden actualizada = buildOrden();
        if (actualizada != null && ordenController.actualizarOrden(selectedOrden.getIdOrden(), actualizada)) {
            int index = listaOrden.indexOf(selectedOrden);
            if (index >= 0) listaOrden.set(index, actualizada);
            refrescarTabla();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    /** Fuerza el repintado de la MFXTableView desvinculando y re-vinculando los items */
    private void refrescarTabla() {
        tbOrden.setItems(null);
        tbOrden.setItems(listaOrden);
    }

    private Orden buildOrden() {
        if (selectedVehiculo == null || txtID.getText().isEmpty() || dpFecha.getValue() == null) return null;
        Orden orden = new Orden(txtID.getText(), selectedVehiculo, dpFecha.getValue());
        for (Mecanico m : List.copyOf(tbMecanico.getSelectionModel().getSelectedValues()))
            orden.agregarMecanico(m);
        return orden;
    }

    private void limpiarSeleccion() {
        tbOrden   .getSelectionModel().clearSelection();
        tbVehiculo.getSelectionModel().clearSelection();
        tbMecanico.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtID.clear();
        dpFecha.setValue(null);
        selectedOrden    = null;
        selectedVehiculo = null;
    }

    public void setApp(App app) { this.app = app; }
}

