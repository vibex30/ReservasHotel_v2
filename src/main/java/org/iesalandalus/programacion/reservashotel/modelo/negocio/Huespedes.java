package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.lang.model.util.AbstractElementVisitor7;
import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Huespedes {

    private List<Huesped> coleccionHuespedes;


    public Huespedes() {

        this.coleccionHuespedes = new ArrayList<>();
    }

    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }



//M�todo para realizar una copia profunda de los Hu�spedes
    private List<Huesped> copiaProfundaHuespedes() {
        List<Huesped> copiaProfundaHuesped= new ArrayList<>();
        Iterator<Huesped> iterator= coleccionHuespedes.iterator();
        while (iterator.hasNext()){
            Huesped huesped=iterator.next();
            copiaProfundaHuesped.add(new Huesped(huesped));
        }

        return copiaProfundaHuesped;
    }


    public int getTamano() {
            return this.coleccionHuespedes.size();
        }



//M�todo para insertar un hu�sped

    public void insertar(Huesped huesped) throws OperationNotSupportedException{
        if(huesped==null) {
            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        }
        if(coleccionHuespedes.contains(huesped)){
            throw new OperationNotSupportedException("ERROR: Ya existe un hu�sped con ese dni.");
        }
        coleccionHuespedes.add(new Huesped(huesped));


    }

//M�todo para buscar un hu�sped
    public Huesped buscar(Huesped huesped) {

        if (huesped == null)
            throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");
        Huesped copia=coleccionHuespedes.get(coleccionHuespedes.indexOf(huesped));
        if (coleccionHuespedes.contains(huesped))

            return new Huesped(copia);
        else
            return null;


    }


//M�todo para borrar un Hu�sped
    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        if (huesped==null) {
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }

        if(!coleccionHuespedes.contains(huesped)) {
            throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped como el indicado.");
        }

        coleccionHuespedes.remove(huesped);

    }




}




