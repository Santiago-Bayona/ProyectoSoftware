package co.edu.uniquindio.poo.demo.Controller;


import co.edu.uniquindio.poo.demo.Back.Cliente;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class ClienteController {

    private Taller taller;

    public ClienteController(Taller taller) {
        this.taller = taller;
    }


    public boolean crearCliente(Cliente cliente) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarCliente(cliente);
    }

    /**
     * Metodo que obtiene la lista de doctores registrados en el hospital.
     * @return Una colección de objetos Doctor si el hospital está inicializado.
     */

    public Collection<Cliente> obtenerListacliente() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de clientes porque la competencia es null.");
            return null;
        }
        return taller.getClientes();
    }



    public boolean eliminarcliente(Cliente cliente) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar cliente ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarCliente(cliente);
    }

    public boolean actualizarCliente(String cedula, Cliente cliente) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar cliente porque la hospital es null.");
            return false;
        }
        return taller.actualizarCliente(cedula, cliente);
    }
}
