package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The MemberController class handles HTTP requests related to members of a fitness club.
 */
@AllArgsConstructor
@RestController()
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * Retrieves a list of all members.
     *
     * @return A ResponseEntity containing a list of Member objects and a status code of OK (200).
     */
    @GetMapping
    public ResponseEntity<List<Member>> getAll() {
        return new ResponseEntity<>(memberService.getAll(), HttpStatus.OK);
    }

    /**
     * Retrieves a list of MemberDTO objects containing summarized information about members.
     *
     * @return A ResponseEntity containing a list of MemberDTO objects and a status code of OK (200).
     */
    @GetMapping("/dto")
    public ResponseEntity<List<MemberDTO>> getAllDTOs() {
        return new ResponseEntity<>(memberService.getAllDTOs(), HttpStatus.OK);
    }

    /**
     * Retrieves a member by their unique ID.
     *
     * @param id The unique ID of the member to retrieve.
     *
     * @return A ResponseEntity containing the retrieved Member object and a status code of OK (200),
     * or a 404 status code if the member is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getByID(@PathVariable Long id) {
        Member foundMember = memberService.findByID(id);
        if (foundMember != null) {
            return new ResponseEntity<>(foundMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new member.
     *
     * @param member The Member object representing the member to be created.
     *
     * @return A ResponseEntity containing the created Member object and a status code of CREATED (201).
     */
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.create(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    /**
     * Updates an existing member's information.
     *
     * @param member The Member object representing the updated member information.
     *
     * @return A ResponseEntity containing the updated Member object and a status code of OK (200),
     * or a 404 status code if the member is not found.
     */
    @PutMapping
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        Member updatedMember = memberService.update(member);
        if (updatedMember != null) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a member by their unique ID.
     *
     * @param id The unique ID of the member to delete.
     *
     * @return A ResponseEntity with a status code of NO_CONTENT (204) if the member was successfully deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
