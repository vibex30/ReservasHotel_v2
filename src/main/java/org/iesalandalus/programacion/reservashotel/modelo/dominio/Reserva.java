package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.Period;

public class Reserva {
    public static final int MAX_NUMERO_MESES_RESERVA=6;

    private final int MAX_HORAS_POSTERIOR_CHECKOUT=12;
    public static final String FORMATO_FECHA_RESERVA="dd/MM/yyyy";

    public static final String FORMATO_FECHA_HORA_RESERVA="dd/MM/yyyy hh:mm";
    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double precio=0.00;
    private int numeroPersonas;



    //Constructor con par�metros
    public Reserva(Huesped huesped, Habitacion habitacion, Regimen regimen, LocalDate fechaInicioReserva, LocalDate fechaFinReserva, int numeroPersonas){
        if (huesped == null)
            throw new NullPointerException("ERROR: El hu�sped de una reserva no puede ser nulo.");
        if (habitacion==null)
            throw new NullPointerException("ERROR: La habitaci�n de una reserva no puede ser nula.");
        if (regimen==null)
            throw new NullPointerException("ERROR: El r�gimen de una reserva no puede ser nulo.");
        if (fechaInicioReserva==null)
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        if (fechaFinReserva==null)
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");

        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);



    }
    //Constructor copia
    public Reserva(Reserva reserva){
        if(reserva==null)
            throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
        setHuesped(reserva.getHuesped());
        setHabitacion(reserva.getHabitacion());
        setRegimen(reserva.getRegimen());
        setFechaInicioReserva(reserva.getFechaInicioReserva());
        setFechaFinReserva(reserva.getFechaFinReserva());

        if (reserva.getCheckIn() == null)
            this.checkIn=null;
        else
            setCheckIn(reserva.getCheckIn());

        if (reserva.getCheckOut() == null)
            this.checkOut=null;
        else
            setCheckOut(reserva.getCheckOut());

        this.precio=0.00;
        setNumeroPersonas(reserva.getNumeroPersonas());
    }


    //GETTER


    public Huesped getHuesped() {
        return new Huesped(huesped);
    }

    public Habitacion getHabitacion() {
        return new Habitacion(habitacion);
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public double getPrecio() {
        return precio;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }


    //Setter

    public void setHuesped(Huesped huesped) {
        if (huesped==null)
            throw new NullPointerException("ERROR: El hu�sped de una reserva no puede ser nulo.");
        this.huesped = new Huesped(huesped);
    }

    public void setHabitacion(Habitacion habitacion) {
        if (habitacion==null)
            throw new NullPointerException("ERROR: La habitaci�n de una reserva no puede ser nula.");
        this.habitacion = new Habitacion(habitacion);
    }

    public void setRegimen(Regimen regimen) {
        if (regimen==null)
            throw new NullPointerException("ERROR: El r�gimen de una reserva no puede ser nulo.");
        this.regimen = regimen;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        if (fechaInicioReserva==null)
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        if(fechaInicioReserva.isBefore (LocalDate.now()))
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser anterior al d�a de hoy.");
        if(fechaInicioReserva.isAfter(LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA)))
            throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser posterior a seis meses.");
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        if(fechaFinReserva==null)
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        if(fechaFinReserva.isBefore(this.fechaInicioReserva))
            throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");
        if(fechaFinReserva.equals(fechaInicioReserva))
            throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");

        this.fechaFinReserva = fechaFinReserva;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        if(checkIn==null)
            throw new NullPointerException("ERROR: El checkin de una reserva no puede ser nulo.");
        if(checkIn.isBefore(fechaInicioReserva.atStartOfDay()))
            throw new IllegalArgumentException("ERROR: El checkin de una reserva no puede ser anterior a la fecha de inicio de la reserva.");

        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        if(checkOut==null)
            throw new NullPointerException("ERROR: El checkout de una reserva no puede ser nulo.");
        if(checkIn.isAfter(checkOut))
            throw new IllegalArgumentException("ERROR: El checkout de una reserva no puede ser anterior al checkin.");
        if(checkOut.isAfter(fechaFinReserva.atStartOfDay().plusHours(MAX_HORAS_POSTERIOR_CHECKOUT)))
            throw new IllegalArgumentException("ERROR: El checkout de una reserva puede ser como m�ximo 12 horas despu�s de la fecha de fin de la reserva.");
        this.checkOut = checkOut;
        setPrecio();
    }

    private void setPrecio() {

        Period periodoDeTiempo= Period.between(fechaInicioReserva,fechaFinReserva);
        this.precio = habitacion.getPrecio() * periodoDeTiempo.getDays() + regimen.getIncrementoPrecio()* getNumeroPersonas()* periodoDeTiempo.getDays();
    }

    public void setNumeroPersonas(int numeroPersonas) {
        if(numeroPersonas<0)
            throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede ser menor o igual a 0.");
        if (numeroPersonas>this.habitacion.getTipoHabitacion().getNumeroMaximoPersonas())
            throw new IllegalArgumentException("Error: El numero de personas sobrepasa la capacidad");

        this.numeroPersonas = numeroPersonas;
    }

    //Equals, hashCode

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Reserva reserva)) return false;
        return Objects.equals(getHabitacion(), reserva.getHabitacion()) && Objects.equals(getFechaInicioReserva(), reserva.getFechaInicioReserva());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHabitacion(), getFechaInicioReserva());
    }

    //toString

    @Override
    public String toString() {
        return
                "Huesped: "+ getHuesped().getNombre()+" "+
                        getHuesped().getDni()+" "+ "Habitaci�n:"+habitacion.getIdentificador()+" - "+TipoHabitacion.SIMPLE +" Fecha Inicio Reserva: "+fechaInicioReserva.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA))+" Fecha Fin Reserva: "+fechaFinReserva.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA))+" Checkin: "+getCheckIn()+" Checkout: "+getCheckOut()+" Precio: "+getPrecio()+" Personas: "+getNumeroPersonas();

    }
}
