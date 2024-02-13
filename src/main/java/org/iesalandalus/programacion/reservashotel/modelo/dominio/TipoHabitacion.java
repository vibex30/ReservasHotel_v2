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

    // Método público para obtener el número máximo de personas
    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }

    // Método público para obtener una representación de cadena
    @Override
    public String toString() {
        return 1+ordinal()+"-." + cadenaAMostrar;
    }



}
