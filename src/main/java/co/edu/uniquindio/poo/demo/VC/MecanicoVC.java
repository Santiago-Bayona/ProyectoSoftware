package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Cliente;
import co.edu.uniquindio.poo.demo.Back.Mecanico;
import co.edu.uniquindio.poo.demo.Back.Estado;
import co.edu.uniquindio.poo.demo.Back.Mecanico;
import co.edu.uniquindio.poo.demo.Controller.MecanicoController;
import co.edu.uniquindio.poo.demo.Controller.MecanicoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MecanicoVC {
    
    App app;
    MecanicoController mecanicoController;
    private ObservableList<Mecanico> listaMecanicos = FXCollections.observableArrayList();
    private Mecanico selectedMecanico;
    
    
    

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttAgregarMecanico;

    @FXML
    private Button bttEliminarMecanico;

    @FXML
    private Button bttLimpiarMecanico;

    @FXML
    private Button bttModificarMecanico;

    @FXML
    private Button bttVolver;

    @FXML
    private ComboBox<Estado> cbxEstado;

    @FXML
    private TableView<Mecanico> tbMecanico;

    @FXML
    private TableColumn<Mecanico, String> tbcDireccion;

    @FXML
    private TableColumn<Mecanico, String> tbcDocumento;

    @FXML
    private TableColumn<Mecanico, String> tbcEmail;

    @FXML
    private TableColumn<Mecanico, String> tbcEspecialidad;

    @FXML
    private TableColumn<Mecanico, String> tbcEstado;

    @FXML
    private TableColumn<Mecanico, String> tbcNombre;

    @FXML
    private TableColumn<Mecanico, String> tbcTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void AgregarMecanico(ActionEvent event) {
        agregarMecanico();
    }

    @FXML
    void EliminarMecanico(ActionEvent event) {
        eliminarMecanico();
    }

    @FXML
    void LimpiarMecanico(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void ModificarMecanico(ActionEvent event) {
        actualizarMecanico();
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
        assert bttAgregarMecanico != null : "fx:id=\"bttAgregarMecanico\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert bttEliminarMecanico != null : "fx:id=\"bttEliminarMecanico\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert bttLimpiarMecanico != null : "fx:id=\"bttLimpiarMecanico\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert bttModificarMecanico != null : "fx:id=\"bttModificarMecanico\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert bttVolver != null : "fx:id=\"bttVolver\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert cbxEstado != null : "fx:id=\"cbxEstado\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbMecanico != null : "fx:id=\"tbMecanico\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbcDireccion != null : "fx:id=\"tbcDireccion\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbcDocumento != null : "fx:id=\"tbcDocumento\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbcEmail != null : "fx:id=\"tbcEmail\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbcEspecialidad != null : "fx:id=\"tbcEspecialidad\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbcEstado != null : "fx:id=\"tbcEstado\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbcNombre != null : "fx:id=\"tbcNombre\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert tbcTelefono != null : "fx:id=\"tbcTelefono\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert txtDireccion != null : "fx:id=\"txtDireccion\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert txtDocumento != null : "fx:id=\"txtDocumento\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert txtEspecialidad != null : "fx:id=\"txtEspecialidad\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'Mecanico.fxml'.";
        assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'Mecanico.fxml'.";

        if (App.taller == null) {
            System.err.println("No se puede agregar el taller");
            return;
        }

        mecanicoController = new MecanicoController(App.taller);
        initView();
    }

    private void initView() {

        initDataBinding();
        obtenerMecanico();
        tbMecanico.getItems().clear();

        // Agregar los elementos a la tabla
        tbMecanico.setItems(listaMecanicos);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    /**
     * Método que configura el enlace entre los datos y la vista.
     */

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initDataBinding() {
        tbcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        tbcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefonono()));
        tbcDocumento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDocumento()));
        tbcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcEspecialidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEspcialidad()));
        tbcEstado.setCellValueFactory(celldata -> {
            Estado categoria = celldata.getValue().getEstado();
            String categoriaString = (categoria != null) ? categoria.toString() : "Sin Tipo";
            return new SimpleStringProperty(categoriaString);
        });

    }

    /**
     * Metodo que obtiene la lista de doctores del sistema.
     */

    private void obtenerMecanico() {
        if (mecanicoController != null) {
            listaMecanicos.addAll(mecanicoController.obtenerListaMecanico());
        } else {
            System.err.println("doctorController no está inicializado.");
        }
    }

    /**
     * Método que configura un listener para la selección de elementos en la tabla de doctores.
     */

    private void listenerSelection() {
        tbMecanico.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedMecanico = newSelection;
            mostrarInformacionMecanico(selectedMecanico);
        });
    }

    /**
     * Metodo que muestra la información del doctor  seleccionado en el campo de texto.
     */

    private void mostrarInformacionMecanico(Mecanico Mecanico) {
        if (Mecanico != null) {
            // Asignar valores a los campos de texto
            txtDireccion.setText(Mecanico.getDireccion());
            txtDocumento.setText(Mecanico.getDocumento());
            txtEmail.setText(Mecanico.getEmail());
            txtNombre.setText(Mecanico.getNombre());
            txtTelefono.setText(Mecanico.getTelefonono());
            txtEspecialidad.setText(Mecanico.getEspcialidad());
            cbxEstado.getSelectionModel().select(Mecanico.getEstado());
        }
    }

    /**
     * Metodo que agrega un nuevo doctor a la lista y lo almacena en el controlador.
     * Se crea un nuevo doctor a partir de los datos ingresados y se añade a la lista si la operación es exitosa.
     */

    private void agregarMecanico() {
        Mecanico Mecanico = buildMecanico();
        if(mecanicoController.crearMecanico(Mecanico)){
            listaMecanicos.add(Mecanico);
            limpiarCampos();
        }
    }

    /**
     * Metodo que construye un objeto de tipo doctor con la información ingresada.
     * @return Un objeto Doctor con el código ingresado en el campo de texto.
     */

    private Mecanico buildMecanico() {
        Mecanico Mecanico = new Mecanico(
                txtDocumento.getText(),
                txtNombre.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                txtDireccion.getText(),
                txtEspecialidad.getText(),
                cbxEstado.getValue()
        );
        return Mecanico;
    }


    /**
     * Metodo que elimina el doctor seleccionado de la lista y del controlador.
     * Si la eliminación es exitosa, también se eliminan los datos de la tabla y se limpian los campos de entrada.
     */

    private void eliminarMecanico() {
        if (mecanicoController.eliminarMecanico(selectedMecanico)) {
            listaMecanicos.remove(selectedMecanico);
            limpiarCampos();
            limpiarSeleccion();
        }
    }

    /**
     * Metodo que actualiza la información del doctor seleccionado.
     * Si el Doctor se encuentra en la lista y la actualización es exitosa,
     * se reemplaza por el nuevo objeto actualizado y se refresca la tabla.
     */

    private void actualizarMecanico() {

        if (selectedMecanico != null &&
                mecanicoController.actualizarMecanico(selectedMecanico.getDocumento(), buildMecanico())) {

            int index = listaMecanicos.indexOf(selectedMecanico);
            if (index >= 0) {
                listaMecanicos.set(index, buildMecanico());
            }

            tbMecanico.refresh();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    /**
     * Metodo que limpia la selección de la tabla de doctor y los campos de entrada.
     */
    private void limpiarSeleccion() {
        tbMecanico.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    /**
     * Metodo que limpia los campos de entrada y la selección de la tabla de Doctor.
     */
    private void limpiarCampos() {
        txtDocumento.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtDireccion.clear();
        txtNombre.clear();
        cbxEstado.setValue(null);
    }

    /**
     * Establece la instancia de la aplicación para acceder a sus métodos.
     * @param app
     */

    public void setApp(App app) {
        this.app = app;

        /**
         * Metodo que inicializa la comboBox con las opciones de la especialidad del objeto Doctor
         */

        ObservableList<Estado> options = FXCollections.observableArrayList(Estado.values());
        cbxEstado.setItems((options));
    }

}

