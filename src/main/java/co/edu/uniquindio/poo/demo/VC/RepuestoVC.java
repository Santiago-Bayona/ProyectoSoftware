package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Repuesto;
import co.edu.uniquindio.poo.demo.Controller.RepuestoController;

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

public class RepuestoVC {

    App app;
    RepuestoController repuestoController;
    private ObservableList<Repuesto> listaRepuesto = FXCollections.observableArrayList();
    private Repuesto selectedRepuesto;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton bttAgregarRepuesto;
    @FXML private MFXButton bttEliminarRepuesto;
    @FXML private MFXButton bttLimpiarRepuesto;
    @FXML private MFXButton bttModificarRepuesto;
    @FXML private MFXButton bttVolver;

    @FXML private MFXTableView<Repuesto> tbRepuesto;

    @FXML private MFXTextField txtID;
    @FXML private MFXTextField txtMarca;
    @FXML private MFXTextField txtNombre;
    @FXML private MFXTextField txtPrecio;
    @FXML private MFXTextField txtStock;

    @FXML void AgregarRepuesto(ActionEvent event)  { agregarRepuesto(); }
    @FXML void EliminarRepuesto(ActionEvent event) { eliminarRepuesto(); }
    @FXML void LimpiarRepuesto(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarRepuesto(ActionEvent event){ actualizarRepuesto(); }
    @FXML void Volver(ActionEvent event) {
        try { app.MenuAdmin(); } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    void initialize() {
        if (App.taller == null) { System.err.println("Taller null"); return; }
        repuestoController = new RepuestoController(App.taller);
        setupColumnas();
        setupFiltros();
        obtenerRepuesto();
        tbRepuesto.setItems(listaRepuesto);
        listenerSelection();
    }

    private void setupColumnas() {
        MFXTableColumn<Repuesto> colID     = new MFXTableColumn<>("ID",     true, Comparator.comparing(Repuesto::getIdRepuesto));
        MFXTableColumn<Repuesto> colNombre = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Repuesto::getNombre));
        MFXTableColumn<Repuesto> colMarca  = new MFXTableColumn<>("Marca",  true, Comparator.comparing(Repuesto::getMarca));
        MFXTableColumn<Repuesto> colPrecio = new MFXTableColumn<>("Precio", true, Comparator.comparingDouble(Repuesto::getPrecio));
        MFXTableColumn<Repuesto> colStock  = new MFXTableColumn<>("Stock",  true, Comparator.comparingInt(Repuesto::getStock));

        colID    .setRowCellFactory(r -> new MFXTableRowCell<>(Repuesto::getIdRepuesto));
        colNombre.setRowCellFactory(r -> new MFXTableRowCell<>(Repuesto::getNombre));
        colMarca .setRowCellFactory(r -> new MFXTableRowCell<>(Repuesto::getMarca));
        colPrecio.setRowCellFactory(r -> new MFXTableRowCell<>(re -> String.valueOf(re.getPrecio())));
        colStock .setRowCellFactory(r -> new MFXTableRowCell<>(re -> String.valueOf(re.getStock())));

        colID    .setPrefWidth(90);
        colNombre.setPrefWidth(160);
        colMarca .setPrefWidth(120);
        colPrecio.setPrefWidth(100);
        colStock .setPrefWidth(80);

        tbRepuesto.getTableColumns().addAll(colID, colNombre, colMarca, colPrecio, colStock);
    }

    private void setupFiltros() {
        tbRepuesto.getFilters().addAll(
                new StringFilter<>("ID",     Repuesto::getIdRepuesto),
                new StringFilter<>("Nombre", Repuesto::getNombre),
                new StringFilter<>("Marca",  Repuesto::getMarca)
        );
    }

    private void listenerSelection() {
        tbRepuesto.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                selectedRepuesto = newVal.values().iterator().next();
                mostrarInformacionRepuesto(selectedRepuesto);
            }
        });
    }

    private void obtenerRepuesto() {
        if (repuestoController != null)
            listaRepuesto.addAll(repuestoController.obtenerListarespuesto());
    }

    private void mostrarInformacionRepuesto(Repuesto r) {
        if (r == null) return;
        txtID    .setText(r.getIdRepuesto());
        txtNombre.setText(r.getNombre());
        txtMarca .setText(r.getMarca());
        txtPrecio.setText(String.valueOf(r.getPrecio()));
        txtStock .setText(String.valueOf(r.getStock()));
    }

    private void agregarRepuesto() {
        Repuesto r = buildRepuesto();
        if (repuestoController.crearrespuesto(r)) {
            listaRepuesto.add(r);
            limpiarCampos();
        }
    }

    private void eliminarRepuesto() {
        if (selectedRepuesto != null && repuestoController.eliminarrespuesto(selectedRepuesto)) {
            listaRepuesto.remove(selectedRepuesto);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void actualizarRepuesto() {
        if (selectedRepuesto != null &&
                repuestoController.actualizarRepuesto(selectedRepuesto.getIdRepuesto(), buildRepuesto())) {
            int index = listaRepuesto.indexOf(selectedRepuesto);
            if (index >= 0) listaRepuesto.set(index, buildRepuesto());
            tbRepuesto.update();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private Repuesto buildRepuesto() {
        return new Repuesto(
                txtNombre.getText(),
                txtID    .getText(),
                txtMarca .getText(),
                Double.parseDouble(txtPrecio.getText()),
                Integer.parseInt(txtStock.getText())
        );
    }

    private void limpiarSeleccion() {
        tbRepuesto.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtID.clear(); txtNombre.clear(); txtMarca.clear();
        txtPrecio.clear(); txtStock.clear();
        selectedRepuesto = null;
    }

    public void setApp(App app) { this.app = app; }
}

