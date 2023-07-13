package com.mastercode.fitmaster.service;

import java.util.List;

public abstract class AbstractService<T, TDTO, TRepository, TAdapter> {

    public List<T> getAll();

    public T findByID(final Long id);

    public List<TDTO> getAllDTOs();

    public T create(final T entity);

    public T update(final T entity);

    public void delete(final Long id);
}
