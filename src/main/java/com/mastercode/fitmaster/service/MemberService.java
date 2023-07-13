package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.MemberAdapter;
import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements AbstractService<Member, MemberDTO> {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberAdapter memberAdapter;

    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findByID(final Long id) {
        return memberRepository.getByMemberID(id);
    }

    @Override
    public List<MemberDTO> getAllDTOs() {
        return memberAdapter.entitiesToDTOs(memberRepository.findAll());
    }

    @Override
    public Member create(Member entity) {
        return memberRepository.saveAndFlush(entity);
    }

    @Override
    public Member update(Member entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
