package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.model.Membership;
import com.mastercode.fitmaster.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/membership")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping
    public List<Membership> getAll() {
        return membershipService.getAll();
    }
}
