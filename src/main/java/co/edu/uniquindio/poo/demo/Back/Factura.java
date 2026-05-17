package co.edu.uniquindio.poo.demo.Back;

public class Factura {

    public String idFactura;
    public double total;
    public Servicio servicio;
    public Pago pago;

    public Factura(String idFactura, Pago pago, Servicio servicio) {
        this.idFactura = idFactura;
        this.total = 0.0;
        this.pago = pago;
        this.servicio = servicio;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public void CalcularTotal() {

        double impuesto1 = 0.1;
        double impuesto2 = 0.2;
        double totalA;
        double precioS = servicio.getPrecioRepuestos() + servicio.getPrecioManoObra();

        if ( precioS > 250000) {
            totalA = precioS + (precioS * impuesto1);
        } else {
            totalA = precioS + (precioS * impuesto2);
        }

        setTotal(totalA);

    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura='" + idFactura + '\'' +
                ", total=" + total +
                ", servicio=" + servicio +
                ", pago=" + pago +
                '}';
    }
}
