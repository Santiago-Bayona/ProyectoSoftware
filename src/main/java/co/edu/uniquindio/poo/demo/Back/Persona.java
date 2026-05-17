package co.edu.uniquindio.poo.demo.Back;

public class Persona {

    public String documento, nombre, telefonono, email, direccion;

    public Persona(String documento, String nombre, String telefonono, String email, String direccion) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefonono = telefonono;
        this.email = email;
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefonono() {
        return telefonono;
    }

    public void setTelefonono(String telefonono) {
        this.telefonono = telefonono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefonono='" + telefonono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
