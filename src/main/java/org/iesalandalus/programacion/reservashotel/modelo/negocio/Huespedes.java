package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.lang.model.util.AbstractElementVisitor7;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Huespedes {

    private int capacidad;
    private int tamano;
    private List<Huesped> coleccionHuespedes;






// Constructor


    public Huespedes(int capacidad) {
        if (capacidad<=0)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        this.capacidad = capacidad;
        this.coleccionHuespedes = new ArrayList<>(capacidad);
    }


    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }



//M�todo para realizar una copia profunda de los Hu�spedes
    private List<Huesped> copiaProfundaHuespedes() {
        List<Huesped> copiaProfundaHuesped= new ArrayList<>();
        for(Huesped huesped:coleccionHuespedes){
            copiaProfundaHuesped.add(new Huesped(huesped));
        }
        return copiaProfundaHuesped;
    }


    public int getTamano() {
            return tamano;
        }



//M�todo para insertar un hu�sped

    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        if(huesped==null) {
            throw new NullPointerException("Error, el huesped no puede ser nulo");
        }
        if (coleccionHuespedes.size()>=capacidad) {
            throw new IllegalStateException("ERROR: Se ha superado el tama�o permitido.");
        }
        if(coleccionHuespedes.contains(huesped)){
            throw new OperationNotSupportedException("Error, el huesped ya est� registrado");
        }
        coleccionHuespedes.add(new Huesped(huesped));


    }

//M�todo para buscar un hu�sped
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

//M�todo para borrar un Hu�sped
    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped==null) {
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }

        if(!coleccionHuespedes.contains(huesped)) {
            throw new OperationNotSupportedException("Error, el hu�sped no se puede borrar porque no existe");
        }

        coleccionHuespedes.remove(huesped);

    }




}


