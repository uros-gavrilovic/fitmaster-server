package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.MembershipAdapter;
import com.mastercode.fitmaster.dto.MembershipDTO;
import com.mastercode.fitmaster.model.MembershipEntity;
import com.mastercode.fitmaster.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MembershipService implements AbstractService<MembershipEntity, MembershipDTO> {

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    MembershipAdapter membershipAdapter;

    public List<MembershipEntity> getAll() {
        return membershipRepository.findAll();
    }

    @Override
    public MembershipEntity findByID(Long id) {
        return membershipRepository.getByMembershipID(id);
    }

    @Override
    public List<MembershipDTO> getAllDTOs() {
        return membershipAdapter.entitiesToDTOs(membershipRepository.findAll());
    }

    @Override
    public MembershipEntity create(MembershipEntity entity) {
        return membershipRepository.saveAndFlush(entity);
    }

    @Override
    public MembershipEntity update(MembershipEntity entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        membershipRepository.deleteById(id);
    }

    public Set<MembershipEntity> getMembershipsByMemberID(Long memberID) {
        return null;
        //        return membershipRepository.findAllByMemberMemberID(memberID);
    }
}
