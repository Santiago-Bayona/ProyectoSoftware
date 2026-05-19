package co.edu.uniquindio.poo.demo.VC;

import co.edu.uniquindio.poo.demo.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuEmpleadoVC {

    App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXButton bttOrden;

    @FXML
    private MFXButton bttPago;

    @FXML
    private MFXButton bttRepuesto;

    @FXML
    private MFXButton bttServicio;

    @FXML
    private MFXButton bttVehiculo;

    @FXML
    void AbirOrden(ActionEvent event) {
        try {
            app.Orden();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AbrirPago(ActionEvent event) {
        try {
            app.Pago();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            app.Servicio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AbrirVehiculo(ActionEvent event) {
        try {
            app.Vehiculo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert bttOrden != null : "fx:id=\"bttOrden\" was not injected: check your FXML file 'MenuEmpleado.fxml'.";
        assert bttPago != null : "fx:id=\"bttPago\" was not injected: check your FXML file 'MenuEmpleado.fxml'.";
        assert bttRepuesto != null : "fx:id=\"bttRepuesto\" was not injected: check your FXML file 'MenuEmpleado.fxml'.";
        assert bttServicio != null : "fx:id=\"bttServicio\" was not injected: check your FXML file 'MenuEmpleado.fxml'.";
        assert bttVehiculo != null : "fx:id=\"bttVehiculo\" was not injected: check your FXML file 'MenuEmpleado.fxml'.";

    }

    public void setApp(App app) {
        this.app = app;
    }

}

