package edu.upc.dsa.util;
import edu.upc.dsa.models.*;
import java.util.*;

public interface Manager {
    int size();
    Dron añadirDron(String id, String nombre, String fabricante, String modelo);
    Piloto añadirPiloto(String id, String nombre, String apellidos);
    List<Dron> dornesPorHorasDeVueloDesc();
    List<Piloto> pilotosPorHorasDeVueloDesc();
    void guardarDronAlmacen(String id);
    Dron repararDron();
    void añadirResrva(String dronId, Date fecha,int tiempoReserva, String posicionInicio, String posicionFin, String pilotoID);
    List<Reserva> reservasAsignadasPiloto(String pilotoId);
    List<Reserva> reservasAsignadasDron(String dronId);
    void clear();
    List<Dron> getDrones();
    List<Piloto> getPilotos();
    List<Reserva> getReservas();
    Dron getDron(String id);        // útil para /almacen
    Piloto getPiloto(String id);
}
