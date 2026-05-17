package co.edu.uniquindio.poo.demo.Controller;

import co.edu.uniquindio.poo.demo.Back.Pago;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class PagoController {

    private Taller taller;

    public PagoController(Taller taller) {
        this.taller = taller;
    }



    public boolean crearpago(Pago pago) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarPago(pago);
    }



    public Collection<Pago> obtenerListapago() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de pagos porque la taller es null.");
            return null;
        }
        return taller.getPagos();
    }

    public boolean eliminarpago(Pago cedula) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar pago ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarPago(cedula);
    }


    public boolean actualizarpago(String cedula, Pago pago) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar pago porque la hospital es null.");
            return false;
        }
        return taller.actualizarPago(cedula, pago);
    }
}
