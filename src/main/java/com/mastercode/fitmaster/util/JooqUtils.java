package com.mastercode.fitmaster.util;

import java.util.Objects;
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
}
