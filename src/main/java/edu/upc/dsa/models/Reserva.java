package edu.upc.dsa.models;

import java.util.*;

public class Reserva {
    String dronId, pilotoId;
    int tiempoReserva;
    Date fecha;
    String posicionInicio,  posicionFin;
    public Reserva() {

    }
    public Reserva(String dronId, Date fecha,int tiempoReserva, String posicionInicio, String posicionFin, String pilotoID) {
        this.dronId = dronId;
        this.fecha = fecha;
        this.tiempoReserva = tiempoReserva;
        this.posicionInicio = posicionInicio;
        this.posicionFin = posicionFin;
        this.pilotoId = pilotoID;
    }
    public String getDronId() {
        return dronId;
    }
    public void setDronId(String dronId) {
        this.dronId = dronId;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getTiempoReserva() {
        return tiempoReserva;
    }
    public void setTiempoReserva(int tiempoReserva) {
        this.tiempoReserva = tiempoReserva;
    }
    public String getPosicionInicio() {
        return posicionInicio;
    }
    public void setPosicionInicio(String posicionInicio) {
        this.posicionInicio = posicionInicio;
    }
    public String getPosicionFin() {
        return posicionFin;
    }
    public void setPosicionFin(String posicionFin) {
        this.posicionFin = posicionFin;
    }
    public String getPilotoId() {
        return pilotoId;
    }
    public void setPilotoId(String pilotoId) {
        this.pilotoId = pilotoId;
    }
}
