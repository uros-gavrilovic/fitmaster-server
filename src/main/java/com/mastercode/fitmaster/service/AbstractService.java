package com.mastercode.fitmaster.service;

import java.util.List;

public interface AbstractService<T, TDTO> {

    List<T> getAll();

    T findByID(final Long id);

    List<TDTO> getAllDTOs();

    T create(final T entity);

    T update(final T entity);

    void delete(final Long id);
}
