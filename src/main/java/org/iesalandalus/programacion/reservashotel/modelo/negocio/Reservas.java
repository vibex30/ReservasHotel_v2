package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Reservas {
    private int capacidad;
    private int tamano;
    private List<Reserva> coleccionReservas;

    // Constructor
    public Reservas(int capacidad) {
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionReservas = new ArrayList<>(capacidad);
    }

    // Método para obtener la lista de reservas
    public List<Reserva> get() {
        return copiaProfundaReservas();
    }

    // Método para realizar una copia profunda de la lista de reservas
    private List<Reserva> copiaProfundaReservas() {
        List<Reserva> copiaProfunda = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            copiaProfunda.add(new Reserva(reserva));
        }

        return copiaProfunda;
    }

    // Método para obtener el tamaño actual de la lista
    public int getTamano() {
        return tamano;
    }



    // Método para insertar una reserva
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if(reserva==null)
            throw new NullPointerException("Error, la reserva no puede ser nula");

        if(coleccionReservas.contains(reserva)){
            throw new OperationNotSupportedException("Error, la reserva ya está hecha");
        }
        //TODO NO ESTOY SEGURA DE QUE AQUI TENGA SENTIDO EL TAMAÑO
        if(coleccionReservas.size()>=tamano){
            throw new IllegalArgumentException("Error se ha superador el tamaño permitido");
        }
        coleccionReservas.add(new Reserva(reserva));
    }


    // Método para buscar una reserva
    public Reserva buscar(Reserva reserva) {
        if(reserva==null)
            throw new NullPointerException("no se puede borrar una reserva nula ");

        for(Reserva i:coleccionReservas){
            if(i.equals(reserva)){
                return  i;
            }
        }
        return null;
    }

    // Método para borrar una reserva
    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva a buscar no puede ser nula.");
        }
        if (buscar(reserva) == null){
            throw new OperationNotSupportedException("ERROR: La reserva que se quiere borrar no existe.");
        }
        coleccionReservas.remove(reserva);

    }



//Devolverá una colección de reservas realizadas por el huésped pasado por parámetro
//TODO REVISAR
    public List<Reserva> getReservas(Huesped huesped) {
        List<Reserva> DameLaReservaHuesped=new ArrayList<>();

        for(Reserva reserva:coleccionReservas){
            if(reserva.equals(huesped)){
                return coleccionReservas;
            }

        }
        return null;


    }



//Devolverá una colección de reservas realizadas para el tipo de habitación indicada en el parámetro
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        List <Reserva> reservaTipoHab= new ArrayList<>();

        for(Reserva reserva:coleccionReservas){
            if(reserva.equals(tipoHabitacion)){
                return coleccionReservas;
            }
        }
        return null;
    }

    //Devolverá una colección de las reservas realizadas para la habitación indicada como parámetro y que sean
    //posteriores a la fecha de hoy

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        List <Reserva> reservasFuturasHabitacion= new ArrayList<>();

        for (Reserva reserva:coleccionReservas) {
            // Verifica si la reserva pertenece a la habitación específica y si la fecha de inicio es posterior a la fecha actual
            if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(LocalDate.now())){
                reservasFuturasHabitacion.add(reserva);

            }
        }
        return reservasFuturasHabitacion;
    }

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha){
        if(reserva==null)
            throw new NullPointerException("Reserva nula");
        if(fecha==null)
            throw new NullPointerException("Fecha nula");
        if(fecha.isBefore(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isAfter(reserva.getFechaFinReserva().atStartOfDay()))
            throw new IllegalArgumentException("La fecha de inicio  de reserva no puede ser posterior al check in");


        reserva.setCheckIn(fecha);


    }

    public void realizarCheckOut (Reserva reserva, LocalDateTime fecha){
        if(reserva==null)
            throw new NullPointerException("check out nulo");
        if(fecha==null)
            throw new NullPointerException("Fecha nula111111");
        if(fecha.isAfter(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isBefore(reserva.getFechaFinReserva().atStartOfDay()))
            throw new IllegalArgumentException("La fecha de inicio  de reserva no puede ser posterior al check in");

        reserva.setCheckOut(fecha);
    }





}



