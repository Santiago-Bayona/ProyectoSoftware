package co.edu.uniquindio.poo.demo.VC;

import co.edu.uniquindio.poo.demo.App;
import co.edu.uniquindio.poo.demo.Back.Mecanico;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class InicioSesionVC {

    App app;
    private ObservableList<Mecanico> listaMecanicos= FXCollections.observableArrayList();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXButton bttIngresar;

    @FXML
    private MFXPasswordField txtContrasena;

    @FXML
    private MFXTextField txtUsuario;

    @FXML
    public void Ingresar (ActionEvent event) {
        Verificacion();
    }

    @FXML
    void initialize() {
        assert bttIngresar != null : "fx:id=\"bttIngresar\" was not injected: check your FXML file 'InicioSesion.fxml'.";
        assert txtContrasena != null : "fx:id=\"txtContrasena\" was not injected: check your FXML file 'InicioSesion.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'InicioSesion.fxml'.";

    }

    public void Verificacion() {

        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario == null || usuario.isBlank() ||
                contrasena == null || contrasena.isBlank()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar usuario y contraseña");
            alert.showAndWait();

            return;
        }

        if (usuario.equals("Admin@gmail.com")
                && contrasena.equals("1235")) {
            try {
                app.MenuAdmin();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }

        for (Mecanico mecanico : listaMecanicos) {

            if (mecanico.getEmail().equals(usuario) &&
                    mecanico.getDocumento().equals(contrasena)) {
                try {
                    app.MenuEmpleado();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Credenciales Incorrectas");
        alert.setHeaderText(null);
        alert.setContentText("Usuario o contraseña incorrectos");
        alert.showAndWait();
    }

    public void setApp(App app) {
        this.app = app;
        listaMecanicos.addAll(App.taller.getMecanicos());
    }

}

