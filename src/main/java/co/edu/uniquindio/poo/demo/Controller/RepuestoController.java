package co.edu.uniquindio.poo.demo.Controller;


import co.edu.uniquindio.poo.demo.Back.Repuesto;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class RepuestoController {

    private Taller taller;

    public RepuestoController(Taller taller) {
        this.taller = taller;
    }



    public boolean crearrespuesto(Repuesto respuesto) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarRespuesto(respuesto);
    }



    public Collection<Repuesto> obtenerListarespuesto() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de respuestos porque la taller es null.");
            return null;
        }
        return taller.getRespuestos();
    }

    public boolean eliminarrespuesto(Repuesto cedula) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar respuesto ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarRespuesto(cedula);
    }


    public boolean actualizarRepuesto(String cedula, Repuesto respuesto) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar respuesto porque la hospital es null.");
            return false;
        }
        return taller.actualizarRespuesto(cedula, respuesto);
    }
}
