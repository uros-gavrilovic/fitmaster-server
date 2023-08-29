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

    private Member member;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        objectMapper = new ObjectMapper();

        this.member = new Member();
        this.member.setMemberID(1L);
        this.member.setFirstName("testFirstname");
        this.member.setLastName("testLastname");
        this.member.setUsername("testUsername");
        this.member.setPassword("testPassword");
    }

    @Test
    public void testGetAllMembers() throws Exception {
        Member m1 = new Member();
        m1.setMemberID(1L);
        m1.setFirstName("testFirstnameA");
        m1.setLastName("testLastnameA");

        Member m2 = new Member();
        m2.setMemberID(2L);
        m2.setFirstName("testFirstnameB");
        m2.setLastName("testLastnameB");

        List<Member> members = Arrays.asList(m1, m2);

        when(memberService.getAll()).thenReturn(members);

        mockMvc.perform(get("/api/member"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(members.size()));
    }

    @Test
    public void testGetMemberById() throws Exception {
        Long memberID = 1L;

        when(memberService.findByID(memberID)).thenReturn(member);

        mockMvc.perform(get("/api/member/{id}", memberID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(member.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(member.getLastName()));
    }

    @Test
    public void testCreateMember() throws Exception {
        Member createdMember = new Member();
        createdMember.setFirstName("testFirstname");
        createdMember.setLastName("testLastname");
        createdMember.setUsername("testUsername");
        createdMember.setPassword("testPassword");

        when(memberService.create(any(Member.class))).thenReturn(createdMember);

        mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(createdMember.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(createdMember.getLastName()));
    }

    @Test
    public void testCreateMemberMissingRequiredField() throws Exception {
        member.setFirstName(null);

        mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(member))).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMember() throws Exception {
        Member updatedMember = new Member();
        updatedMember.setMemberID(1L);
        updatedMember.setFirstName("testUpdatedFirstname");
        updatedMember.setLastName("testLastname");

        when(memberService.update(any(Member.class))).thenReturn(updatedMember);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(updatedMember.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedMember.getLastName()));
    }

    @Test
    public void testUpdateMemberMissingRequiredFiled() throws Exception {
        member.setFirstName(null);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(member))).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMemberWithNonExistingId() throws Exception {
        member.setMemberID(2L);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(member))).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteMember() throws Exception {
        Long memberIdToDelete = 1L;

        mockMvc.perform(delete("/api/member/{id}", memberIdToDelete)).andExpect(status().isNoContent());
    }

}
