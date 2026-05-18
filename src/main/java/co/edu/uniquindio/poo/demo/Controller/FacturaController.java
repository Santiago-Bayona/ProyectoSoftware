package co.edu.uniquindio.poo.demo.Controller;

import co.edu.uniquindio.poo.demo.Back.Factura;
import co.edu.uniquindio.poo.demo.Back.Pago;
import co.edu.uniquindio.poo.demo.Back.Servicio;
import co.edu.uniquindio.poo.demo.Back.Taller;

import java.util.Collection;

public class FacturaController {

    private Taller taller;

    public FacturaController(Taller taller) {
        this.taller = taller;
    }



    public boolean crearfactura(Factura factura) {
        if (taller == null) {
            System.err.println("Error: La Competnecia no ha sido inicializada.");
            return false;
        }
        return taller.agregarFactura(factura);
    }



    public Collection<Factura> obtenerListafactura() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de facturas porque la taller es null.");
            return null;
        }
        return taller.getFacturas();
    }

    public boolean eliminarfactura(Factura cedula) {
        if (taller == null) {
            System.err.println("Error: No se puede eliminar factura ya que la comptencia  es null.");
            return false;
        }
        return taller.eliminarFactura(cedula);
    }

    public Collection<Pago> obtenerListaPagos() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de Pago es null.");
            return null;
        }

        return taller.getPagos();

    }

    public Collection<Servicio> obtenerListaServicio() {
        if (taller == null) {
            System.err.println("Error: No se puede obtener la lista de servicios es null.");
            return null;
        }

        return taller.getServicios();

    }


    public boolean actualizarfactura(String cedula, Factura factura) {
        if (taller == null) {
            System.err.println("Error: No se puede actualizar factura porque la hospital es null.");
            return false;
        }
        return taller.actualizarFactura(cedula, factura);
    }
}
