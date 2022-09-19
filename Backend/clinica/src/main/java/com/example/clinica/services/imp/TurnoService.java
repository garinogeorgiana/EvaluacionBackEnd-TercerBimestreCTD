package com.example.clinica.services.imp;


import com.example.clinica.domain.Odontologo;
import com.example.clinica.repository.IDao;
import com.example.clinica.domain.Turno;

import java.util.List;

public class TurnoService {
    private IDao<Turno> turnoIdao;

    public TurnoService(IDao<Turno> turnoIdao) {
        this.turnoIdao = turnoIdao;
    }

    public IDao<Turno> getTurnoIdao() {
        return turnoIdao;
    }

    public void setTurnoIdao(IDao<Turno> turnoIdao) {
        this.turnoIdao = turnoIdao;
    }

    public Turno guardar(Turno o) {
        return turnoIdao.guardar(o);
    }

    public void elimar(Long id) {
        turnoIdao.eliminar(id);
    }

    public Turno buscar(Long id) {
        return turnoIdao.buscar(id);
    }

    public List<Turno> buscarTodos() {
        return turnoIdao.buscarTodos();
    }

    public Turno actualizarTurno(Turno o, Long id) {
        return turnoIdao.actualizar(o,id);
    }
}
