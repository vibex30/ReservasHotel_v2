package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Modelo {

    private static final int CAPACIDAD=1;
    private Habitaciones habitaciones;
    private Reservas reservas;
    private Huespedes huespedes;




    public Modelo(){
        habitaciones=new Habitaciones();
        reservas=new Reservas();
        huespedes=new Huespedes();
    }

    public void comenzar(){

    }

    public void terminar(){
        System.out.println("Adiositooooo");
    }


    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        huespedes.insertar(huesped);

    }



    public Huesped buscar(Huesped huesped){
         return new Huesped(huespedes.buscar(huesped));
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        huespedes.borrar(huesped);
    }


    public List<Huesped> getHuespedes(){
        return huespedes.get();
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException{
        habitaciones.insertar(habitacion);
    }


    public Habitacion buscar(Habitacion habitacion){
        return habitaciones.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException{
        habitaciones.borrar(habitacion);

    }

    public List<Habitacion> getHabitaciones (){
        return habitaciones.get();
    }


    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion){
        return habitaciones.get();
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException{
        reservas.insertar(reserva);
    }


    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        reservas.borrar(reserva);
    }

    public Reserva buscar(Reserva reserva){
        return reservas.buscar(reserva);
    }

    public List<Reserva> getReserva(){
        return reservas.get();
    }


    public List<Reserva> getReserva(Huesped huesped){
        return reservas.getReservas(huesped);
    }


    public List<Reserva> getReserva(TipoHabitacion tipoHabitacion){
        return reservas.getReservas(tipoHabitacion);
    }

    public List<Reserva> getReserva(Habitacion habitacion){
        return reservas.getReservasFuturas(habitacion);
    }

    public void realizarCheckIn (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckIn(reserva, fecha);
    }

    public void realizarCheckOut (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckOut(reserva, fecha);
    }




}
