package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.lang.model.util.AbstractElementVisitor7;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Huespedes {

    private List<Huesped> coleccionHuespedes;



// Constructor


    public Huespedes() {

        this.coleccionHuespedes = new ArrayList<>();
    }


    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }



//Método para realizar una copia profunda de los Huéspedes
    private List<Huesped> copiaProfundaHuespedes() {
        List<Huesped> copiaProfundaHuesped= new ArrayList<>();
        for(Huesped huesped:coleccionHuespedes){
            copiaProfundaHuesped.add(new Huesped(huesped));
        }
        return copiaProfundaHuesped;
    }


    public int getTamano() {
            return this.coleccionHuespedes.size();
        }



//Método para insertar un huésped

    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        if(huesped==null) {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }
        if(coleccionHuespedes.contains(huesped)){
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }
        coleccionHuespedes.add(new Huesped(huesped));


    }

//Método para buscar un huésped
    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("Error, el huesped no puede ser nulo.");
        }

        for(Huesped huesped1:coleccionHuespedes){
            if(coleccionHuespedes.contains(huesped)){
                return huesped1;
            }
        }
        return null;
    }

//Método para borrar un Huésped
    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped==null) {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }

        if(!coleccionHuespedes.contains(huesped)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        }

        coleccionHuespedes.remove(huesped);

    }




}


