package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.model.Membership;
import com.mastercode.fitmaster.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The MembershipController class handles HTTP requests related to fitness memberships.
 *
 * @author Uroš Gavrilović
 */
@RestController
@RequestMapping(value = "/api/membership")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    /**
     * Retrieves a list of all fitness memberships.
     *
     * @return A list of Membership objects representing fitness memberships.
     */
    @GetMapping
    public List<Membership> getAll() {
        return membershipService.getAll();
    }
}
