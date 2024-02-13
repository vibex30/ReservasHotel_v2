package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {
    private Consola() {

    }

    public static void mostrarMenu() {
        System.out.println("Menú:");
        for (Opcion opcionOp : Opcion.values()) {
            System.out.println(opcionOp);
        }
    }

    public static Opcion elegirOpcion() {
        int opcionElegida = 0;
        do {
            System.out.println("Elija una opción");
            opcionElegida = Entrada.entero();

        } while (opcionElegida < 1 || opcionElegida>=Opcion.values().length);
        return Opcion.values()[opcionElegida-1];
    }

    public static Huesped leerHuesped() {
        String nombre;
        String dni;
        String telefono;
        String email;

        do{
            System.out.println("Introduce un nombre");
            nombre =Entrada.cadena();
        }while (nombre.isBlank());


        do{
            System.out.println("Introduce un dni");
            dni= Entrada.cadena();
        }while (!dni.matches(Huesped.ER_DNI));

        do{
            System.out.println("Introduce el teléfono");
            telefono=Entrada.cadena();
        }while (!telefono.matches(Huesped.ER_TELEFONO));

        do{
            System.out.println("Introduce el correo");
            email= Entrada.cadena();
        }while(!email.matches(Huesped.ER_CORREO));

        System.out.println("Dame la fecha de nacimiento");
        LocalDate fechaNacimiento= leerFecha(Entrada.cadena());


        return new Huesped(nombre, dni, telefono, email, fechaNacimiento);

    }
    public static Huesped getHuespedPorDni() {
        System.out.println("Introduzca el dni:");
        String dni = Entrada.cadena();


        return new Huesped("Nombre Ficticio", dni, "correoficticio@hotmail.com", "666666666", LocalDate.of(1993, 10, 17));
    }

    public static LocalDate leerFecha(String mensaje){
        DateTimeFormatter formatoFecha= DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA);
        while(!mensaje.matches("[0-3][0-9]/[01][0-9]/[0-9]{4}")) {
            System.out.println("Introduzca fecha hora correcta");
            mensaje = Entrada.cadena();
        }

        LocalDate formateada= LocalDate.parse(mensaje, formatoFecha);

        return formateada;
    }

    public static LocalDate leerFechaHora(String mensaje){
        DateTimeFormatter formatoFechaHora= DateTimeFormatter.ofPattern(Reserva.FORMATO_FECHA_HORA_RESERVA);
        while(!mensaje.matches("[0-3][0-9]/[01][0-9]/[0-9]{4} [ ] [0-2][0-9]-[0-5][0-9]-[0-5][0-9]")){
            System.out.println("Introduzca fecha hora correcta");
            mensaje=Entrada.cadena();

        }
        LocalDate formateada= LocalDate.parse(mensaje, formatoFechaHora);

        return formateada;
    }

    public static Habitacion leerHabitacion(){

        System.out.println("Introduzca la planta de la habitación:");
        int planta=Entrada.entero();

        System.out.println("Introduzca la puerta de la habitación:");
        int puerta=Entrada.entero();

        System.out.println("Introduzca el tipo de habitación");
        TipoHabitacion tipoHabitacion= leerTipoHabitacion();

        System.out.println("Introduzca el precio de la habitación:");
        double precio=Entrada.realDoble();

        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador(){
        System.out.println("Introduzca la planta de la habitación:");
        int planta=Entrada.entero();

        System.out.println("Introduzca la puerta de la habitación:");
        int puerta=Entrada.entero();

        return new Habitacion(planta,puerta, 50.00, TipoHabitacion.SIMPLE);

    }

    public static TipoHabitacion leerTipoHabitacion(){
        for(TipoHabitacion loQueSea : TipoHabitacion.values())
            System.out.println(loQueSea);
        int opcionTipoHabitacion=0;

        do{
            System.out.println("Introduce el tipo de habitación");
            opcionTipoHabitacion=Entrada.entero();

        }while (opcionTipoHabitacion<0|| opcionTipoHabitacion>TipoHabitacion.values().length -1);

        return TipoHabitacion.values()[opcionTipoHabitacion];
    }

    public static Regimen leerRegimen(){
        for(Regimen tipoRegimen: Regimen.values())
            System.out.println(tipoRegimen);
        int opcionElegida=0;
        do{
            System.out.println("Introduce el régimen");
            opcionElegida=Entrada.entero();
        }while (opcionElegida<0 || opcionElegida>Regimen.values().length-1);
        return Regimen.values()[opcionElegida];

    }

    public static Reserva leerReserva(){
        Huesped huesped=getHuespedPorDni();
       Habitacion habitacion;
       habitacion=leerHabitacion();
       Regimen regimen= leerRegimen();
        System.out.println("Introduce una fecha de inicio (dd/MM/yyyy): ");
       LocalDate fechaIni=leerFecha(Entrada.cadena());
        System.out.println("Introduce una fecha de fin (dd/MM/yyyy): ");
       LocalDate fechaFin=leerFecha(Entrada.cadena());

       int numPer;

        System.out.println("Introduce numero de personas");
        numPer=Entrada.entero();

    return new Reserva(huesped, habitacion, regimen, fechaIni, fechaFin, numPer);
    }



}
