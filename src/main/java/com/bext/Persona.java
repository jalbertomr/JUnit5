package com.bext;

public class Persona {
    String Nombre;
    String Apellido;

    public Persona(String nombre, String apellido) {
        Nombre = nombre;
        Apellido = apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
}
