package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.MemberAdapter;
import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.mastercode.fitmaster.data.MemberData.*;

public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberAdapter memberAdapter;

    @InjectMocks
    private MemberService memberService;

    @PersistenceContext
    EntityManager entityManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(memberAdapter.entityToDTO(MEMBER_ENTITY))
                .thenReturn(MEMBER_DTO);
    }

    @Test
    public void testCreateMember() {
        when(memberRepository.saveAndFlush(MEMBER_ENTITY))
                .thenReturn(MEMBER_ENTITY);

        MemberDTO createdMemberDTO = memberService.create(MEMBER_ENTITY);

        assertEquals(MEMBER_DTO, createdMemberDTO);

        verify(memberRepository, times(1))
                .saveAndFlush(MEMBER_ENTITY);
    }

    @Test
    public void testUpdateMember() {
        when(memberRepository.saveAndFlush(MEMBER_ENTITY)).thenReturn(MEMBER_ENTITY);
        when(memberRepository.findById(MEMBER_ENTITY.getMemberID())).thenReturn(java.util.Optional.of(MEMBER_ENTITY));
        when(memberRepository.saveAndFlush(UPDATED_MEMBER_ENTITY)).thenReturn(UPDATED_MEMBER_ENTITY);
        when(memberAdapter.entityToDTO(UPDATED_MEMBER_ENTITY)).thenReturn(UPDATED_MEMBER_DTO);

        MemberDTO createdMemberDTO = memberService.create(MEMBER_ENTITY);

        MemberDTO updatedMemberDTO = memberService.update(UPDATED_MEMBER_ENTITY);

        assertNotNull(updatedMemberDTO);

        assertNotEquals(createdMemberDTO.getFirstName(), updatedMemberDTO.getFirstName());
        assertNotEquals(createdMemberDTO.getLastName(), updatedMemberDTO.getLastName());
        assertNotEquals(createdMemberDTO.getGender(), updatedMemberDTO.getGender());

        assertEquals(createdMemberDTO.getAddress(), updatedMemberDTO.getAddress());
        assertEquals(createdMemberDTO.getPhoneNumber(), updatedMemberDTO.getPhoneNumber());
        assertEquals(createdMemberDTO.getStatus(), updatedMemberDTO.getStatus());
    }

    @Test
    public void testDeleteMember() {
        Long memberID = 1L;

        memberService.delete(memberID);

        // Verify that the deleteById method of the repository was called once and with correct memberID.
        verify(memberRepository, times(1)).deleteById(memberID);
    }
}
