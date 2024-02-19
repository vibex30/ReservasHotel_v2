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
import java.util.*;

import static org.iesalandalus.programacion.reservashotel.vista.Opcion.*;


public class Vista {
    private static Controlador controlador;

    public void setControlador(Controlador controlador) {
        if (controlador == null)
            throw new NullPointerException("El controlador es nulo");

        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcionSalir;
        do {
            Consola.mostrarMenu();
            ejecutarOpcion(opcionSalir=Consola.elegirOpcion());
        } while (opcionSalir != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Adiossss");
    }

    private static void ejecutarOpcion(Opcion opcion) {

        switch (opcion) {
            case SALIR:
                System.out.println("Hasta pronto!!");
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

    try {
        Huesped huesped = Consola.getHuespedPorDni();
        List<Reserva> listaRserva = controlador.getReservas(huesped);
        //Mostrar reservas disponibles
        Iterator<Reserva>iterator=listaRserva.iterator();
        int i=0;
        while(iterator.hasNext()){
            Reserva reserva=iterator.next();
            System.out.println((i+1) + ".-" + listaRserva.get(i));
        }

        //Bucle para que introduzca una reserva.
        int opcion;
        do {
            System.out.println("Introduce la opción que desee ");
            opcion = Entrada.entero();

        } while (opcion < 1 || opcion > listaRserva.size());

        Reserva reservaSeleccionada= listaRserva.get(opcion-1);

        System.out.println("Introduce fecha y hora de inicio de CheckIn (formato dd/MM/yyyy HH:mm)");
        LocalDateTime fechaInic = Consola.leerFechaHora(Entrada.cadena());

        controlador.realizarCheckIn(reservaSeleccionada, fechaInic);
        System.out.println("Reserva realizada con éxito");


    }catch (NullPointerException | IllegalArgumentException e){
        System.out.println(e.getMessage());
    }

    }

    private static void realizarCheckOut() {
       try {
           Huesped huesped = Consola.getHuespedPorDni();

           List<Reserva> reservas = controlador.getReservas(huesped);
           LocalDateTime fechaCheckOut = Consola.leerFechaHora(Entrada.cadena());
           boolean reservaEncontrada = false;

           Iterator<Reserva> iterator = reservas.iterator();
           while (iterator.hasNext()) {
               Reserva reserva = iterator.next();
               if (reserva.getFechaInicioReserva().isBefore(fechaCheckOut.toLocalDate())) {
                   controlador.realizarCheckOut(reserva, fechaCheckOut);
                   reservaEncontrada = true;
                   System.out.println("Checkout realizado con éxito");
               }
           }
           if (!reservaEncontrada) {
               System.out.println("No se encontraron reservas");

           }
       }catch (NullPointerException|IllegalArgumentException e){
           System.out.println(e.getMessage());
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

        //TODO ACABO DE PONER EL MéTODO PARA ORDENAR
        List<Huesped> listaAuxiliar=controlador.getHuespedes();
        Collections.sort(listaAuxiliar ,Comparator.comparing(Huesped::getNombre));

        Iterator<Huesped> nombre=listaAuxiliar.iterator();

        while (nombre.hasNext())
            System.out.println(nombre.next());
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

    /*Crea el método mostrarHabitaciones que mostrará todos las habitaciones almacenadas
    si es que hay o si no, nos informará de que no hay habitaciones.*/
    private static void mostrarHabitaciones() {
        try {
            List<Habitacion> habitaciones = controlador.getHabitaciones();
            if (habitaciones.isEmpty()) {
                System.out.println("No hay habitaciones para mostrar");
            } else {
                Iterator<Habitacion> iterator= habitaciones.iterator();
                while(iterator.hasNext()){
                    Habitacion habitacion=iterator.next();
                    System.out.println(habitacion);
                }
                /*for (Habitacion habitacion : habitaciones) {
                    System.out.println(habitacion);
                }*/
            }
            Collections.sort(habitaciones, Comparator.comparing(Habitacion::getPlanta).thenComparing(Habitacion::getPuerta));

            System.out.println("Habitaciones ordenadas:");



            for (Habitacion habitacion : habitaciones) {
                System.out.println(habitacion);
            }
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

            List<Reserva> listadoOrd= controlador.getReservas(huesped);

            Comparator<Habitacion> comparador=Comparator.comparing(Habitacion::getPlanta).thenComparing(Habitacion::getPuerta);
            listadoOrd.sort(Comparator.comparing(Reserva::getFechaInicioReserva).reversed().thenComparing(Reserva::getHabitacion,comparador));

            Iterator<Reserva> iterator= listadoOrd.iterator();
            while(iterator.hasNext()){
                Reserva reserva=iterator.next();
                System.out.println(reserva);
            }



        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }



    private void listaReserva(TipoHabitacion tipoHabitacion) {
        try {
            List<Reserva> reservas= controlador.getReservas(tipoHabitacion);
            Iterator<Reserva> iterator= reservas.iterator();
            while(iterator.hasNext()){
                Reserva reserva=iterator.next();
                System.out.println(reserva);
            }

            /*for (Reserva reserva : controlador.getReservas(tipoHabitacion))
                System.out.println(reserva);*/

        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }
/*Crea el método getReservasAnulables que dé la colección de reservas recibidas como parámetro,
devolverá aquellas que sean anulables, es decir, cuya fecha de inicio de la reserva aún no
haya llegado.*/
    private static List<Reserva> getReservasAnulables(List<Reserva> reservas) {
        List<Reserva> reservasAnulables = new ArrayList<>();
        try {
            Iterator<Reserva> iterator=reservas.iterator();
            while(iterator.hasNext()){
                Reserva reserva=iterator.next();
                if (reserva.getFechaInicioReserva().isAfter(LocalDate.now()))
                    reservasAnulables.add(reserva);
            }

            /*for (Reserva reserva : reservas) {
                if (reserva.getFechaInicioReserva().isAfter(LocalDate.now()))
                    reservasAnulables.add(reserva);
            }*/


        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return reservasAnulables;
    }

/*Crea el método anularReserva que pedirá el dni del huésped del que se desea anular una
reserva (haciendo uso de la clase Consola), obteniendo de todas las reservas de dicho huésped
aquellas que sean anulables. En el caso de que no tenga ninguna anulable deberá de informarse
de dicha circunstancia. Si solo tiene una reserva anulable deberá confirmarse de que realmente
se quiere anular. Y por último, en el caso de que el huésped tenga más de una reserva anulable,
deberán ser listadas precedidas por un número para que el usuario elija la reserva que
desea anular. */
    private static void anularReserva() {
        try {
            Huesped h= Consola.getHuespedPorDni();
            List<Reserva> li = controlador.getReservas(h);
            List<Reserva> reservasAnulables= getReservasAnulables(li);

            if(reservasAnulables.isEmpty()) {
                System.out.println("El huésped no tiene reservas anulables");
            }else if(reservasAnulables.size()==1) {
                System.out.println("Se ha encontrado una reserva anulable");
                System.out.println(reservasAnulables.get(0));
                System.out.println("Quieres anular la reserva? (SI/NO)");

                String respuesta = Entrada.cadena().toLowerCase();
                if (respuesta.equals("SI")){
                    controlador.borrar(reservasAnulables.get(0));
                System.out.println("Se ha anulado la reserva");
                }else if(respuesta.equals("NO")) {
                    System.out.println("La reserva no ha sido anulada");
                }else{
                    System.out.println("No válido");

                }

            }
        } catch (NullPointerException|IllegalArgumentException|OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }


    private static void mostrarReservas() {


        try {
            List<Reserva> listadoOrd= controlador.getReservas();

            Comparator<Habitacion> comparador=Comparator.comparing(Habitacion::getPlanta).thenComparing(Habitacion::getPuerta);
            listadoOrd.sort(Comparator.comparing(Reserva::getFechaInicioReserva).reversed().thenComparing(Reserva::getHabitacion,comparador));


            Iterator<Reserva> iterator= listadoOrd.iterator();
                while (iterator.hasNext()){
                    Reserva reserva=iterator.next();
                    System.out.println(reserva);
                }




        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva)
    {
        boolean tipoHabitacionEncontrada=false;
        Habitacion habitacionDisponible=null;
        int numElementos=0;

        Habitacion[] habitacionesTipoSolicitado= controlador.getHabitaciones(tipoHabitacion).toArray(Habitacion[]::new);

        if (habitacionesTipoSolicitado==null)
            return habitacionDisponible;

        for (int i=0; i<habitacionesTipoSolicitado.length && !tipoHabitacionEncontrada; i++)
        {

            if (habitacionesTipoSolicitado[i]!=null)
            {
                Reserva[] reservasFuturas = controlador.getReservas(habitacionesTipoSolicitado[i]).toArray(Reserva[]::new);
                numElementos=getNumElementosNoNulos(Arrays.stream(reservasFuturas).toList());

                if (numElementos == 0)
                {
                    //Si la primera de las habitaciones encontradas del tipo solicitado no tiene reservas en el futuro,
                    // quiere decir que está disponible.
                    habitacionDisponible=new Habitacion(habitacionesTipoSolicitado[i]);
                    tipoHabitacionEncontrada=true;
                }
                else {

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

                    if (!tipoHabitacionEncontrada)
                    {
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
                    if (!tipoHabitacionEncontrada)
                    {
                        for(int j=1;j<reservasFuturas.length && !tipoHabitacionEncontrada;j++)
                        {
                            if (reservasFuturas[j]!=null && reservasFuturas[j-1]!=null)
                            {
                                if(fechaInicioReserva.isAfter(reservasFuturas[j-1].getFechaFinReserva()) &&
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



    private static int getNumElementosNoNulos(List<Reserva> reservas) {
        int numero = 0;
        Iterator<Reserva> iterator=reservas.iterator();
        while(iterator.hasNext()){
            if(iterator.next()!=null){
                numero ++;
            }
        }
        return numero;
    }

















}
