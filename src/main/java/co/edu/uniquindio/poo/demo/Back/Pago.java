package co.edu.uniquindio.poo.demo.Back;

import java.time.LocalDate;

public class Pago {

    public String idPago;
    public LocalDate fechaPago;
    public MetodoPago metodo;
    public EstadoPago estado;

    public enum MetodoPago{
        Tarjeta, Efectivo, Transferencia
    }
    public enum EstadoPago{
        Pagado, No_Pagado
    }

    public Pago(String idPago, LocalDate fechaPago, MetodoPago metodo, EstadoPago estado) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.metodo = metodo;
        this.estado = estado;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    public MetodoPago getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoPago metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago='" + idPago + '\'' +
                ", fechaPago=" + fechaPago +
                ", metodo=" + metodo +
                ", estado=" + estado +
                '}';
    }
}
