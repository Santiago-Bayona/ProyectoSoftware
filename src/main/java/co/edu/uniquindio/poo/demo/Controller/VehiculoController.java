package co.edu.uniquindio.poo.demo.Controller;

import co.edu.uniquindio.poo.demo.Back.Cliente;
import co.edu.uniquindio.poo.demo.Back.Vehiculo;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class VehiculoController {

    private Taller taller;

    public VehiculoController(Taller taller) {
        this.taller = taller;
    }



    public boolean crearVehiculo(Vehiculo vehiculo) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarVehiculo(vehiculo);
    }



    public Collection<Vehiculo> obtenerListavehiculo() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de vehiculos porque la taller es null.");
            return null;
        }
        return taller.getVehiculos();
    }

    public boolean eliminarvehiculo(Vehiculo cedula) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar vehiculo ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarVehiculo(cedula);
    }

    public Collection<Cliente> obtenerListaCliente(){
        if (taller == null) {

        }
        return taller.getClientes();
    }


    public boolean actualizarVehiculo(String cedula, Vehiculo vehiculo) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar vehiculo porque la hospital es null.");
            return false;
        }
        return taller.actualizarVehiculo(cedula, vehiculo);
    }
}
