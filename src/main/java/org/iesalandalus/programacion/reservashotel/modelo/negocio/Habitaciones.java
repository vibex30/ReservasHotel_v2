package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {
    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    // Constructor
    public Habitaciones(int capacidad) {
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionHabitaciones = new Habitacion[capacidad];
    }


    public Habitacion[] get(TipoHabitacion tipoHabitacion) {
        if (coleccionHabitaciones == null)
            throw new NullPointerException("Error, la lista no puede ser nula");
        Habitacion[] copia = new Habitacion[this.capacidad];


        int puntero = 0;

        for (Habitacion elemento : coleccionHabitaciones)
            if (elemento.getTipoHabitacion() == tipoHabitacion) {
                copia[puntero] = new Habitacion(elemento);
                puntero++;

            }

        return copia;


    }




    // Método para obtener la lista de habitaciones
    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    // Método para realizar una copia profunda de la lista de habitaciones
    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copia = new Habitacion[capacidad];
        for (int i = 0; i < tamano; i++) {
            copia[i] = coleccionHabitaciones[i];
        }
        return copia;
    }

    // Método para obtener el tamaño actual de la lista
    public int getTamano() {
        return tamano;
    }

    // Método para obtener la capacidad de la lista
    public int getCapacidad() {
        return capacidad;
    }

    // Método para insertar una habitacion
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException{
        if(habitacion==null)
            throw new NullPointerException("Error, la habitacion no puede ser nula");


        int indice= buscarIndice(habitacion);


        if (tamanoSuperado(tamano)) {
            throw new IllegalStateException("ERROR: Se ha superado el tamaño permitido.");
        } else if (indice>=0)
            throw new OperationNotSupportedException("Error, la habitacion ya esta registrada");
        else
            coleccionHabitaciones[tamano] = new Habitacion(habitacion);
            tamano++;

    }

    // Método para buscar el índice de una habitacion
    private int buscarIndice(Habitacion habitacion) {
        if(habitacion==null)
            throw new NullPointerException("Error, introduce un valor válido");
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
                return i;
            }
        }
        return -1;
    }

    // Método para verificar si el tamaño ha sido superado
    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }
    private boolean capacidadSuperada(int indice){
        return indice>=capacidad;
    }

    // Método para buscar una habitacion
    public Habitacion buscar(Habitacion habitacion) {
        if(habitacion==null)
            throw new NullPointerException("Error, introduce un valor correcto");
        int indice = buscarIndice(habitacion);
        return (indice != -1) ? coleccionHabitaciones[indice] : null;
    }

    // Método para borrar una habitacion
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if(habitacion==null)
            throw new NullPointerException("Error, no puede ser nulo");
        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;

        }else
            throw new OperationNotSupportedException("la habitacion a borrar no existe");

        if(buscarIndice(habitacion)!=-1){
            throw new OperationNotSupportedException("Error, notSupported");
        }


    }

    // Método para desplazar una posición hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];
        }
        coleccionHabitaciones[tamano-1]=null;
    }

}
