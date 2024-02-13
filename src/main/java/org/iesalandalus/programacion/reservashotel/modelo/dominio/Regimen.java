package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum Regimen {

    SOLO_ALOJAMIENTO ("S�lo Alojamiento", 0),
    ALOJAMIENTO_DESAYUNO ("Alojamiento y desayuno", 15),
    MEDIA_PENSION ("Media pensi�n", 30),
    PENSION_COMPLETA("Pensi�n completa", 50);
    private final String cadenaAMostrar;
    private final double incrementoPrecio;


    //Constructor privado


    private Regimen(String cadenaAMostrar, int incrementoPrecio){
        this.cadenaAMostrar=cadenaAMostrar;
        this.incrementoPrecio=incrementoPrecio;
    }

    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    @Override
    public String toString() {
        return 1+ordinal()+"-."+ cadenaAMostrar;
    }
}
