package edu.upc.dsa.util;
import edu.upc.dsa.models.*;
import java.util.*;
import org.apache.log4j.Logger;
public class ManagerImpl implements Manager{
    private static Manager instance;
    protected List<Dron> drones;
    protected List<Reserva> reservas;
    protected List<Piloto> pilotos;
    protected Queue<Dron> colaMantenimiento;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    public ManagerImpl() {
        this.drones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.pilotos = new ArrayList<>();
        this.colaMantenimiento = new LinkedList<>();
    }
    public static Manager getInstance() {
        if (instance == null) {
            instance = new ManagerImpl();
        }
        return instance;
    }

    public int size(){
        int q =this.drones.size();
        logger.info("Drones: "+q);
        return q;
    }

    @Override
    public Dron añadirDron(String id, String nombre, String fabricante, String modelo){
        Dron dr = new Dron(id,nombre,fabricante,modelo);
        this.drones.add(dr);
        logger.info("Dron añadido");
        return dr;
    }

    @Override
    public Piloto añadirPiloto(String id, String nombre, String apellidos){
        Piloto pi = new Piloto(id,nombre,apellidos);
        this.pilotos.add(pi);
        logger.info("Piloto añadido");
        return pi;
    }

    @Override
    public List<Dron> dornesPorHorasDeVueloDesc(){
        List<Dron> dronesPorHorasDeVuelo = new ArrayList<>(this.drones);
        dronesPorHorasDeVuelo.sort(Comparator.comparing(Dron::getHorasvuelo).reversed());
        logger.info("Lista de drones ordenada por horas de vuelo (Descendiente): "+dronesPorHorasDeVuelo);
        return dronesPorHorasDeVuelo;
    }

    @Override
    public List<Piloto> pilotosPorHorasDeVueloDesc(){
        List<Piloto> PilotosPorHorasDeVuelo = new ArrayList<>(this.pilotos);
        PilotosPorHorasDeVuelo.sort(Comparator.comparing(Piloto::getHorasDeVuelo).reversed());
        logger.info("Lista de pilotos ordenada por horas de vuelo (Descendiente): " + PilotosPorHorasDeVuelo);
        return PilotosPorHorasDeVuelo;
    }

    @Override
    public void guardarDronAlmacen(String id){
        for(Dron dr : this.drones){
            if(dr.getId().equals(id)){
                dr.setMantenimiento(true);
                this.colaMantenimiento.add(dr);
                logger.info("Nuevo dron en mantenimiento");
                break;
            }
        }
    }

    @Override
    public Dron repararDron(){
        if(!this.colaMantenimiento.isEmpty()){
            Dron dron  = this.colaMantenimiento.poll();
            dron.setMantenimiento(false);
            logger.info("Dron reparado.");
            return dron;
        }
        return null;
    }

    @Override
    public void añadirResrva(String dronId, Date fecha,int tiempoReserva, String posicionInicio, String posicionFin, String pilotoID){
        Dron dron = null;
        Piloto piloto = null;
        for(Dron dr : this.drones){
            if(dr.getId().equals(dronId)){
                dron = dr;
            }
        }
        for(Piloto pi : this.pilotos){
            if(pi.getId().equals(pilotoID)){
                piloto = pi;
            }
        }
        if(piloto == null || dron == null){
            logger.info("Piloto o dron no existente");
        }
        if(dron.isMantenimiento()){
            logger.info("Dron en mantenimiento");
        }
        Date Fecha = new Date(fecha.getTime() + tiempoReserva*3600*1000);
        for (Reserva reserva : this.reservas) {
            if(reserva.getDronId().equals(dron.getId())||reserva.getPilotoId().equals(piloto.getId())){
                Date rDuracion = new Date(reserva.getFecha().getTime() + reserva.getTiempoReserva()*3600*1000);
                if((fecha.before(rDuracion)&& Fecha.after(reserva.getFecha()))){
                    logger.info("Ya hay una reserca asignada para esa fecha");
                }
            }
        }
        Reserva newReserva = new Reserva(dronId, fecha, tiempoReserva, posicionInicio, posicionFin, piloto.getId());
        this.reservas.add(newReserva);
        dron.addReservas(newReserva);
        piloto.addReserva(newReserva);
        logger.info("Reserva añadida");

    }
    @Override
    public List<Reserva> reservasAsignadasPiloto(String pilotoId){
        Piloto piloto = null;
        for(Piloto pi : this.pilotos){
            if(pi.getId().equals(pilotoId)){
                piloto = pi;
            }
        }
        List<Reserva> reservas = new ArrayList<>(piloto.getReservas());
        logger.info("Reservas asignadas piloto: "+ reservas);
        return reservas;
    }
    @Override
    public List<Reserva> reservasAsignadasDron(String dronId){
        Dron dron = null;
        for(Dron dr : this.drones){
            if(dr.getId().equals(dronId)){
                dron = dr;
            }
        }
        List<Reserva> reservas = new ArrayList<>(dron.getReservas());
        logger.info("Reservas asignadas dron: "+ reservas);
        return reservas;
    }
    public void clear() {
        this.drones.clear();
        this.pilotos.clear();
        this.reservas.clear();
        this.colaMantenimiento.clear();
    }
    // ManagerImpl.java (implementación)
    @Override public List<Dron> getDrones()   { return new ArrayList<>(this.drones); }
    @Override public List<Piloto> getPilotos(){ return new ArrayList<>(this.pilotos); }
    @Override public List<Reserva> getReservas(){ return new ArrayList<>(this.reservas); }

    @Override public Dron getDron(String id) {
        for (Dron d : this.drones) if (d.getId().equals(id)) return d;
        return null;
    }
    @Override public Piloto getPiloto(String id) {
        for (Piloto p : this.pilotos) if (p.getId().equals(id)) return p;
        return null;
    }

}