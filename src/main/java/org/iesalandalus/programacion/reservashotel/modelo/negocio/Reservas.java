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

    // M�todo para obtener la lista de reservas
    public List<Reserva> get() {
        return copiaProfundaReservas();
    }

    // M�todo para realizar una copia profunda de la lista de reservas
    private List<Reserva> copiaProfundaReservas() {
        List<Reserva> copiaProfunda = new ArrayList<>();
        for (Reserva reserva : coleccionReservas) {
            copiaProfunda.add(new Reserva(reserva));
        }

        return copiaProfunda;
    }

    // M�todo para obtener el tama�o actual de la lista
    public int getTamano() {
        return tamano;
    }



    // M�todo para insertar una reserva
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if(reserva==null)
            throw new NullPointerException("Error, la reserva no puede ser nula");

        if(coleccionReservas.contains(reserva)){
            throw new OperationNotSupportedException("Error, la reserva ya est� hecha");
        }
        //TODO NO ESTOY SEGURA DE QUE AQUI TENGA SENTIDO EL TAMA�O
        if(coleccionReservas.size()>=tamano){
            throw new IllegalArgumentException("Error se ha superador el tama�o permitido");
        }
        coleccionReservas.add(new Reserva(reserva));
    }


    // M�todo para buscar una reserva
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

    // M�todo para borrar una reserva
    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva a buscar no puede ser nula.");
        }
        if (buscar(reserva) == null){
            throw new OperationNotSupportedException("ERROR: La reserva que se quiere borrar no existe.");
        }
        coleccionReservas.remove(reserva);

    }



//Devolver� una colecci�n de reservas realizadas por el hu�sped pasado por par�metro
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



//Devolver� una colecci�n de reservas realizadas para el tipo de habitaci�n indicada en el par�metro
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        List <Reserva> reservaTipoHab= new ArrayList<>();

        for(Reserva reserva:coleccionReservas){
            if(reserva.equals(tipoHabitacion)){
                return coleccionReservas;
            }
        }
        return null;
    }

    //Devolver� una colecci�n de las reservas realizadas para la habitaci�n indicada como par�metro y que sean
    //posteriores a la fecha de hoy

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        List <Reserva> reservasFuturasHabitacion= new ArrayList<>();

        for (Reserva reserva:coleccionReservas) {
            // Verifica si la reserva pertenece a la habitaci�n espec�fica y si la fecha de inicio es posterior a la fecha actual
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



