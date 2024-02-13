package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Habitaciones {
    private int capacidad;
    private int tamano;
    private List<Habitacion> coleccionHabitaciones;

    // Constructor
    public Habitaciones(int capacidad) {
        this.capacidad = capacidad;
        this.coleccionHabitaciones = new ArrayList<>(capacidad);
    }



    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        if (coleccionHabitaciones == null)
            throw new NullPointerException("Error, la lista no puede ser nula");

        List<Habitacion> lista= new ArrayList<>();

        for (Habitacion habitacion : coleccionHabitaciones)
            if (habitacion.getTipoHabitacion() == tipoHabitacion) {
                lista.add(new Habitacion(habitacion));

            }

        return lista;


    }


    // Método para obtener la lista de habitaciones
    public List<Habitacion> get() {
        return new ArrayList<>(coleccionHabitaciones);
    }

    // Método para realizar una copia profunda de la lista de habitaciones
    private List<Habitacion> copiaProfundaHabitaciones() {
        List<Habitacion> copiaProfunda = new ArrayList<>();
        for(Habitacion habitacion:coleccionHabitaciones){
            copiaProfunda.add(new Habitacion(habitacion));

        }
        return copiaProfunda;
    }


    // Método para insertar una habitacion
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException{
        if(habitacion==null) {
            throw new NullPointerException("Error, la habitacion no puede ser nula");
        }
        if (coleccionHabitaciones.size()>=capacidad) {
            throw new IllegalStateException("ERROR: Se ha superado el tamaño permitido.");
        }
        if(coleccionHabitaciones.contains(habitacion)){
            throw new OperationNotSupportedException("Error, esta habitación ya está registrada");
        }
        coleccionHabitaciones.add(new Habitacion(habitacion));
    }



    // Método para buscar una habitacion
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("Error, introduce un valor correcto");

        for(Habitacion i:coleccionHabitaciones){
            if(i.equals(habitacion)){
                return i;
            }
        }

        return null;


    }

    // Método para borrar una habitacion
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if(habitacion==null)
            throw new NullPointerException("Error, no puede ser nulo");

        if(!coleccionHabitaciones.contains(habitacion)){
            throw new OperationNotSupportedException("Error, la habitación no se puede borrar porque no existe");
        }

        coleccionHabitaciones.remove(habitacion);


    }
    public int getTamano() {
        return tamano;
    }




}
