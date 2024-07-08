package com.mastercode.fitmaster.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.Collection;

@Data
@Builder
public class SearchResponse<T> {
    private Collection<T> data;
    @Builder.Default private long count = -1;

    public static <T> SearchResponse<T> of(Collection<T> data, long count) {
        return SearchResponse.<T>builder()
                .data(data)
                .count(count)
                .build();
    }
}
