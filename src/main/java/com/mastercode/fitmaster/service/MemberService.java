package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.MemberAdapter;
import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberAdapter memberAdapter;

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public List<MemberDTO> getAllMembers() {
        return memberAdapter.entitiesToDTOs(memberRepository.findAll());
    }
}
