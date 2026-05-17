package co.edu.uniquindio.poo.demo.Controller;

import co.edu.uniquindio.poo.demo.Back.*;

import java.util.Collection;

public class OrdenController {

    private Taller taller;

    public OrdenController(Taller taller) {
        this.taller = taller;
    }



    public boolean crearOrden(Orden orden) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarOrden(orden);
    }



    public Collection<Orden> obtenerListaOrden() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de Ordens porque la taller es null.");
            return null;
        }
        return taller.getOrdenes();
    }

    public Collection<Mecanico> obtenerListaMecanicos() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de vehículos porque la taller es null.");
            return null;
        }
        return taller.getMecanicos();
    }

    public boolean eliminarOrden(Orden cedula) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar Orden ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarOrden(cedula);
    }

    public Collection<Cliente> obtenerListaCliente(){
        if (taller == null) {

        }
        return taller.getClientes();
    }

    public Collection<Vehiculo> obtenerListaVehiculo(){
        if (taller == null) {

        }
        return taller.getVehiculos();
    }


    public boolean actualizarOrden(String cedula, Orden orden) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar Orden porque la hospital es null.");
            return false;
        }
        return taller.actualizarOrden(cedula, orden);
    }
}
