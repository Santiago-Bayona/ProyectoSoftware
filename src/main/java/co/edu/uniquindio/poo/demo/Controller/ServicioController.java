package co.edu.uniquindio.poo.demo.Controller;

import co.edu.uniquindio.poo.demo.Back.Orden;
import co.edu.uniquindio.poo.demo.Back.Repuesto;
import co.edu.uniquindio.poo.demo.Back.Servicio;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class ServicioController {

    private Taller taller;

    public ServicioController(Taller taller) {
        this.taller = taller;
    }



    public boolean crearServicio(Servicio servicio) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarServicio(servicio);
    }



    public Collection<Servicio> obtenerListaServicio() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de Servicios porque la taller es null.");
            return null;
        }
        return taller.getServicios();
    }

    public boolean eliminarServicio(Servicio cedula) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar Servicio ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarServicio(cedula);
    }

    public Collection<Orden> obtenerListaOrden() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de Ordens porque la taller es null.");
            return null;
        }
        return taller.getOrdenes();
    }

    public Collection<Repuesto> obtenerListaRepuestos() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de Ordens porque la taller es null.");
            return null;
        }
        return taller.getRespuestos();
    }


    public boolean actualizarServicio(String cedula, Servicio servicio) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar Servicio porque la hospital es null.");
            return false;
        }
        return taller.actualizarServicio(cedula, servicio);
    }
}
