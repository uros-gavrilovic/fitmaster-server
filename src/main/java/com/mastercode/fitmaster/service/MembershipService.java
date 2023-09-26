package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.MembershipAdapter;
import com.mastercode.fitmaster.dto.MembershipDTO;
import com.mastercode.fitmaster.model.Membership;
import com.mastercode.fitmaster.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MembershipService implements AbstractService<Membership, MembershipDTO> {

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    MembershipAdapter membershipAdapter;

    public List<Membership> getAll() {
        return membershipRepository.findAll();
    }

    @Override
    public Membership findByID(Long id) {
        return membershipRepository.getByMembershipID(id);
    }

    @Override
    public List<MembershipDTO> getAllDTOs() {
        return membershipAdapter.entitiesToDTOs(membershipRepository.findAll());
    }

    @Override
    public Membership create(Membership entity) {
        return membershipRepository.saveAndFlush(entity);
    }

    @Override
    public Membership update(Membership entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        membershipRepository.deleteById(id);
    }

    public Set<Membership> getMembershipsByMemberID(Long memberID) {
        return membershipRepository.findAllByMemberMemberID(memberID);
    }
}
