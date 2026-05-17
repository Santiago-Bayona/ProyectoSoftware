package co.edu.uniquindio.poo.demo.Back;

public class Cliente extends Persona{

    public Estado estado;


    public Cliente(String documento, String nombre, String telefonono, String email, String direccion, Estado estado) {
        super(documento, nombre, telefonono, email, direccion);
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "estado=" + estado +
                '}';
    }
}
