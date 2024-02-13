package org.iesalandalus.programacion.reservashotel.dominio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoHabitacionTest {

    private static final String NOMBRE_NO_VALIDO = "El nombre del tipo de habitaci�n no es v�lido.";
    private static final String NUMERO_MAXIMO_PERSONAS_NO_VALIDO = "El n�mero m�ximo de personas para el tipo de habitaci�n no es el correcto.";


    @Test
    void objectosValidosTipoHabitacion() {
        assertEquals("SUITE", TipoHabitacion.SUITE.name(), NOMBRE_NO_VALIDO);
        assertEquals("SIMPLE", TipoHabitacion.SIMPLE.name(), NOMBRE_NO_VALIDO);
        assertEquals("DOBLE", TipoHabitacion.DOBLE.name(), NOMBRE_NO_VALIDO);
        assertEquals("TRIPLE", TipoHabitacion.TRIPLE.name(), NOMBRE_NO_VALIDO);
    }

    @Test
    void numPersonasValidasTipoHabitacion() {
        assertEquals(4, TipoHabitacion.SUITE.getNumeroMaximoPersonas(), NUMERO_MAXIMO_PERSONAS_NO_VALIDO);
        assertEquals(1, TipoHabitacion.SIMPLE.getNumeroMaximoPersonas(), NUMERO_MAXIMO_PERSONAS_NO_VALIDO);
        assertEquals(2, TipoHabitacion.DOBLE.getNumeroMaximoPersonas(), NUMERO_MAXIMO_PERSONAS_NO_VALIDO);
        assertEquals(3, TipoHabitacion.TRIPLE.getNumeroMaximoPersonas(), NUMERO_MAXIMO_PERSONAS_NO_VALIDO);
    }

}
