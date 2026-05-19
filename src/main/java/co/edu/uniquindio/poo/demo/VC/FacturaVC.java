package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Factura;
import co.edu.uniquindio.poo.demo.Back.Pago;
import co.edu.uniquindio.poo.demo.Back.Servicio;
import co.edu.uniquindio.poo.demo.Controller.FacturaController;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FacturaVC {

    App app;
    FacturaController facturaController;
    private ObservableList<Factura>  listaFacturas = FXCollections.observableArrayList();
    private ObservableList<Pago>     listaPagos    = FXCollections.observableArrayList();
    private ObservableList<Servicio> listaServicio = FXCollections.observableArrayList();
    private Factura  selectedFactura;
    private Pago     selectedPago;
    private Servicio selectedServicio;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton bttAgregarFactura;
    @FXML private MFXButton bttEliminarFactura;
    @FXML private MFXButton bttLimpiarFactura;
    @FXML private MFXButton bttModificarFactura;
    @FXML private MFXButton bttVolver;

    @FXML private MFXTextField       txtID;
    @FXML private MFXTableView<Factura>  tbFactura;
    @FXML private MFXTableView<Pago>     tbPago;
    @FXML private MFXTableView<Servicio> tbServicio;

    @FXML void AgregarFactura(ActionEvent event)  { agregarFactura(); }
    @FXML void EliminarFactura(ActionEvent event) { eliminarFactura(); }
    @FXML void LimpiarFactura(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarFactura(ActionEvent event){ actualizarFactura(); }
    @FXML void Volver(ActionEvent event) {
        try { app.MenuAdmin(); } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    void initialize() {
        if (App.taller == null) { System.err.println("Taller null"); return; }
        facturaController = new FacturaController(App.taller);

        obtenerPagos();
        obtenerServicios();
        obtenerFacturas();

        setupColumnasFactura();
        setupColumnasPago();
        setupColumnasServicio();
        setupFiltros();

        tbFactura .setItems(listaFacturas);
        tbPago    .setItems(listaPagos);
        tbServicio.setItems(listaServicio);

        listenerSelectionFactura();
        listenerSelectionPago();
        listenerSelectionServicio();
    }

    // ─── Columnas ────────────────────────────────────────────────────────────────

    private void setupColumnasFactura() {
        MFXTableColumn<Factura> colID      = new MFXTableColumn<>("ID",       true, Comparator.comparing(Factura::getIdFactura));
        MFXTableColumn<Factura> colPago    = new MFXTableColumn<>("Pago",     true, Comparator.comparing(f -> f.getPago().getIdPago()));
        MFXTableColumn<Factura> colServ    = new MFXTableColumn<>("Servicio", true, Comparator.comparing(f -> f.getServicio().getIdServicio()));
        MFXTableColumn<Factura> colTotal   = new MFXTableColumn<>("Total",    true, Comparator.comparingDouble(Factura::getTotal));

        colID   .setRowCellFactory(f -> new MFXTableRowCell<>(Factura::getIdFactura));
        colPago .setRowCellFactory(f -> new MFXTableRowCell<>(fa -> fa.getPago() != null ? fa.getPago().getIdPago() : ""));
        colServ .setRowCellFactory(f -> new MFXTableRowCell<>(fa -> fa.getServicio() != null ? fa.getServicio().getIdServicio() : ""));
        colTotal.setRowCellFactory(f -> new MFXTableRowCell<>(fa -> String.format("$ %.2f", fa.CalcularTotal())));

        colID   .setPrefWidth(100);
        colPago .setPrefWidth(120);
        colServ .setPrefWidth(120);
        colTotal.setPrefWidth(120);

        tbFactura.getTableColumns().addAll(colID, colPago, colServ, colTotal);
    }

    private void setupColumnasPago() {
        MFXTableColumn<Pago> colID     = new MFXTableColumn<>("ID Pago", true, Comparator.comparing(Pago::getIdPago));
        MFXTableColumn<Pago> colEstado = new MFXTableColumn<>("Estado",  true, Comparator.comparing(p -> p.getEstado().toString()));

        colID    .setRowCellFactory(p -> new MFXTableRowCell<>(Pago::getIdPago));
        colEstado.setRowCellFactory(p -> new MFXTableRowCell<>(pa -> pa.getEstado() != null ? pa.getEstado().toString() : ""));

        colID    .setPrefWidth(150);
        colEstado.setPrefWidth(100);

        tbPago.getTableColumns().addAll(colID, colEstado);
    }

    private void setupColumnasServicio() {
        MFXTableColumn<Servicio> colID    = new MFXTableColumn<>("ID Servicio", true, Comparator.comparing(Servicio::getIdServicio));
        MFXTableColumn<Servicio> colOrden = new MFXTableColumn<>("Orden",       true, Comparator.comparing(s -> s.getOrden().getIdOrden()));

        colID   .setRowCellFactory(s -> new MFXTableRowCell<>(Servicio::getIdServicio));
        colOrden.setRowCellFactory(s -> new MFXTableRowCell<>(sv -> sv.getOrden().getIdOrden()));

        colID   .setPrefWidth(150);
        colOrden.setPrefWidth(100);

        tbServicio.getTableColumns().addAll(colID, colOrden);
    }

    private void setupFiltros() {
        tbFactura.getFilters().addAll(
                new StringFilter<>("ID", Factura::getIdFactura)
        );
    }

    // ─── Listeners ───────────────────────────────────────────────────────────────

    private void listenerSelectionFactura() {
        tbFactura.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                selectedFactura = newVal.values().iterator().next();
                mostrarInformacionFactura(selectedFactura);
            }
        });
    }

    private void listenerSelectionPago() {
        tbPago.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) selectedPago = newVal.values().iterator().next();
        });
    }

    private void listenerSelectionServicio() {
        tbServicio.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) selectedServicio = newVal.values().iterator().next();
        });
    }

    // ─── Carga de datos ──────────────────────────────────────────────────────────

    private void obtenerFacturas() {
        Collection<Factura> fs = facturaController.obtenerListafactura();
        if (fs != null) listaFacturas.setAll(fs);
    }

    private void obtenerPagos() {
        Collection<Pago> ps = facturaController.obtenerListaPagos();
        if (ps != null) listaPagos.setAll(ps);
    }

    private void obtenerServicios() {
        Collection<Servicio> ss = facturaController.obtenerListaServicio();
        if (ss != null) listaServicio.setAll(ss);
    }

    // ─── Mostrar información ─────────────────────────────────────────────────────

    private void mostrarInformacionFactura(Factura f) {
        if (f == null) return;
        txtID.setText(f.getIdFactura());
    }

    // ─── CRUD ────────────────────────────────────────────────────────────────────

    private void agregarFactura() {
        Factura f = buildFactura();
        if (facturaController.crearfactura(f)) {
            listaFacturas.add(f);
            limpiarCampos();
        }
    }

    private void eliminarFactura() {
        if (selectedFactura != null && facturaController.eliminarfactura(selectedFactura)) {
            listaFacturas.remove(selectedFactura);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void actualizarFactura() {
        if (selectedFactura != null &&
                facturaController.actualizarfactura(selectedFactura.getIdFactura(), buildFactura())) {
            int index = listaFacturas.indexOf(selectedFactura);
            if (index >= 0) listaFacturas.set(index, buildFactura());
            tbFactura.update();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private Factura buildFactura() {
        return new Factura(txtID.getText(), selectedPago, selectedServicio);
    }

    private void limpiarSeleccion() {
        tbFactura .getSelectionModel().clearSelection();
        tbPago    .getSelectionModel().clearSelection();
        tbServicio.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtID.clear();
        selectedFactura  = null;
        selectedPago     = null;
        selectedServicio = null;
    }

    public void setApp(App app) { this.app = app; }
}

