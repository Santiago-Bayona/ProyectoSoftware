package co.edu.uniquindio.poo.demo.Controller;

import co.edu.uniquindio.poo.demo.Back.Mecanico;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class MecanicoController {

    private Taller taller;

    public MecanicoController(Taller taller) {
        this.taller = taller;
    }



    public boolean crearMecanico(Mecanico mecanico) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarMecanico(mecanico);
    }



    public Collection<Mecanico> obtenerListaMecanico() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de Mecanicos porque la taller es null.");
            return null;
        }
        return taller.getMecanicos();
    }

    public boolean eliminarMecanico(Mecanico cedula) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar Mecanico ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarMecanico(cedula);
    }


    public boolean actualizarMecanico(String cedula, Mecanico mecanico) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar Mecanico porque la hospital es null.");
            return false;
        }
        return taller.actualizarMecanico(cedula, mecanico);
    }
}
