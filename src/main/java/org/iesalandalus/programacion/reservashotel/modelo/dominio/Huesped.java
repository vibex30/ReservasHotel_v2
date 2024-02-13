package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {
    public static final String ER_TELEFONO="[0-9]{9}";
    public static final String ER_CORREO=".+[@].+[.][a-zA-Z]+";
    public static final String ER_DNI="([\\d]{8})([a-zA-Z])";
    public static final String FORMATO_FECHA="dd/MM/yyyy";
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;




    //constructor con parámentros
    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento){
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    //constructor copia
    public Huesped(Huesped huesped){
        if (huesped==null)
            throw new NullPointerException("ERROR: No es posible copiar un huésped nulo.");
        setNombre(huesped.getNombre());
        setDni(huesped.getDni());
        setCorreo(huesped.getCorreo());
        setTelefono(huesped.getTelefono());
        setFechaNacimiento(huesped.getFechaNacimiento());

    }

    private String formateaNombre(String nombre){
        //Siempre que nos den un paramentro hay que validarlo
        if(nombre==null)
            throw new NullPointerException("ERROR: El nombre de un huésped no puede ser nulo.");
        if(nombre.isBlank())
            throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede estar vacío.");

        String nombreFormateado="";
        //quito espacio de la izquierda y derecha (no toca los espacios de en medio)
        //pasar el texto a minuscula
        String nombreEnMinusSinEspacios = nombre.toLowerCase().trim();
        //split, separar cuando haya un espacio, 1 o mas
        String[] nombreArray = nombreEnMinusSinEspacios.split("\\s+");
        //poner en mayuscula la primera letra
        for(int i=0; i<nombreArray.length; i++)
            if (!nombreArray[i].isEmpty()) {
                char inicialMayuscula = Character.toUpperCase(nombreArray[i].charAt(0));
                nombreFormateado += inicialMayuscula + nombreArray[i].substring(1) + " ";
            }

        return nombreFormateado.trim();
    }


    //METODO COMPROBAR LETRA DNI
    private Boolean comprobarLetraDni(String dni) {
        //Como lo meto por un patron, no es necesario poner .isblank
        Pattern patronDni= Pattern.compile(ER_DNI);
        Matcher m =patronDni.matcher(dni);
        if (!m.matches())
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        int numeroDni=Integer.parseInt(m.group(1));
        int resultadoDivision=numeroDni%23;
        String[] tablaLetras= {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
        if (m.group(2).equalsIgnoreCase(tablaLetras[resultadoDivision]))
            return true;
        return false;



    }
    private String getIniciales(){
        String iniciales = "";
        for (int i = 0; i < nombre.length(); i++) {
            if (i == 0 || nombre.charAt(i - 1) == ' ') {
                iniciales += nombre.charAt(i);
            }
        }

        return iniciales.toUpperCase();

    }



    //GETTER

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }


    //SETTER

    public void setCorreo(String correo) {
        if(correo==null)
            throw new NullPointerException("ERROR: El correo de un huésped no puede ser nulo.");
        if(!correo.matches(ER_CORREO))
            throw new IllegalArgumentException("ERROR: El correo del huésped no tiene un formato válido.");
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        if(nombre==null)
            throw new NullPointerException("ERROR: El nombre de un huésped no puede ser nulo.");
        this.nombre = formateaNombre(nombre);
    }

    public void setTelefono(String telefono) {
        if (telefono==null)
            throw new NullPointerException("ERROR: El teléfono de un huésped no puede ser nulo.");
        if(!telefono.matches(ER_TELEFONO))
            throw new IllegalArgumentException("ERROR: El teléfono del huésped no tiene un formato válido.");

        this.telefono = telefono;
    }

    private void setDni(String dni) {
        if(dni==null)
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        if(dni.isBlank()||dni.isEmpty())
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");

        if (!dni.matches((ER_DNI)))
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");

        if(!comprobarLetraDni(dni))
            throw new IllegalArgumentException("ERROR: La letra del dni del huésped no es correcta.");
        this.dni = dni;
    }


    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento==null)
            throw new NullPointerException("ERROR: La fecha de nacimiento de un huésped no puede ser nula.");
        if(fechaNacimiento.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser despues que el dia de hoy");
        this.fechaNacimiento = fechaNacimiento;
    }

    //equals, hasCode, toString


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return Objects.equals(dni, huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return
                "nombre=" + nombre + " ("+getIniciales()+")" + ", DNI=" + dni + ", correo=" + correo + ", teléfono=" + telefono + ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));




    }
}
