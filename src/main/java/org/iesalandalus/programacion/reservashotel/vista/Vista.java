package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;


public class Vista {
    private static Controlador controlador;


    public static boolean salir = false;

    public void setControlador(Controlador controlador) {
        if (controlador == null)
            throw new NullPointerException("El controlador es nulo");

        this.controlador = controlador;
    }

    public void comenzar() {
        do {
            Consola.mostrarMenu();
            ejecutarOpcion(Consola.elegirOpcion());
        } while (!salir);
    }

    public void terminar() {
        System.out.println("Adiossss");
    }

    private static void ejecutarOpcion(Opcion opcion) {

        switch (opcion) {
            case SALIR:
                salir = true;
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                TipoHabitacion tipo = Consola.leerTipoHabitacion();
                System.out.println("Fecha inicio: ");
                LocalDate fechaIni = Consola.leerFecha(Entrada.cadena());

                System.out.println("Fecha fin: ");
                LocalDate fechaFin = Consola.leerFecha(Entrada.cadena());

                consultarDisponibilidad(Consola.leerTipoHabitacion(), fechaIni, fechaFin);
                break;
            case REALIZAR_CHECKIN:
                realizarCheckIn();

                break;
            case REALIZAR_CHECKOUT:
                realizarCheckOut();
                break;

        }
    }

