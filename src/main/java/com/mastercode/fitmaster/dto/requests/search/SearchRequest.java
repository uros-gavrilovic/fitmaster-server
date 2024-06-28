package com.mastercode.fitmaster.dto.requests.search;

import lombok.Data;

@Data
public abstract class SearchRequest {
    private int page = 0;
    private int size = 10;
}
