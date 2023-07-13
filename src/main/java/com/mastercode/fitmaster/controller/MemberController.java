package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
