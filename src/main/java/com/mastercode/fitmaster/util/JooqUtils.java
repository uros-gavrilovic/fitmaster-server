package com.mastercode.fitmaster.util;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Record;

public final class JooqUtils {
    private JooqUtils() {
    }

    public static String likeAnythingBeforeAndAfter(String word) {
        return "%".concat(word).concat("%");
    }

    public static String likeAnythingAfter(String word) {
        return word.concat("%");
    }

    public static <C, R extends Record> C convertOptional(R record, Class<C> klass) {
        return !Objects.isNull(record) && !record.fieldsRow().fieldStream().allMatch((field) -> {
            return Objects.isNull(field.getValue(record));
        }) ? record.into(klass) : null;
    }

    public static void addStringCondition(Optional<?> filterValue, Field<String> field, List<Condition> conditions) {
        filterValue
                .filter(value -> value != null)
                .map(Object::toString)
                .map(JooqUtils::likeAnythingBeforeAndAfter)
                .map(field::likeIgnoreCase)
                .ifPresent(conditions::add);
    }
}
