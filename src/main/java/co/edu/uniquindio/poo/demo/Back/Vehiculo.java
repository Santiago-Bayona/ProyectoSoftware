package co.edu.uniquindio.poo.demo.Back;

public class Vehiculo {

    public String placa, marca, modelo, kilometraje;
    public Cliente duenio;
    public Tipo tipo;

    public enum Tipo{
        Automovil, Camion, Camioneta, Moto
    }

    public Vehiculo(String placa, String marca, String modelo, String kilometraje, Cliente duenio, Tipo tipo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.duenio = duenio;
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Cliente getDuenio() {
        return duenio;
    }

    public void setDuenio(Cliente duenio) {
        this.duenio = duenio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", kilometraje='" + kilometraje + '\'' +
                ", duenio=" + duenio +
                ", tipo=" + tipo +
                '}';
    }
}
