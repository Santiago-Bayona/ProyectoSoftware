package co.edu.uniquindio.poo.demo.VC;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.demo.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipalVC {

    App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttCliente;

    @FXML
    private Button bttFactura;

    @FXML
    private Button bttMecanico;

    @FXML
    private Button bttOrden;

    @FXML
    private Button bttPago;

    @FXML
    private Button bttRepuesto;

    @FXML
    private Button bttServicio;

    @FXML
    private Button bttVehiculo;

    @FXML
    void AbirMenuFactura(ActionEvent event) {

    }

    @FXML
    void AbirOrden(ActionEvent event) {
        try {
            app.Orden();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AbrirMecanico(ActionEvent event) {
        try {
            app.Mecanico();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AbrirMenuCliente(ActionEvent event) {
        try {
            app.Cliente();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AbrirPago(ActionEvent event) {

    }

    @FXML
    void AbrirRepuesto(ActionEvent event) {
        try {
            app.Repuesto();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AbrirServicio(ActionEvent event) {

    }

    @FXML
    void AbrirVehiculo(ActionEvent event) {
        try {
            app.Vehiculo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarAplicacion(ActionEvent event) {
        Platform.exit();
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void initialize() {
        assert bttCliente != null : "fx:id=\"bttCliente\" was not injected: check your FXML file 'Untitled'.";
        assert bttFactura != null : "fx:id=\"bttFactura\" was not injected: check your FXML file 'Untitled'.";
        assert bttMecanico != null : "fx:id=\"bttMecanico\" was not injected: check your FXML file 'Untitled'.";
        assert bttOrden != null : "fx:id=\"bttOrden\" was not injected: check your FXML file 'Untitled'.";
        assert bttPago != null : "fx:id=\"bttPago\" was not injected: check your FXML file 'Untitled'.";
        assert bttRepuesto != null : "fx:id=\"bttRepuesto\" was not injected: check your FXML file 'Untitled'.";
        assert bttServicio != null : "fx:id=\"bttServicio\" was not injected: check your FXML file 'Untitled'.";
        assert bttVehiculo != null : "fx:id=\"bttVehiculo\" was not injected: check your FXML file 'Untitled'.";

    }

}

