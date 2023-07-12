package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Membership;
import com.mastercode.fitmaster.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MembershipService {
    @Autowired
    MembershipRepository membershipRepository;

    public List<Membership> getAll() {
        return membershipRepository.findAll();
    }
}
