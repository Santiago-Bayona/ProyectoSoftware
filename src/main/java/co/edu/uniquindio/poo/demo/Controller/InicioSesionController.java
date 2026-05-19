package co.edu.uniquindio.poo.demo.Controller;

import co.edu.uniquindio.poo.demo.Back.Factura;
import co.edu.uniquindio.poo.demo.Back.Mecanico;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class InicioSesionController {

    private Taller taller;

    public InicioSesionController(Taller taller) {
        this.taller = taller;
    }

    public Collection<Mecanico> obtenerListaMecanico() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de facturas porque la taller es null.");
            return null;
        }
        return taller.getMecanicos();
    }



}
