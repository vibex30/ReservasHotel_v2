package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Arrays;


public class Reservas {
    private int capacidad;
    private int tamano;
    private Reserva[] coleccionReservas;

    // Constructor
    public Reservas(int capacidad) {
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionReservas = new Reserva[capacidad];
    }

    // M�todo para obtener la lista de reservas
    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    // M�todo para realizar una copia profunda de la lista de reservas
    private Reserva[] copiaProfundaReservas() {
        Reserva[] copia = new Reserva[capacidad];
        for (int i = 0; i < tamano; i++) {
            copia[i] = coleccionReservas[i];
        }
        return copia;
    }

    // M�todo para obtener el tama�o actual de la lista
    public int getTamano() {
        return tamano;
    }

    // M�todo para obtener la capacidad de la lista
    public int getCapacidad() {
        return capacidad;
    }

    // M�todo para insertar una reserva
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if(reserva==null)
            throw new NullPointerException("Error, la reserva no puede ser nula");


        int indice= buscarIndice(reserva);


        if (tamanoSuperado(tamano)) {
            throw new IllegalStateException("ERROR: Se ha superado el tama�o permitido.");
        } else if (indice>=0)
            throw new OperationNotSupportedException("Error, la reserva ya esta registrada");
        else
            coleccionReservas[tamano] = new Reserva(reserva);
        tamano++;
    }

    // M�todo para buscar el �ndice de una reserva
    private int buscarIndice(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("ERROR: El �ndice de la  reserva no puede ser nulo.");
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                return i;
            }
        }
        return -1;
    }

    // M�todo para verificar si el tama�o ha sido superado
    private boolean tamanoSuperado(int indice) {
        return indice >= capacidad;
    }
    private boolean capacidadSuperada(int indice) {
        if (indice > capacidad)
            return true;
        return false;
    }
    // M�todo para buscar una reserva
    public Reserva buscar(Reserva reserva) {
        if(reserva==null)
            throw new NullPointerException("no se puede borrar una reserva nula ");

        int busqueda = buscarIndice(reserva);
        if (busqueda == -1)
            return null;
        return coleccionReservas[busqueda];
    }

    // M�todo para borrar una reserva
    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        if (reserva == null)
            throw new NullPointerException("ERROR: La reserva a buscar no puede ser nula.");
        if (buscar(reserva) == null)
            throw new IllegalArgumentException("ERROR: La reserva que se quiere borrar no existe.");
        int indice = buscarIndice(reserva);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    // M�todo para desplazar una posici�n hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionReservas[i] = coleccionReservas[i + 1];
        }
        coleccionReservas[tamano-1]=null;
    }

    public Reserva[] getReservas(Huesped huesped) {
        Reserva[] reservasHuesped = new Reserva[this.tamano];
        int j = 0;
        for (int i = 0; i < this.tamano; i++) {
            if (this.coleccionReservas[i].getHuesped().equals(huesped)) {
                reservasHuesped[j] = this.coleccionReservas[i];
                j++;
            }
        }
        return reservasHuesped;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        Reserva[] reservasTipoHabitacion = new Reserva[this.tamano];
        int j = 0;
        for (int i = 0; i < this.tamano; i++) {
            if (this.coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion[j] = this.coleccionReservas[i];
                j++;
            }
        }
        return reservasTipoHabitacion;

    }


    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        Reserva[] reservasFuturasHabitacion = new Reserva[this.tamano];
        int j = 0;
        for (int i = 0; i < this.tamano; i++) {
            //verifico si la reserva pertenece a la habitaci�n espec�ficada y si la fecha de inicio de la reserva es posterior a la fecha actual
            if (this.coleccionReservas[i].getHabitacion().equals(habitacion) && this.coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturasHabitacion[j] = this.coleccionReservas[i];
                j++;
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



