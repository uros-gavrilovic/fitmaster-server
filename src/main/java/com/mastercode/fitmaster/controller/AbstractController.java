package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.requests.search.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface AbstractController {
    @PostMapping("/search")
    ResponseEntity search(SearchRequest searchRequest);
}
