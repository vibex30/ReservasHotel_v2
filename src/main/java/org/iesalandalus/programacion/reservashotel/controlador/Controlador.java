package org.iesalandalus.programacion.reservashotel.controlador;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista){
        if (modelo==null || vista==null ){
            throw new NullPointerException("Modelo o vista es nulo");

        }

        this.modelo=modelo;
        this.vista=vista;
        vista.setControlador(this);

    }

    public void comenzar(){
        vista.comenzar();
        modelo.comenzar();
    }





    public void terminar(){
        vista.terminar();
        modelo.terminar();
    }


    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        modelo.insertar(huesped);

    }



    public Huesped buscar(Huesped huesped){
        return new Huesped(modelo.buscar(huesped));
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        modelo.borrar(huesped);
    }


    public List<Huesped> getHuespedes(){
        return modelo.getHuespedes();
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException{
        modelo.insertar(habitacion);
    }


    public Habitacion buscar(Habitacion habitacion){
        return modelo.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException{
        modelo.borrar(habitacion);

    }

    public List<Habitacion> getHabitaciones (){
        return modelo.getHabitaciones();
    }


    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion){
        return modelo.getHabitaciones();
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException{
        modelo.insertar(reserva);
    }


    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        modelo.borrar(reserva);
    }

    public Reserva buscar(Reserva reserva){
        return modelo.buscar(reserva);
    }

    public List<Reserva> getReservas(){
        return modelo.getReserva();
    }


    public List<Reserva> getReservas(Huesped huesped){
        return modelo.getReserva(huesped);
    }


    public List <Reserva> getReservas(TipoHabitacion tipoHabitacion){
        return modelo.getReserva(tipoHabitacion);
    }

    public List<Reserva> getReservas(Habitacion habitacion){
        return modelo.getReserva(habitacion);
    }

    public void realizarCheckIn (Reserva reserva, LocalDateTime fecha){
        modelo.realizarCheckIn(reserva, fecha);
    }

    public void realizarCheckOut (Reserva reserva, LocalDateTime fecha){
        modelo.realizarCheckOut(reserva, fecha);
    }







}
