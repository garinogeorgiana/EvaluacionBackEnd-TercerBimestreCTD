package com.example.clinica.services.imp;


import com.example.clinica.repository.IDao;
import com.example.clinica.domain.Domicilio;

import java.util.List;

public class DomicilioService {
    private IDao<Domicilio> domicilioIdao;

    public DomicilioService(IDao<Domicilio> domicilioIdao) {
        this.domicilioIdao = domicilioIdao;
    }

    public IDao<Domicilio> getDomicilioIdao() {
        return domicilioIdao;
    }

    public void setDomicilioIdao(IDao<Domicilio> domicilioIdao) {
        this.domicilioIdao = domicilioIdao;
    }

    public Domicilio guardar(Domicilio o) {
        return domicilioIdao.guardar(o);
    }

    public void elimarDomicilio(Long id) {
        domicilioIdao.eliminar(id);
    }

    public Domicilio buscarDomicilio(Long id) {
        return domicilioIdao.buscar(id);
    }

    public List<Domicilio> buscarTodos() {
        return domicilioIdao.buscarTodos();
    }
}