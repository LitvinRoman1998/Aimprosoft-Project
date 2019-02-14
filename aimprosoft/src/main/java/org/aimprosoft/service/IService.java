package org.aimprosoft.service;


import org.aimprosoft.dao.IDao;

import java.util.List;

public interface IService<T> {
    IDao<T> getDao();

    default void create(T entity) {
        getDao().create(entity);
    }

    default T read(String identification) {
        return getDao().read(identification);
    }

    default List<T> readAll() {
        return getDao().readAll();
    }

    default void update(T entity) {
        getDao().update(entity);
    }

    default void delete(int id) {
        getDao().delete(id);
    }
}
