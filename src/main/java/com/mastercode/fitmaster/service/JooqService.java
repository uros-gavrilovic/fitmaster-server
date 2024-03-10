package com.mastercode.fitmaster.service;

import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class JooqService {
    private final DSLContext dslContext;

    @Autowired
    public JooqService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
