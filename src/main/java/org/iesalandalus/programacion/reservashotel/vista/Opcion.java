package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {

    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar huesped"),
    BUSCAR_HUESPED("Buscar huesped"),
    BORRAR_HUESPED("Borrar huesped"),
    MOSTRAR_HUESPEDES("Mostrar huesped"),
    INSERTAR_HABITACION("Insertar habiatación"),
    BUSCAR_HABITACION("Buscar habitación"),
    BORRAR_HABITACION("Borrar habitación"),
    MOSTRAR_HABITACIONES("Mostrar habitaciones"),
    INSERTAR_RESERVA("Insertar reserva"),
    ANULAR_RESERVA("Anular reserva"),
    MOSTRAR_RESERVAS("Mostrar reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad"),
    REALIZAR_CHECKIN("Realizar check in"),
    REALIZAR_CHECKOUT("Realizar check out");




    private String mensajeAmostrar;

    private Opcion(String mensajeAmostrar){
        this.mensajeAmostrar=mensajeAmostrar;
    }

    @Override
    public String toString() {
        return
                1+ordinal()+".-" + mensajeAmostrar;
    }


}
