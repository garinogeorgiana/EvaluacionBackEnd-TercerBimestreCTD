package com.example.clinica.services.imp;

import com.example.clinica.repository.IDao;
import com.example.clinica.domain.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIdao;

    public OdontologoService(IDao<Odontologo> odontologoIdao) {
        this.odontologoIdao = odontologoIdao;
    }

    public IDao<Odontologo> getOdontologoIdao() {
        return odontologoIdao;
    }

    public void setOdontologoIdao(IDao<Odontologo> odontologoIdao) {
        this.odontologoIdao = odontologoIdao;
    }

    public Odontologo guardar(Odontologo o) {
        return odontologoIdao.guardar(o);
    }

    public Odontologo actualizarOdontologo(Odontologo o,Long id) {
        return odontologoIdao.actualizar(o,id);
    }

    public void elimarOdontologo(Long id) {
        odontologoIdao.eliminar(id);
    }

    public Odontologo buscarOdontologo(Long id) {
        return odontologoIdao.buscar(id);
    }

    public List<Odontologo> buscarTodos() {
        return odontologoIdao.buscarTodos();
    }
}