package com.example.clinica.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;

public class Turno {
    private Long id;
    private LocalDate fecha;
    private Odontologo odontologo;
    private Paciente paciente;
    private Long odontologoId;
    private Long pacienteId;

    /*
    @JsonIgnoreProperties(value = { "intValue" })
    @JsonIgnore
    */

    public Turno() {
    }

    public Turno(LocalDate fecha, Long odontologoId, Long pacienteId) {
        this.fecha = fecha;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", odontologoId=" + odontologoId +
                ", pacienteId=" + pacienteId +
                '}';
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


}
