package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Cliente;
import co.edu.uniquindio.poo.demo.Back.Estado;
import co.edu.uniquindio.poo.demo.Controller.ClienteController;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

import java.util.Comparator;

public class ClienteVC {

    App app;
    ClienteController clienteController;
    private ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
    private Cliente selectedCliente;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private MFXButton    bttAgregarCliente;
    @FXML private MFXButton    bttEliminarCliente;
    @FXML private MFXButton    bttLimpiarCliente;
    @FXML private MFXButton    bttModificarCliente;
    @FXML private MFXButton    bttVolver;

    @FXML private MFXComboBox<Estado> cbxEstado;

    @FXML private MFXTableView<Cliente> tbCliente;

    @FXML private MFXTextField txtDireccion;
    @FXML private MFXTextField txtDocumento;
    @FXML private MFXTextField txtEmail;
    @FXML private MFXTextField txtNombre;
    @FXML private MFXTextField txtTelefono;

    // ─── Acciones FXML ───────────────────────────────────────────────────────────

    @FXML void AgregarCliente(ActionEvent event)  { agregarCliente(); }
    @FXML void EliminarCliente(ActionEvent event) { eliminarCliente(); }
    @FXML void LimpiarCliente(ActionEvent event)  { limpiarCampos(); }
    @FXML void ModificarCliente(ActionEvent event){ actualizarCliente(); }

    @FXML
    void Volver(ActionEvent event) {
        try { app.MenuAdmin(); } catch (Exception e) { e.printStackTrace(); }
    }

    // ─── Inicialización ──────────────────────────────────────────────────────────

    @FXML
    void initialize() {
        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }
        clienteController = new ClienteController(App.taller);
        initView();
    }

    private void initView() {
        setupColumnas();
        setupFiltros();
        obtenerCliente();
        tbCliente.setItems(listaCliente);
        listenerSelection();
    }

    // ─── Configuración de columnas (MaterialFX estilo) ───────────────────────────

    private void setupColumnas() {

        MFXTableColumn<Cliente> colDocumento = new MFXTableColumn<>("Documento", true, Comparator.comparing(Cliente::getDocumento));
        MFXTableColumn<Cliente> colNombre    = new MFXTableColumn<>("Nombre",    true, Comparator.comparing(Cliente::getNombre));
        MFXTableColumn<Cliente> colTelefono  = new MFXTableColumn<>("Teléfono",  true, Comparator.comparing(Cliente::getTelefonono));
        MFXTableColumn<Cliente> colEmail     = new MFXTableColumn<>("Email",     true, Comparator.comparing(Cliente::getEmail));
        MFXTableColumn<Cliente> colDireccion = new MFXTableColumn<>("Dirección", true, Comparator.comparing(Cliente::getDireccion));
        MFXTableColumn<Cliente> colEstado    = new MFXTableColumn<>("Estado",    true, Comparator.comparing(c -> c.getEstado().toString()));

        colDocumento.setRowCellFactory(c -> new MFXTableRowCell<>(Cliente::getDocumento));
        colNombre   .setRowCellFactory(c -> new MFXTableRowCell<>(Cliente::getNombre));
        colTelefono .setRowCellFactory(c -> new MFXTableRowCell<>(Cliente::getTelefonono));
        colEmail    .setRowCellFactory(c -> new MFXTableRowCell<>(Cliente::getEmail));
        colDireccion.setRowCellFactory(c -> new MFXTableRowCell<>(Cliente::getDireccion));
        colEstado   .setRowCellFactory(c -> {
            MFXTableRowCell<Cliente, String> cell =
                    new MFXTableRowCell<>(cl -> cl.getEstado() != null ? cl.getEstado().toString() : "");
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        // Anchos proporcionales
        colDocumento.setPrefWidth(110);
        colNombre   .setPrefWidth(130);
        colTelefono .setPrefWidth(100);
        colEmail    .setPrefWidth(160);
        colDireccion.setPrefWidth(140);
        colEstado   .setPrefWidth(80);

        tbCliente.getTableColumns().addAll(
                colDocumento, colNombre, colTelefono, colEmail, colDireccion, colEstado
        );
    }

    /** Filtros de búsqueda que MaterialFX muestra en la barra superior de la tabla */
    private void setupFiltros() {
        tbCliente.getFilters().addAll(
                new StringFilter<>("Documento", Cliente::getDocumento),
                new StringFilter<>("Nombre",    Cliente::getNombre),
                new StringFilter<>("Email",     Cliente::getEmail)
        );
    }

    // ─── Listener de selección ───────────────────────────────────────────────────

    private void listenerSelection() {
        tbCliente.getSelectionModel().selectionProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                // MFXTableView devuelve un Map<Integer, Cliente>; tomamos el primer valor
                selectedCliente = newVal.values().iterator().next();
                mostrarInformacionCliente(selectedCliente);
            }
        });
    }

    // ─── Lógica de negocio ───────────────────────────────────────────────────────

    private void obtenerCliente() {
        if (clienteController != null) {
            listaCliente.addAll(clienteController.obtenerListacliente());
        }
    }

    private void mostrarInformacionCliente(Cliente cliente) {
        if (cliente == null) return;
        txtDocumento.setText(cliente.getDocumento());
        txtNombre   .setText(cliente.getNombre());
        txtTelefono .setText(cliente.getTelefonono());
        txtEmail    .setText(cliente.getEmail());
        txtDireccion.setText(cliente.getDireccion());
        cbxEstado.setValue(cliente.getEstado());
    }

    private void agregarCliente() {
        Cliente cliente = buildCliente();
        if (clienteController.crearCliente(cliente)) {
            listaCliente.add(cliente);
            limpiarCampos();
        }
    }

    private void eliminarCliente() {
        if (clienteController.eliminarcliente(selectedCliente)) {
            listaCliente.remove(selectedCliente);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    private void actualizarCliente() {
        if (selectedCliente != null &&
                clienteController.actualizarCliente(selectedCliente.getDocumento(), buildCliente())) {
            int index = listaCliente.indexOf(selectedCliente);
            if (index >= 0) {
                listaCliente.set(index, buildCliente());
            }
            tbCliente.update();   // MFXTableView usa update() en lugar de refresh()
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private Cliente buildCliente() {
        return new Cliente(
                txtDocumento.getText(),
                txtNombre   .getText(),
                txtTelefono .getText(),
                txtEmail    .getText(),
                txtDireccion.getText(),
                cbxEstado   .getValue()
        );
    }

    private void limpiarSeleccion() {
        tbCliente.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtDocumento.clear();
        txtNombre   .clear();
        txtTelefono .clear();
        txtEmail    .clear();
        txtDireccion.clear();
        cbxEstado.setValue(null);
        selectedCliente = null;
    }

    // ─── setApp ──────────────────────────────────────────────────────────────────

    public void setApp(App app) {
        this.app = app;
        // El ComboBox de MFX se llena con setItems(), igual que el estándar
        ObservableList<Estado> options = FXCollections.observableArrayList(Estado.values());
        cbxEstado.setItems(options);
    }
}
