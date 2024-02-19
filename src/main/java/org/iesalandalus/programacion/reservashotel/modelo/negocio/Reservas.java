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
import java.util.Iterator;
import java.util.List;


public class Reservas {
    private List<Reserva> coleccionReservas;

    // Constructor
    public Reservas() {

        this.coleccionReservas = new ArrayList<>();
    }

    // M�todo para obtener la lista de reservas
    public List<Reserva> get() {
        return copiaProfundaReservas();
    }

    // M�todo para realizar una copia profunda de la lista de reservas
    private List<Reserva> copiaProfundaReservas() {
        List<Reserva> copiaProfunda = new ArrayList<>();

        Iterator<Reserva> iterator= coleccionReservas.iterator();

        while(iterator.hasNext()){
            Reserva reserva=iterator.next();
            copiaProfunda.add(new Reserva(reserva));

        }

        /*
        for (Reserva reserva : coleccionReservas) {
            copiaProfunda.add(new Reserva(reserva));
        }*/

        return copiaProfunda;
    }

    // M�todo para obtener el tama�o actual de la lista
    public int getTamano() {
        return coleccionReservas.size();
    }



    // M�todo para insertar una reserva
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if(reserva==null)
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");

        if(coleccionReservas.contains(reserva)){
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }

        coleccionReservas.add(new Reserva(reserva));
    }


    // M�todo para buscar una reserva
    public Reserva buscar(Reserva reserva) {
        if(reserva==null)
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");


        Iterator<Reserva> iterator=coleccionReservas.iterator();
        while(iterator.hasNext()){
            Reserva i=iterator.next();
            if(i.equals(reserva)){
                return  i;
            }
        }


        /*for(Reserva i:coleccionReservas){
            if(i.equals(reserva)){
                return  i;
            }
        }*/
        return null;
    }

    // M�todo para borrar una reserva
    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
        if (buscar(reserva) == null){
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
        coleccionReservas.remove(reserva);

    }



//Devolver� una colecci�n de reservas realizadas por el hu�sped pasado por par�metro

    public List<Reserva> getReservas(Huesped huesped) {
        List<Reserva> dameLaReservaHuesped=new ArrayList<>();

        Iterator<Reserva> iterator=coleccionReservas.iterator();

        while (iterator.hasNext()){
            Reserva reserva= iterator.next();
            if(reserva.getHuesped().equals(huesped))
                dameLaReservaHuesped.add(reserva);
        }

        /*for(Reserva reserva:coleccionReservas){
            if(reserva.getHuesped().equals(huesped)){
                DameLaReservaHuesped.add(reserva);
                return DameLaReservaHuesped;
            }

        }*/
        if(dameLaReservaHuesped.isEmpty()){
            return null;
        }else {
            return dameLaReservaHuesped;
        }

    }



//Devolver� una colecci�n de reservas realizadas para el tipo de habitaci�n indicada en el par�metro
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitaci�n nula.");
        List <Reserva> reservaTipoHab= new ArrayList<>();

        Iterator<Reserva> iterator= coleccionReservas.iterator();

        while(iterator.hasNext()) {
            Reserva reserva = iterator.next();
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservaTipoHab.add(reserva);
                return reservaTipoHab;
            }
            //He dejado los bucles for porque primero lo hice as� (no sab�a que habia que utilizar iteradores)
        /*
        for(Reserva reserva:coleccionReservas){
            if(reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)){
                reservaTipoHab.add(reserva);
                return reservaTipoHab;
            }
        }*/
        }
        return null;
    }

    //Devolver� una colecci�n de las reservas realizadas para la habitaci�n indicada como par�metro y que sean
    //posteriores a la fecha de hoy

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        List <Reserva> reservasFuturasHabitacion= new ArrayList<>();

        Iterator<Reserva> iterator=coleccionReservas.iterator();

        while(iterator.hasNext()){
            Reserva reserva=iterator.next();
            if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturasHabitacion.add(reserva);
                return reservasFuturasHabitacion;
            }
        }
        //nombreLista.sort(Comparator.comparing(ClaseQueComparas::m�todoConElQueLoHaces).reversed());
        //Collection.sort(ElQu�, Comparator.comparing(Clase::m�todo))
        /*for (Reserva reserva:coleccionReservas) {
            // Verifica si la reserva pertenece a la habitaci�n espec�fica y si la fecha de inicio es posterior a la fecha actual
            if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(LocalDate.now())){
                reservasFuturasHabitacion.add(reserva);
                return reservasFuturasHabitacion;

            }
        }*/
        return null;
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
            throw new NullPointerException("Fecha nula");
        if(fecha.isAfter(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isBefore(reserva.getFechaFinReserva().atStartOfDay()))
            throw new IllegalArgumentException("La fecha de inicio  de reserva no puede ser posterior al check in");
        if(reserva.getCheckIn()!=null)
            throw new NullPointerException("Error con el checkIn");
        reserva.setCheckOut(fecha);
    }





}



