package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

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
        Member member = new Member();
        member.setFirstName("TEST_FIRSTNAME");
        member.setLastName("TEST_LASTNAME");

        when(memberRepository.saveAndFlush(member)).thenReturn(member);

        Member createdMember = memberService.create(member);

        assertEquals(member, createdMember);
    }

    @Test
    public void testUpdateMember() {
        Member member = new Member();
        member.setMemberID(1L);
        member.setFirstName("TEST_FIRSTNAME");
        member.setLastName("TEST_LASTNAME");

        when(memberRepository.saveAndFlush(member)).thenReturn(member);

        Member updatedMember = memberService.update(member);

        assertEquals(member, updatedMember);
    }

    @Test
    public void testDeleteMember() {
        Long memberID = 1L;

        memberService.delete(memberID);

        // Verify that the deleteById method of the repository was called once and with correct memberID.
        verify(memberRepository, times(1)).deleteById(memberID);
    }
}
