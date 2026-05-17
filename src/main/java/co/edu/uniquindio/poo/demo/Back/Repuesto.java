package co.edu.uniquindio.poo.demo.Back;

public class Repuesto {

    public String nombre, idRepuesto, marca;
    public double precio;
    public int stock;

    public Repuesto(String nombre, String idRepuesto, String marca, double precio, int stock) {
        this.nombre = nombre;
        this.idRepuesto = idRepuesto;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(String idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void consultarDisponibilidad() {
        System.out.println("La cantidad  disponible es: " + stock);
    }

    @Override
    public String toString() {
        return "Repuesto{" +
                "nombre='" + nombre + '\'' +
                ", idRepuesto='" + idRepuesto + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
