package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.MemberAdapter;
import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.exception.UserException;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.repository.MemberRepository;
import com.mastercode.fitmaster.util.DescriptionUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService implements AbstractService<Member, MemberDTO> {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberAdapter memberAdapter;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        Member member = memberRepository.findById(entity.getMemberID())
                .orElseThrow(() -> new UserException("Member with id " + entity.getMemberID() + " does not exist", HttpStatus.NOT_FOUND));
        entity.setPassword(member.getPassword());
        entity.setUsername(member.getUsername());
        return memberRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public Map<String, Object> getMemberStatistics() {
        return memberRepository.getMemberStatistics();
    }

    public Member login(UserDTO userDTO) {
        Member member = memberRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new LoginException(DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"), HttpStatus.UNAUTHORIZED));

        if (passwordEncoder.matches(CharBuffer.wrap(userDTO.getPassword()), member.getPassword())) return member;

        throw new LoginException(DescriptionUtils.getErrorDescription("WRONG_USERNAME_OR_PASSWORD"), HttpStatus.UNAUTHORIZED);
    }

    public Member registerMember(Member member) {
        Optional<Member> optionalMember = memberRepository.findByUsername(member.getUsername());

        if (optionalMember.isPresent())
            throw new RegisterException(DescriptionUtils.getErrorDescription("USERNAME_TAKEN"), HttpStatus.CONFLICT);

        member.setPassword(passwordEncoder.encode(CharBuffer.wrap(member.getPassword())));
        Member createdMember = memberRepository.save(member);

        return createdMember;
    }

    @Transactional
    public UserDTO findByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new LoginException(DescriptionUtils.getErrorDescription("Unknown member"), HttpStatus.NOT_FOUND));
        return memberAdapter.entityToDTO(member);
    }
}
