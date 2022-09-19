package com.example.clinica.repository;

import java.util.List;

public interface IDao<T> {
    public T guardar(T t);

    public void eliminar(Long id);

    public T buscar(Long id);

    public T buscar(String email);

    public List<T> buscarTodos();

    public T actualizar(T t, Long id);

}
