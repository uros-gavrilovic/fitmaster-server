package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;
    @Mock
    private MemberService memberService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void testGetAllMembers() throws Exception {
        Member m1 = new Member();
        m1.setMemberID(1L);
        m1.setFirstName("TEST_FIRSTNAME_1");
        m1.setLastName("TEST_LASTNAME_1");

        Member m2 = new Member();
        m2.setMemberID(2L);
        m2.setFirstName("TEST_FIRSTNAME_2");
        m2.setLastName("TEST_LASTNAME_2");

        List<Member> members = Arrays.asList(m1, m2);

        when(memberService.getAll()).thenReturn(members);

        mockMvc.perform(get("/api/member"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(members.size()));
    }

    @Test
    public void testGetMemberById() throws Exception {
        Long memberID = 1L;

        Member member = new Member();
        member.setMemberID(memberID);
        member.setFirstName("TEST_FIRSTNAME");
        member.setLastName("TEST_LASTNAME");

        when(memberService.findByID(memberID)).thenReturn(member);

        mockMvc.perform(get("/api/member/{id}", memberID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(member.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(member.getLastName()));
    }

    @Test
    public void testCreateMember() throws Exception {
        Member newMember = new Member();
        newMember.setFirstName("TEST_FIRSTNAME");
        newMember.setLastName("TEST_LASTNAME");

        when(memberService.create(any(Member.class))).thenReturn(newMember);

        mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newMember)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(newMember.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(newMember.getLastName()));
    }

    @Test
    public void testUpdateMember() throws Exception {
        Long memberId = 1L;
        Member updatedMember = new Member();
        updatedMember.setMemberID(memberId);
        updatedMember.setFirstName("TEST_UPDATED_FIRSTNAME");
        updatedMember.setLastName("TEST_UPDATED_LASTNAME");

        when(memberService.update(any(Member.class))).thenReturn(updatedMember);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatedMember)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(updatedMember.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedMember.getLastName()));
    }

    @Test
    public void testDeleteMember() throws Exception {
        Long memberIdToDelete = 1L;

        mockMvc.perform(delete("/api/member/{id}", memberIdToDelete)).andExpect(status().isNoContent());
    }

    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
