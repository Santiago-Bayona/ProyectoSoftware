package co.edu.uniquindio.poo.demo.Back;

import java.time.LocalDate;
import java.util.LinkedList;

public class Orden {

    public String idOrden;
    public Vehiculo vehiculo;
    public Cliente cliente;
    public LinkedList<Mecanico> mecanicos;
    public LocalDate fecha;

    public Orden(String idOrden, Vehiculo vehiculo, LocalDate fecha) {
        this.idOrden = idOrden;
        this.vehiculo = vehiculo;
        this.cliente = vehiculo.getDuenio();
        this.fecha = fecha;
        this.mecanicos = new LinkedList<>();
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LinkedList<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(LinkedList<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public void agregarMecanico(Mecanico mecanico) {
        if (mecanico != null && !mecanicos.contains(mecanico)) {
            mecanicos.add(mecanico);
        }
    }

    /**
     * Elimina un mecánico de la orden
     */
    public void eliminarMecanico(Mecanico mecanico) {
        mecanicos.remove(mecanico);
    }

    /**
     * Retorna los nombres de los mecánicos separados por coma
     */
    /**
     * Retorna los nombres de los mecánicos separados por coma
     * Ejemplo: "Jose, Lucas, Pedro"
     * @return String con los nombres de los mecánicos
     */
    public String getNombresMecanicos() {
        if (mecanicos.isEmpty()) {
            return "Sin asignar";
        }
        StringBuilder nombres = new StringBuilder();
        for (int i = 0; i < mecanicos.size(); i++) {
            nombres.append(mecanicos.get(i).getNombre());
            if (i < mecanicos.size() - 1) {
                nombres.append(", ");
            }
        }
        return nombres.toString();
    }

    @Override
    public String toString() {
        return "Orden{" +
                "idOrden='" + idOrden + '\'' +
                ", vehiculo=" + vehiculo +
                ", cliente=" + cliente +
                ", fecha=" + fecha +
                '}';
    }
}
