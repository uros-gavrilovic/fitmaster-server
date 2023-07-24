package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.service.MemberService;
import org.apache.coyote.Response;
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

    @GetMapping
    public ResponseEntity<List<Member>> getAll() {
        return new ResponseEntity<>(memberService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getByID(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.findByID(id), HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<MemberDTO>> getAllDTOs() {
        return new ResponseEntity<>(memberService.getAllDTOs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.create(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        Member updatedMember = memberService.update(member);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
