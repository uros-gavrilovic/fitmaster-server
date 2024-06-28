package com.mastercode.fitmaster.dto.response.search;

import com.mastercode.fitmaster.dto.MemberDTO;

import java.util.List;

public class SearchResponse {
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
    private List<?> data;

    public SearchResponse(List<?> data) {
        this.data = data;
    }
}
