package org.aimprosoft.dao;


import java.util.List;


public interface IDao<T> {

    void create(T entity);

    T read(String identification);

    List<T> readAll();

    void update(T entity);

    void delete(int id);

}

