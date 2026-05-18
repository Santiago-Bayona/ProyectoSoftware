package co.edu.uniquindio.poo.demo.Back;

import java.util.LinkedList;

public class Servicio{

    public String idServicio, descripcion;
    public double precioManoObra, precioRepuestos;
    public EstadoServicio estadoServicio;
    public LinkedList<Repuesto> repuestos;
    public Orden orden;
    public enum EstadoServicio{
        En_Proceso, Finalizado, Cancelado
    }

    public Servicio(String idServicio, String descripcion, double precioManoObra, EstadoServicio estadoServicio, Orden orden) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.precioManoObra = precioManoObra;
        this.precioRepuestos = 0.0;
        this.estadoServicio = estadoServicio;
        this.orden = orden;
        this.repuestos = new LinkedList<>();
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioManoObra() {
        return precioManoObra;
    }

    public void setPrecioManoObra(double precioManoObra) {
        this.precioManoObra = precioManoObra;
    }

    public double getPrecioRepuestos() {
        return precioRepuestos;
    }

    public void setPrecioRepuestos(double precioRepuestos) {
        this.precioRepuestos = precioRepuestos;
    }

    public EstadoServicio getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(EstadoServicio estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public LinkedList<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(LinkedList<Repuesto> repuestos) {
        this.repuestos = repuestos;
    }

    public void agregarRepuesto(Repuesto repuesto) {
        if (repuesto != null && !repuestos.contains(repuesto)) {
            repuestos.add(repuesto);
        }
    }


    /**
     * Retorna los nombres de los mecánicos separados por coma
     */
    /**
     * Retorna los nombres de los mecánicos separados por coma
     * Ejemplo: "Jose, Lucas, Pedro"
     * @return String con los nombres de los mecánicos
     */
    public String getNombresRepuestos() {
        if (repuestos.isEmpty()) {
            return "Sin asignar";
        }
        StringBuilder nombres = new StringBuilder();
        for (int i = 0; i < repuestos.size(); i++) {
            nombres.append(repuestos.get(i).getNombre());
            if (i < repuestos.size() - 1) {
                nombres.append(", ");
            }
        }
        return nombres.toString();
    }


    public Double calcularPrecioRepuestos() {
        double total = 0.0;

        if (repuestos == null || repuestos.isEmpty()) {
            setPrecioRepuestos(0.0);
            return null;
        }

        for (Repuesto repuesto : repuestos) {
            if (repuesto != null) {
                total += repuesto.getPrecio();
                repuesto.setStock(repuesto.getStock() - 1);
            }
        }

        setPrecioRepuestos(total);
        return total;
    }






    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio='" + idServicio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioManoObra=" + precioManoObra +
                ", precioRepuestos=" + precioRepuestos +
                ", estadoServicio=" + estadoServicio +
                ", orden=" + orden +
                '}';
    }
}
