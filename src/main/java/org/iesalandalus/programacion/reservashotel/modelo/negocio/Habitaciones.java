package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Habitaciones {

    private List<Habitacion> coleccionHabitaciones;

    // Constructor
    public Habitaciones() {

        this.coleccionHabitaciones = new ArrayList<>();
    }



    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        if (coleccionHabitaciones == null)
            throw new NullPointerException("Error, la lista no puede ser nula");

        List<Habitacion> lista= new ArrayList<>();

        Iterator<Habitacion> iterator=coleccionHabitaciones.iterator();

        while(iterator.hasNext()){
            Habitacion habitacion= iterator.next();
            lista.add(new Habitacion(habitacion));

        }
        /*
        for (Habitacion habitacion : coleccionHabitaciones)
            if (habitacion.getTipoHabitacion() == tipoHabitacion) {
                lista.add(new Habitacion(habitacion));

            }*/
        return lista;
    }


    // M�todo para obtener la lista de habitaciones
    public List<Habitacion> get() {
        return new ArrayList<>(coleccionHabitaciones);
    }

    // M�todo para realizar una copia profunda de la lista de habitaciones
    private List<Habitacion> copiaProfundaHabitaciones() {
        List<Habitacion> copiaProfunda = new ArrayList<>();

        Iterator<Habitacion> iterador= coleccionHabitaciones.iterator();

        while (iterador.hasNext()) {
            Habitacion habitacion = iterador.next();
            copiaProfunda.add(new Habitacion(habitacion));
        }
        /*
        for(Habitacion habitacion:coleccionHabitaciones){
            copiaProfunda.add(new Habitacion(habitacion));

        }*/
        return copiaProfunda;
    }


    // M�todo para insertar una habitacion
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException{
        if(habitacion==null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");
        }

        if(coleccionHabitaciones.contains(habitacion)){
            throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
        }
        coleccionHabitaciones.add(new Habitacion(habitacion));
    }



    // M�todo para buscar una habitacion
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede buscar una habitaci�n nula.");

        Iterator<Habitacion> iterator= coleccionHabitaciones.iterator();
        while (iterator.hasNext()){
            Habitacion i=iterator.next();
            if(i.equals(habitacion)){
                return i;
            }
        }

        /*for(Habitacion i:coleccionHabitaciones){
            if(i.equals(habitacion)){
                return i;
            }
        }*/

        return null;


    }

    // M�todo para borrar una habitacion
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if(habitacion==null)
            throw new NullPointerException("ERROR: No se puede borrar una habitaci�n nula.");

        if(!coleccionHabitaciones.contains(habitacion)){
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitaci�n como la indicada.");
        }

        coleccionHabitaciones.remove(habitacion);


    }
    public int getTamano() {
        return this.coleccionHabitaciones.size();
    }




}
