package co.edu.uniquindio.poo.demo.Back;

public class Mecanico extends Persona {

    public String espcialidad, contrasenia;
    public Estado estado;

    public Mecanico(String documento, String nombre, String telefonono, String email, String direccion, String espcialidad, Estado estado) {
        super(documento, nombre, telefonono, email, direccion);
        this.espcialidad = espcialidad;
        this.estado = estado;
    }

    public String getEspcialidad() {
        return espcialidad;
    }

    public void setEspcialidad(String espcialidad) {
        this.espcialidad = espcialidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    @Override
    public String toString() {
        return "Mecanico{" +
                "espcialidad='" + espcialidad + '\'' +
                ", estado=" + estado +
                '}';
    }
}
