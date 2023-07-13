package com.mastercode.fitmaster.adapter;

public interface AbstractAdapter<T, TDTO> {
    T dtoToEntity(TDTO dto);

    TDTO entityToDTO(T entity);
}
