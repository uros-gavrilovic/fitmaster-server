package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.adapter.MemberAdapter;
import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberAdapter memberAdapter;
    @GetMapping
    public ResponseEntity<List<Member>> getAll() {
        return new ResponseEntity<>(memberService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/dto")
    public ResponseEntity<List<MemberDTO>> getAllDTOs() {
        return new ResponseEntity<>(memberService.getAllDTOs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getByID(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.findByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.create(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO dto) {
        Member member = memberAdapter.dtoToEntity(dto);
        return new ResponseEntity<>(memberAdapter.entityToDTO(memberService.update(member)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
