package com.mastercode.fitmaster.model.util;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Optional;

@Data
public class PaginationAndSort {
    protected Optional<Integer> limit = Optional.of(10);
    protected Optional<Long> offset = Optional.of(0L);
    protected Optional<List<Order>> orders = Optional.empty();

    @JsonIgnore
    protected int getPage() {
        return getLimit().map(limit -> limit != 0 ? getOffset().orElse(0L)/limit : 1L).orElse(0L)
                .intValue();
    }

    @JsonIgnore
    protected int calculateOffset(int page, int pageSize) {
        if (pageSize == 0) return 0;

        return pageSize * page;
    }
}
