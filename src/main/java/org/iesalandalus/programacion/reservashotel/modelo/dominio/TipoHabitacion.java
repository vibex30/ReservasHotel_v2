package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {

    SUITE("Suite", 4),
    SIMPLE("Simple", 1),
    DOBLE("Doble", 2),
    TRIPLE("Triple", 3);

    private final String cadenaAMostrar;
    private final int numeroMaximoPersonas;


    // Constructor privado
    private TipoHabitacion(String cadenaAMostrar, int numeroMaximoPersonas) {
        this.cadenaAMostrar = cadenaAMostrar;
        this.numeroMaximoPersonas = numeroMaximoPersonas;
    }

    // M�todo p�blico para obtener el n�mero m�ximo de personas
    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }

    // M�todo p�blico para obtener una representaci�n de cadena
    @Override
    public String toString() {
        return 1+ordinal()+"-." + cadenaAMostrar;
    }



}
