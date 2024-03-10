package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.model.MemberEntity;
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

public class MemberEntityControllerTest {

    @InjectMocks
    private MemberController memberController;
    @Mock
    private MemberService memberService;
    private MockMvc mockMvc;

    private MemberEntity memberEntity;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        objectMapper = new ObjectMapper();

        this.memberEntity = new MemberEntity();
        this.memberEntity.setMemberID(1L);
        this.memberEntity.setFirstName("testFirstname");
        this.memberEntity.setLastName("testLastname");
        this.memberEntity.setUsername("testUsername");
        this.memberEntity.setPassword("testPassword");
    }

    @Test
    public void testGetAllMembers() throws Exception {
        MemberEntity m1 = new MemberEntity();
        m1.setMemberID(1L);
        m1.setFirstName("testFirstnameA");
        m1.setLastName("testLastnameA");

        MemberEntity m2 = new MemberEntity();
        m2.setMemberID(2L);
        m2.setFirstName("testFirstnameB");
        m2.setLastName("testLastnameB");

        List<MemberEntity> memberEntities = Arrays.asList(m1, m2);

        when(memberService.getAll()).thenReturn(memberEntities);

        mockMvc.perform(get("/api/member"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(memberEntities.size()));
    }

    @Test
    public void testGetMemberById() throws Exception {
        Long memberID = 1L;

        when(memberService.findByID(memberID)).thenReturn(memberEntity);

        mockMvc.perform(get("/api/member/{id}", memberID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(memberEntity.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(memberEntity.getLastName()));
    }

    @Test
    public void testCreateMember() throws Exception {
        MemberEntity createdMemberEntity = new MemberEntity();
        createdMemberEntity.setFirstName("testFirstname");
        createdMemberEntity.setLastName("testLastname");
        createdMemberEntity.setUsername("testUsername");
        createdMemberEntity.setPassword("testPassword");

        when(memberService.create(any(MemberEntity.class))).thenReturn(createdMemberEntity);

        mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEntity)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(createdMemberEntity.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(createdMemberEntity.getLastName()));
    }

    @Test
    public void testCreateMemberMissingRequiredField() throws Exception {
        memberEntity.setFirstName(null);

        mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberEntity))).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMember() throws Exception {
        MemberEntity updatedMemberEntity = new MemberEntity();
        updatedMemberEntity.setMemberID(1L);
        updatedMemberEntity.setFirstName("testUpdatedFirstname");
        updatedMemberEntity.setLastName("testLastname");

        when(memberService.update(any(MemberEntity.class))).thenReturn(updatedMemberEntity);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEntity)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(updatedMemberEntity.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedMemberEntity.getLastName()));
    }

    @Test
    public void testUpdateMemberMissingRequiredFiled() throws Exception {
        memberEntity.setFirstName(null);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberEntity))).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMemberWithNonExistingId() throws Exception {
        memberEntity.setMemberID(2L);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberEntity))).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteMember() throws Exception {
        Long memberIdToDelete = 1L;

        mockMvc.perform(delete("/api/member/{id}", memberIdToDelete)).andExpect(status().isNoContent());
    }

}
