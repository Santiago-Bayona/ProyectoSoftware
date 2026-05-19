package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Estado;
import co.edu.uniquindio.poo.demo.Back.Mecanico;
import co.edu.uniquindio.poo.demo.Controller.MecanicoController;

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
import javafx.geometry.Pos;

public class MecanicoVC {

    App app;
    MecanicoController mecanicoController;
    private ObservableList<Mecanico> listaMecanicos = FXCollections.observableArrayList();
    private Mecanico selectedMecanico;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton bttAgregarMecanico;
    @FXML private MFXButton bttEliminarMecanico;
    @FXML private MFXButton bttLimpiarMecanico;
    @FXML private MFXButton bttModificarMecanico;
    @FXML private MFXButton bttVolver;

    @FXML private MFXComboBox<Estado> cbxEstado;
    @FXML private MFXTableView<Mecanico> tbMecanico;

    @FXML private MFXTextField txtDireccion;
    @FXML private MFXTextField txtDocumento;
    @FXML private MFXTextField txtEmail;
    @FXML private MFXTextField txtEspecialidad;
    @FXML private MFXTextField txtNombre;
    @FXML private MFXTextField txtTelefono;

    @FXML void AgregarMecanico(ActionEvent event)  { agregarMecanico(); }
    @FXML void EliminarMecanico(ActionEvent event) { eliminarMecanico(); }
    @FXML void LimpiarMecanico(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarMecanico(ActionEvent event){ actualizarMecanico(); }
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
        mecanicoController = new MecanicoController(App.taller);
        setupColumnas();
        setupFiltros();
        obtenerMecanico();
        tbMecanico.setItems(listaMecanicos);
        listenerSelection();
    }

    private void setupColumnas() {
        MFXTableColumn<Mecanico> colDoc   = new MFXTableColumn<>("Documento",    true, Comparator.comparing(Mecanico::getDocumento));
        MFXTableColumn<Mecanico> colNom   = new MFXTableColumn<>("Nombre",       true, Comparator.comparing(Mecanico::getNombre));
        MFXTableColumn<Mecanico> colTel   = new MFXTableColumn<>("Teléfono",     true, Comparator.comparing(Mecanico::getTelefonono));
        MFXTableColumn<Mecanico> colEmail = new MFXTableColumn<>("Email",        true, Comparator.comparing(Mecanico::getEmail));
        MFXTableColumn<Mecanico> colDir   = new MFXTableColumn<>("Dirección",    true, Comparator.comparing(Mecanico::getDireccion));
        MFXTableColumn<Mecanico> colEsp   = new MFXTableColumn<>("Especialidad", true, Comparator.comparing(Mecanico::getEspcialidad));
        MFXTableColumn<Mecanico> colEst   = new MFXTableColumn<>("Estado",       true, Comparator.comparing(m -> m.getEstado().toString()));

        colDoc  .setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getDocumento));
        colNom  .setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getNombre));
        colTel  .setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getTelefonono));
        colEmail.setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getEmail));
        colDir  .setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getDireccion));
        colEsp  .setRowCellFactory(m -> new MFXTableRowCell<>(Mecanico::getEspcialidad));
        colEst  .setRowCellFactory(m -> {
            MFXTableRowCell<Mecanico, String> cell =
                    new MFXTableRowCell<>(me -> me.getEstado() != null ? me.getEstado().toString() : "");
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        colDoc  .setPrefWidth(100);
        colNom  .setPrefWidth(120);
        colTel  .setPrefWidth(90);
        colEmail.setPrefWidth(150);
        colDir  .setPrefWidth(120);
        colEsp  .setPrefWidth(110);
        colEst  .setPrefWidth(80);

        tbMecanico.getTableColumns().addAll(colDoc, colNom, colTel, colEmail, colDir, colEsp, colEst);
    }

    private void setupFiltros() {
        tbMecanico.getFilters().addAll(
                new StringFilter<>("Documento",    Mecanico::getDocumento),
                new StringFilter<>("Nombre",       Mecanico::getNombre),
                new StringFilter<>("Especialidad", Mecanico::getEspcialidad)
        );
    }

    private void listenerSelection() {
        tbMecanico.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                selectedMecanico = newVal.values().iterator().next();
                mostrarInformacionMecanico(selectedMecanico);
            }
        });
    }

    private void obtenerMecanico() {
        if (mecanicoController != null)
            listaMecanicos.addAll(mecanicoController.obtenerListaMecanico());
    }

    private void mostrarInformacionMecanico(Mecanico m) {
        if (m == null) return;
        txtDocumento  .setText(m.getDocumento());
        txtNombre     .setText(m.getNombre());
        txtTelefono   .setText(m.getTelefonono());
        txtEmail      .setText(m.getEmail());
        txtDireccion  .setText(m.getDireccion());
        txtEspecialidad.setText(m.getEspcialidad());
        cbxEstado.setValue(m.getEstado());
    }

    private void agregarMecanico() {
        Mecanico m = buildMecanico();
        if (mecanicoController.crearMecanico(m)) {
            listaMecanicos.add(m);
            limpiarCampos();
        }
    }

    private void eliminarMecanico() {
        if (mecanicoController.eliminarMecanico(selectedMecanico)) {
            listaMecanicos.remove(selectedMecanico);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void actualizarMecanico() {
        if (selectedMecanico != null &&
                mecanicoController.actualizarMecanico(selectedMecanico.getDocumento(), buildMecanico())) {
            int index = listaMecanicos.indexOf(selectedMecanico);
            if (index >= 0) listaMecanicos.set(index, buildMecanico());
            tbMecanico.update();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private Mecanico buildMecanico() {
        return new Mecanico(
                txtDocumento.getText(), txtNombre.getText(), txtTelefono.getText(),
                txtEmail.getText(), txtDireccion.getText(), txtEspecialidad.getText(),
                cbxEstado.getValue()
        );
    }

    private void limpiarSeleccion() {
        tbMecanico.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtDocumento.clear(); txtNombre.clear(); txtTelefono.clear();
        txtEmail.clear(); txtDireccion.clear(); txtEspecialidad.clear();
        cbxEstado.setValue(null);
        selectedMecanico = null;
    }

    public void setApp(App app) {
        this.app = app;
        cbxEstado.setItems(FXCollections.observableArrayList(Estado.values()));
    }
}

