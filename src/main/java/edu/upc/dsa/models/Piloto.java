package edu.upc.dsa.models;

import java.util.List;
import java.util.ArrayList;

public class Piloto {
    private String id, nombre, apellidos;
    double horasDeVuelo;
    List<Reserva> reservas;

    public Piloto(String id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.horasDeVuelo = 0;
        this.reservas = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public double getHorasDeVuelo() {
        return horasDeVuelo;
    }
    public void setHorasVuelo(double horasDeVuelo) {
        this.horasDeVuelo = horasDeVuelo;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }
    public static Piloto buscarPilotoPorId(List<Piloto> pilotos, String id) {
        for (Piloto piloto : pilotos) {
            if (piloto.getId().equals(id)) {
                return piloto;
            }
        }
        return null;
    }
}
