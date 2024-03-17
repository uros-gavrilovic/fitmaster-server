package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mastercode.fitmaster.data.MemberData.MEMBER_ENTITY;
import static com.mastercode.fitmaster.data.MemberData.UPDATED_MEMBER_ENTITY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest

public class MemberRepositoryRepositoryTest implements CRUDRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Test
    public void testSave() {
        MemberEntity savedMember = memberRepository.save(MEMBER_ENTITY);

        assertEquals(MEMBER_ENTITY.getFirstName(), savedMember.getFirstName());
        assertEquals(MEMBER_ENTITY.getLastName(), savedMember.getLastName());
        assertEquals(MEMBER_ENTITY.getGender(), savedMember.getGender());
        assertEquals(MEMBER_ENTITY.getBirthDate(), savedMember.getBirthDate());
        assertEquals(MEMBER_ENTITY.getAddress(), savedMember.getAddress());
        assertEquals(MEMBER_ENTITY.getPhoneNumber(), savedMember.getPhoneNumber());
    }

    @Override
    @Test
    public void testUpdate() {
        MemberEntity savedMember = memberRepository.save(MEMBER_ENTITY);
        Long savedMemberID = savedMember.getMemberID();

        MemberEntity memberToUpdate = UPDATED_MEMBER_ENTITY;

        MemberEntity updatedMember = memberRepository.save(memberToUpdate);

        assertNotEquals(savedMember.getFirstName(), updatedMember.getFirstName());
        assertNotEquals(savedMember.getLastName(), updatedMember.getLastName());
        assertNotEquals(savedMember.getGender(), updatedMember.getGender());

        assertEquals(UPDATED_MEMBER_ENTITY.getFirstName(), updatedMember.getFirstName());
        assertEquals(UPDATED_MEMBER_ENTITY.getLastName(), updatedMember.getLastName());
        assertEquals(UPDATED_MEMBER_ENTITY.getGender(), updatedMember.getGender());
        assertEquals(UPDATED_MEMBER_ENTITY.getBirthDate(), updatedMember.getBirthDate());
        assertEquals(UPDATED_MEMBER_ENTITY.getAddress(), updatedMember.getAddress());
        assertEquals(UPDATED_MEMBER_ENTITY.getPhoneNumber(), updatedMember.getPhoneNumber());
    }

    @Override
    @Test
    public void testFind() {
        Long savedMemberID = memberRepository.save(MEMBER_ENTITY).getMemberID();

        MemberEntity foundMember = memberRepository.findById(savedMemberID).get();

        assertEquals(MEMBER_ENTITY.getFirstName(), foundMember.getFirstName());
        assertEquals(MEMBER_ENTITY.getLastName(), foundMember.getLastName());
        assertEquals(MEMBER_ENTITY.getGender(), foundMember.getGender());
        assertEquals(MEMBER_ENTITY.getBirthDate(), foundMember.getBirthDate());
        assertEquals(MEMBER_ENTITY.getAddress(), foundMember.getAddress());
        assertEquals(MEMBER_ENTITY.getPhoneNumber(), foundMember.getPhoneNumber());
    }

    @Override
    @Test
    public void testDelete() {
        Long savedMemberID = memberRepository.save(MEMBER_ENTITY).getMemberID();
        memberRepository.deleteById(savedMemberID);

        assertEquals(0, memberRepository.count());

        MemberEntity foundMember = memberRepository.findById(savedMemberID).orElse(null);

        assertEquals(null, foundMember);

    }
}
