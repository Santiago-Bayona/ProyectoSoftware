package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Pago;
import co.edu.uniquindio.poo.demo.Controller.PagoController;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
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

public class PagoVC {

    App app;
    PagoController pagocontroller;
    private ObservableList<Pago> listaPago = FXCollections.observableArrayList();
    private Pago selectedPago;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton bttAgregarPago;
    @FXML private MFXButton bttEliminarPago;
    @FXML private MFXButton bttLimpiarPago;
    @FXML private MFXButton bttModificarPago;
    @FXML private MFXButton bttVolver;

    @FXML private MFXComboBox<Pago.EstadoPago>  cbxEstado;
    @FXML private MFXComboBox<Pago.MetodoPago>  cbxMetodo;
    @FXML private MFXDatePicker                  dpFecha;
    @FXML private MFXTableView<Pago>             tbPago;
    @FXML private MFXTextField                   txtID;

    @FXML void AgregarPago(ActionEvent event)  { agregarPago(); }
    @FXML void EliminarPago(ActionEvent event) { eliminarPago(); }
    @FXML void LimpiarPago(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarPago(ActionEvent event){ actualizarPago(); }
    @FXML void Volver(ActionEvent event) {
        try { app.MenuAdmin(); } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    void initialize() {
        if (App.taller == null) { System.err.println("Taller null"); return; }
        pagocontroller = new PagoController(App.taller);
        setupColumnas();
        setupFiltros();
        obtenerPago();
        tbPago.setItems(listaPago);
        listenerSelection();
    }

    private void setupColumnas() {
        MFXTableColumn<Pago> colID     = new MFXTableColumn<>("ID",     true, Comparator.comparing(Pago::getIdPago));
        MFXTableColumn<Pago> colFecha  = new MFXTableColumn<>("Fecha",  true, Comparator.comparing(Pago::getFechaPago));
        MFXTableColumn<Pago> colMetodo = new MFXTableColumn<>("Método", true, Comparator.comparing(p -> p.getMetodo().toString()));
        MFXTableColumn<Pago> colEstado = new MFXTableColumn<>("Estado", true, Comparator.comparing(p -> p.getEstado().toString()));

        colID    .setRowCellFactory(p -> new MFXTableRowCell<>(Pago::getIdPago));
        colFecha .setRowCellFactory(p -> new MFXTableRowCell<>(pa -> pa.getFechaPago().format(FORMATTER)));
        colMetodo.setRowCellFactory(p -> new MFXTableRowCell<>(pa -> pa.getMetodo() != null ? pa.getMetodo().toString() : ""));
        colEstado.setRowCellFactory(p -> new MFXTableRowCell<>(pa -> pa.getEstado() != null ? pa.getEstado().toString() : ""));

        colID    .setPrefWidth(100);
        colFecha .setPrefWidth(120);
        colMetodo.setPrefWidth(120);
        colEstado.setPrefWidth(120);

        tbPago.getTableColumns().addAll(colID, colFecha, colMetodo, colEstado);
    }

    private void setupFiltros() {
        tbPago.getFilters().addAll(
                new StringFilter<>("ID", Pago::getIdPago)
        );
    }

    private void listenerSelection() {
        tbPago.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                selectedPago = newVal.values().iterator().next();
                mostrarInformacionPago(selectedPago);
            }
        });
    }

    private void obtenerPago() {
        if (pagocontroller != null)
            listaPago.addAll(pagocontroller.obtenerListapago());
    }

    private void mostrarInformacionPago(Pago p) {
        if (p == null) return;
        txtID.setText(p.getIdPago());
        dpFecha.setValue(p.getFechaPago());
        cbxEstado.setValue(p.getEstado());
        cbxMetodo.setValue(p.getMetodo());
    }

    private void agregarPago() {
        Pago p = buildPago();
        if (pagocontroller.crearpago(p)) {
            listaPago.add(p);
            limpiarCampos();
        }
    }

    private void eliminarPago() {
        if (selectedPago != null && pagocontroller.eliminarpago(selectedPago)) {
            listaPago.remove(selectedPago);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void actualizarPago() {
        if (selectedPago != null &&
                pagocontroller.actualizarpago(selectedPago.getIdPago(), buildPago())) {
            int index = listaPago.indexOf(selectedPago);
            if (index >= 0) listaPago.set(index, buildPago());
            tbPago.update();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private Pago buildPago() {
        return new Pago(txtID.getText(), dpFecha.getValue(), cbxMetodo.getValue(), cbxEstado.getValue());
    }

    private void limpiarSeleccion() {
        tbPago.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtID.clear();
        dpFecha.setValue(null);
        cbxEstado.setValue(null);
        cbxMetodo.setValue(null);
        selectedPago = null;
    }

    public void setApp(App app) {
        this.app = app;
        cbxEstado.setItems(FXCollections.observableArrayList(Pago.EstadoPago.values()));
        cbxMetodo.setItems(FXCollections.observableArrayList(Pago.MetodoPago.values()));
    }
}

