package com.example.clinica.domain;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Date;

public class Paciente {
    private Long id;
    private String apellido;
    private String nombre;
    private String email;
    private int dni;
    private LocalDate fechaIngreso;
    private Odontologo odontologo;
    private Domicilio domicilio;
    //para setear el id de odontologo hace falta que este guardado en la base de datos, por que se crea de forma autoincremental.
    //puede ser innecesario ya que en la clase turno guardo referencia de quien es el turno tanto Odontologo como Paciente.
    //Y no podemos indexarlo como hacemos con el id de domicilio en el H2, ya que aunque guarde la referencia, odontologo y paciente son independientes.
    private Long ondotologoId;
    private Long domicilioId;//No crear un paciente sin domicilio, en el crud de guardar Paciente primero guardamos el domicilio para conseguir el id automatico.

    public Paciente(String apellido, String nombre, String email, int dni, LocalDate fechaIngreso) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio.getCalle()+" "+domicilio.getNumero() +
                ", domicilioId=" + domicilioId +
                '}';
    }

    public Long getOndotologoId() {
        return ondotologoId;
    }

    public void setOndotologoId(Long ondotologoId) {
        this.ondotologoId = ondotologoId;
    }

    public Long getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(Long domicilioId) {
        this.domicilioId = domicilioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
