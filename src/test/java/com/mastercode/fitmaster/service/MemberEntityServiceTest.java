package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.MemberEntity;
import com.mastercode.fitmaster.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MemberEntityServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMember() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setFirstName("TEST_FIRSTNAME");
        memberEntity.setLastName("TEST_LASTNAME");

        when(memberRepository.saveAndFlush(memberEntity)).thenReturn(memberEntity);

        MemberEntity createdMemberEntity = memberService.create(memberEntity);

        assertEquals(memberEntity, createdMemberEntity);
    }

    @Test
    public void testUpdateMember() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberID(1L);
        memberEntity.setFirstName("TEST_FIRSTNAME");
        memberEntity.setLastName("TEST_LASTNAME");

        when(memberRepository.saveAndFlush(memberEntity)).thenReturn(memberEntity);

        MemberEntity updatedMemberEntity = memberService.update(memberEntity);

        assertEquals(memberEntity, updatedMemberEntity);
    }

    @Test
    public void testDeleteMember() {
        Long memberID = 1L;

        memberService.delete(memberID);

        // Verify that the deleteById method of the repository was called once and with correct memberID.
        verify(memberRepository, times(1)).deleteById(memberID);
    }
}
