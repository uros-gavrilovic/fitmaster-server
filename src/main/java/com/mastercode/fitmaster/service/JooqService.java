package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.dto.response.SearchResponse;
import com.mastercode.fitmaster.model.util.Direction;
import com.mastercode.fitmaster.model.util.PaginationAndSort;
import lombok.Getter;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import static org.jooq.impl.DSL.count;

@Service
@Getter
public class JooqService {
    private final DSLContext dslContext;
    private static final String TOTAL_ROWS_COLUMN_NAME = "total_rows";

    @Autowired
    public JooqService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public <E, F extends PaginationAndSort> SearchResponse<E> search(Select<?> jooqQuery, F filter, Class<E> responseClass) {
        Table<?> uTable = jooqQuery.asTable("u");
        SortField<?>[] tableSortFields = createSortFields(filter, uTable);

        Field<Integer> totalRows = count().over().as(TOTAL_ROWS_COLUMN_NAME);

        final SelectSeekStepN<org.jooq.Record> selectStep = dslContext
                .select(uTable.asterisk())
                .select(totalRows)
                .from(uTable)
                .orderBy(tableSortFields);

        filter.getLimit()
                .ifPresent(selectStep::limit);
        filter.getOffset()
                .ifPresent(selectStep::offset);

        final Table<org.jooq.Record> tTable = selectStep.asTable("t");

        AtomicInteger count = new AtomicInteger(0);

        final Class<?> deserializationClass = getDeserializationClass(responseClass);

        final List<E> test = dslContext
                .select(tTable.fields(jooqQuery.getSelect().toArray(new Field[0])))
                .select(tTable.field(totalRows).as(TOTAL_ROWS_COLUMN_NAME))
                .from(tTable)
                .orderBy(createSortFields(filter, tTable))
                .fetch(record -> {
                    final Field<?>[] fieldsWithoutCountField = Arrays.stream(record.fields())
                            .filter(field -> !field.getName().equals(TOTAL_ROWS_COLUMN_NAME))
                            .toArray(Field[]::new);

                    final org.jooq.Record recordCopyWithoutCountField = record.into(fieldsWithoutCountField);

                    if (count.get() == 0L) {
                        count.set((Integer) record.get(TOTAL_ROWS_COLUMN_NAME));
                    }

                    return (E) recordCopyWithoutCountField.into(deserializationClass);
                });

        return SearchResponse.of(test, count.longValue());
    }

    private static <F extends PaginationAndSort> SortField<?>[] createSortFields(F filter, Table<?> u) {
        return filter.getOrders().map(
                        orders -> orders.stream()
                                .map(order -> {
                                    Field<?> field = u.field(order.property());
                                    if (field == null) {
                                        throw new IllegalArgumentException("Invalid property name: " + order);
                                    }
                                    if (order.direction().equals(Direction.ASC))
                                        return field.asc();
                                    return field.desc();
                                }).collect(Collectors.toList())).map(t -> t.toArray(new SortField[0]))
                .orElse(new SortField[0]);
    }

    private Class<?> getDeserializationClass(Class<?> responseClass) {
        final Optional<? extends Class<?>> aClass = Optional.ofNullable(responseClass.getDeclaredAnnotation(JsonDeserialize.class))
                .map(JsonDeserialize::as);

        if (aClass.isPresent()) {
            return aClass.get();
        }
        return responseClass;
    }
}
