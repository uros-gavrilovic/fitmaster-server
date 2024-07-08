package com.mastercode.fitmaster.service;
import com.mastercode.fitmaster.dto.response.SearchResponse;
import java.util.List;

/**
 * @param <T>    Entity
 * @param <TDTO> DTO
 * @param <TSI>  Search Item
 * @param <TF>   Filter
 * @param <TSV>  Single View
 */
public interface AbstractService<T, TDTO, TSI, TF, TSV> {

    List<T> getAll();

    T findByID(final Long id);

    List<TDTO> getAllDTOs();

    default SearchResponse<TSI> search(TF filter) {
        throw new UnsupportedOperationException("TODO!");
    }

    TDTO create(final T entity);

    TDTO update(final T entity);

    void delete(final Long id);
}
