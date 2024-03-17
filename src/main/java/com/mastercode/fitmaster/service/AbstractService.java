package com.mastercode.fitmaster.service;

import java.util.List;

/**\
 *
 * @param <T> Entity
 * @param <TDTO> DTO
 * @param <TSV> Single View
 */
public interface AbstractService<T, TDTO> {

    List<T> getAll();

    T findByID(final Long id);

    List<TDTO> getAllDTOs();

    TDTO create(final T entity);

    TDTO update(final T entity);

    void delete(final Long id);
}