    private static void realizarCheckIn() {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] li = controlador.getReserva(huesped);
        LocalDateTime fechaInic = Consola.leerFechaHora(Entrada.cadena()).atStartOfDay();

    }

    private static void realizarCheckOut() {
        Huesped huesped = Consola.getHuespedPorDni();
        Reserva[] reservas = controlador.getReserva(huesped);
        LocalDateTime fechaCheckOut = Consola.leerFechaHora(Entrada.cadena()).atStartOfDay();
        boolean reservaEncontrada = false;

        for (Reserva reserva : reservas) {
            if (reserva.getFechaInicioReserva().isBefore(fechaCheckOut.toLocalDate())) {
                controlador.realizarCheckOut(reserva, fechaCheckOut);
                reservaEncontrada = true;
                System.out.println("Checkout realizado con éxito");
            }
        }


    }

    private static void insertarHuesped() {

        try {
            controlador.insertar(Consola.leerHuesped());
            System.out.println("Ha insertado un huésped");

        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {

            System.out.println(e.getMessage());

        }

    }


    private static void buscarHuesped() {

        try {
            controlador.buscar(Consola.leerHuesped());
            System.out.println("He buscado un huésped");

        } catch (NullPointerException | IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }

    }
//Es el controlador al que le tengo que pedir los datos!!


    private static void borrarHuesped() {

        try {
            controlador.borrar(Consola.leerHuesped());
            System.out.println("He borrado un huésped");

        } catch (OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHuespedes() {
        try {
            for (Huesped cliente : controlador.getHuespedes())
                System.out.println(cliente);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void insertarHabitacion() {
        try {
            controlador.insertar(Consola.leerHabitacion());
            System.out.println("Ha insertado una habitación");

        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {

            System.out.println(e.getMessage());

        }


    }

    private static void buscarHabitacion() {
        try {
            controlador.buscar(Consola.leerHabitacionPorIdentificador());
            System.out.println("He buscado una habitación");

        } catch (NullPointerException | IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
    }

    private static void borrarHabitacion() {
        try {
            controlador.borrar(Consola.leerHabitacionPorIdentificador());
            System.out.println("He borrado una  habitación");

        } catch (OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }
    }

    //TODO ME HE QUEDADO AQUI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static void mostrarHabitaciones() {
        try {
            for (Habitacion cliente : controlador.getHabitaciones())
                System.out.println();
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertarReserva() {
        Reserva reservaQueQuiero = Consola.leerReserva();


        if (consultarDisponibilidad(reservaQueQuiero.getHabitacion().getTipoHabitacion(), reservaQueQuiero.getFechaInicioReserva(), reservaQueQuiero.getFechaFinReserva()) == null) {
            System.out.println("Ho hay habitacion");
        } else {


            try {
                controlador.insertar(reservaQueQuiero);
                System.out.println("Ha insertado una reserva");

            } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {

                System.out.println(e.getMessage());

            }
        }

    }

    private void listarReservas(Huesped huesped) {
        try {
            for (Reserva reserva : controlador.getReserva(huesped))
                System.out.println(reserva);

        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private void listaReserva(TipoHabitacion tipoHabitacion) {
        try {
            for (Reserva reserva : controlador.getReserva(tipoHabitacion))
                System.out.println(reserva);

        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }

    private Reserva[] getReservasAnulables(Reserva[] reservasAAnular) {
        return null;
    }


    private static void anularReserva() {
        try {
            controlador.borrar(Consola.leerReserva());
            System.out.println("He anulado una reserva");

        } catch (OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }


    private static void mostrarReservas() {

        try {
            for (Reserva reserva1 : controlador.getReserva())
                System.out.println(reserva1);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
        boolean tipoHabitacionEncontrada = false;
        Habitacion habitacionDisponible = null;
        int numElementos = 0;

        Habitacion[] habitacionesTipoSolicitado = controlador.getHabitaciones(tipoHabitacion);

        if (habitacionesTipoSolicitado == null)
            return habitacionDisponible;

        for (int i = 0; i < habitacionesTipoSolicitado.length && !tipoHabitacionEncontrada; i++) {

            if (habitacionesTipoSolicitado[i] != null) {
                Reserva[] reservasFuturas = controlador.getReserva(habitacionesTipoSolicitado[i]);
                numElementos = getNumElementosNoNulos(reservasFuturas);

                if (numElementos == 0) {
                    //Si la primera de las habitaciones encontradas del tipo solicitado no tiene reservas en el futuro,
                    // quiere decir que está disponible.
                    habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                    tipoHabitacionEncontrada = true;
                } else {

                    //Ordenamos de mayor a menor las reservas futuras encontradas por fecha de fin de la reserva.
                    // Si la fecha de inicio de la reserva es posterior a la mayor de las fechas de fin de las reservas
                    // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                    Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                    mostrar(reservasFuturas);*/

                    if (fechaInicioReserva.isAfter(reservasFuturas[0].getFechaFinReserva())) {
                        habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                        tipoHabitacionEncontrada = true;
                    }

                    if (!tipoHabitacionEncontrada) {
                        //Ordenamos de menor a mayor las reservas futuras encontradas por fecha de inicio de la reserva.
                        // Si la fecha de fin de la reserva es anterior a la menor de las fechas de inicio de las reservas
                        // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                        Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaInicioReserva));

                        /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                        mostrar(reservasFuturas);*/

                        if (fechaFinReserva.isBefore(reservasFuturas[0].getFechaInicioReserva())) {
                            habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                            tipoHabitacionEncontrada = true;
                        }
                    }

                    //Recorremos el array de reservas futuras para ver si las fechas solicitadas están algún hueco existente entre las fechas reservadas
                    if (!tipoHabitacionEncontrada) {
                        for (int j = 1; j < reservasFuturas.length && !tipoHabitacionEncontrada; j++) {
                            if (reservasFuturas[j] != null && reservasFuturas[j - 1] != null) {
                                if (fechaInicioReserva.isAfter(reservasFuturas[j - 1].getFechaFinReserva()) &&
                                        fechaFinReserva.isBefore(reservasFuturas[j].getFechaInicioReserva())) {

                                    habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                    tipoHabitacionEncontrada = true;
                                }
                            }
                        }
                    }


                }
            }
        }

        return habitacionDisponible;

    }

    private static int getNumElementosNoNulos(Reserva[] reservas) {
        int numero = 0;
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] != null)
                numero++;
        }
        return numero;
    }

    ///////////////////////////7
    public static final int CAPACIDAD=1;
    private static Habitaciones habitaciones;
    private static Reservas reservas;
    private static Huespedes huespedes;
















}
