package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;

public class Huespedes {

    private int capacidad;
    private int tamano;
    private Huesped[] coleccionHuespedes;

        // Constructor
    public Huespedes(int capacidad) {
        if (capacidad<=0)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionHuespedes = new Huesped[capacidad];
    }


    public Huesped[] get() {
        return copiaProfundaHuespedes();
    }


    private Huesped[] copiaProfundaHuespedes() {
        Huesped[] copiaProfunda = new Huesped[capacidad];
            for (int i = 0; i < tamano; i++) {
                copiaProfunda[i] = coleccionHuespedes[i];
            }
            return copiaProfunda;
    }


    public int getTamano() {
            return tamano;
        }


    public int getCapacidad() {
            return capacidad;
        }


    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped==null)
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        
        
        int indice= buscarIndice(huesped);
        
        if (tamanoSuperado(tamano))
            throw new OperationNotSupportedException("El array esta lleno");
        else if (indice>=0) {
            //Esto significa que el huesped esta ya en la coleccion
            // no se puede insertar duplicado
            throw new OperationNotSupportedException("El huesped ya esta presente");

        }else {
            //insertar en la posicion encontrada por buscarIndice
            coleccionHuespedes[tamano] = new Huesped(huesped);
            tamano++;

        }



    }


    private int buscarIndice(Huesped huesped) {
        if (huesped==null)
            throw new NullPointerException("No puede ser nulo ");
        for (int i = 0; i < tamano; i++) {
                if (coleccionHuespedes[i].equals(huesped)) {
                    return i;
                }
            }
            return -1;
    }


    private boolean tamanoSuperado(int indice){
        if (indice >= tamano)
            return true;

        return false;
    }
    private boolean capacidadSuperada(int indice) {
        if (indice >= capacidad)
            return true;

        return false;
    }

    public Huesped buscar(Huesped huesped) {
        if (huesped == null)
            throw new NullPointerException("Error, el huesped no puede ser nulo.1");

        int busqueda = buscarIndice(huesped);

        if (busqueda == -1)
            return null;

        return huesped;
    }


    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped==null)
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");

        int indice = buscarIndice(huesped);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
            
        } else{
            throw new OperationNotSupportedException("El huésped a borrar no existe");
            
        }
    }


        private void desplazarUnaPosicionHaciaIzquierda(int indice) {
            for (int i = indice; i < tamano - 1; i++) {
                coleccionHuespedes[i] = coleccionHuespedes[i + 1];
            }
            coleccionHuespedes[tamano-1]=null;
        }



}


