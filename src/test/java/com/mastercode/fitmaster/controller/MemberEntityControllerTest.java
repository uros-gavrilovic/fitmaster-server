package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mastercode.fitmaster.data.MemberData;
import com.mastercode.fitmaster.dto.MemberDTO;
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
import static com.mastercode.fitmaster.util.constants.JsonConstants.*;

import static com.mastercode.fitmaster.data.MemberData.*;

public class MemberEntityControllerTest {
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;
    private MockMvc mockMvc;

    private MemberEntity memberEntity;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        this.memberEntity = MEMBER_ENTITY.toBuilder().build();
    }

    @Test
    public void testGetAllMembers() throws Exception {
        MemberEntity m1 = MEMBER_ENTITY;
        MemberEntity m2 = MEMBER_ENTITY;

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
        when(memberService.create(any())).thenReturn(MEMBER_DTO);

        mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEntity)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(path(FIRST_NAME)).value(memberEntity.getFirstName()))
                .andExpect(jsonPath(path(LAST_NAME)).value(memberEntity.getLastName()))
                .andExpect(jsonPath(path(GENDER)).value(memberEntity.getGender().toString()))
//                .andExpect(jsonPath(path(BIRTH_DATE).value(memberEntity.getBirthDate().toString()))
                .andExpect(jsonPath(path(ADDRESS)).value(memberEntity.getAddress()))
                .andExpect(jsonPath(path(PHONE_NUMBER)).value(memberEntity.getPhoneNumber()))
                .andExpect(jsonPath(path(STATUS)).value(memberEntity.getStatus().toString()));
    }

    @Test
    public void testCreateMemberMissingRequiredField() throws Exception {
        memberEntity.setFirstName(null);

        mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberEntity))).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMember() throws Exception {
        MemberDTO updatedMember = UPDATED_MEMBER_DTO;
        when(memberService.update(any())).thenReturn(updatedMember);

        mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEntity)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(path(FIRST_NAME)).value(updatedMember.getFirstName()))
                .andExpect(jsonPath(path(LAST_NAME)).value(updatedMember.getLastName()))
                .andExpect(jsonPath(path(GENDER)).value(updatedMember.getGender().toString()))
//                .andExpect(jsonPath(path(BIRTH_DATE).value(memberEntity.getBirthDate().toString()))
                .andExpect(jsonPath(path(ADDRESS)).value(memberEntity.getAddress()))
                .andExpect(jsonPath(path(PHONE_NUMBER)).value(memberEntity.getPhoneNumber()))
                .andExpect(jsonPath(path(STATUS)).value(memberEntity.getStatus().toString()));
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
