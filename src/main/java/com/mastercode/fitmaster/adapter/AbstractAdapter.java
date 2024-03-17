package com.mastercode.fitmaster.adapter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAdapter<T, TDTO> {
//    public abstract T dtoToEntity(TDTO dto);

    public abstract TDTO entityToDTO(T entity);

    public List<TDTO> entitiesToDTOs(List<T> entities) {
        if (entities == null) return null;

        return entities.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
