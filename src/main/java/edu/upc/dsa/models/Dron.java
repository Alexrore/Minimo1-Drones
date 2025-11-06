package edu.upc.dsa.models;
import java.util.ArrayList;
import java.util.List;
public class Dron {
    String Id, nombre, fabricante, modelo;
    double horasvuelo;
    boolean mantenimiento;
    List<Reserva> reservas;

    public Dron(String id, String nombre, String fabricante, String modelo){
        this.Id = id;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.horasvuelo = 0;
        this.mantenimiento = false;
        this.reservas = new ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public double getHorasvuelo() {
        return horasvuelo;
    }
    public void setHorasVuelo(double horasvuelo) {
        this.horasvuelo = horasvuelo;
    }
    public boolean isMantenimiento() {
        return mantenimiento;
    }
    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    public void addReservas(Reserva reservas) {
        this.reservas.add(reservas);
    }
    public static Dron buscarDronPorId(List<Dron> drones, String id) {
        for (Dron dron : drones) {
            if (dron.getId().equals(id)) {
                return dron;
            }
        }
        return null;
    }
}