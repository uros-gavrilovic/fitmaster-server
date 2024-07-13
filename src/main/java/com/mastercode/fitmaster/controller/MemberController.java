package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.dto.member.MemberFilter;
import com.mastercode.fitmaster.dto.member.MemberSearchItem;
import com.mastercode.fitmaster.dto.response.SearchResponse;
import com.mastercode.fitmaster.model.MemberEntity;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.util.CustomLogger;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The MemberController class handles HTTP requests related to members of a fitness club.
 *
 * @author Uroš Gavrilović
 */
@AllArgsConstructor
@RestController()
@RequestMapping("/api/member")
public class MemberController {
    /**
     * Represents service class for entity MemberEntity.
     */
    @Autowired
    private MemberService memberService;

    /**
     * Retrieves a list of all members.
     *
     * @return A ResponseEntity containing a list of MemberEntity objects and a status code of OK (200).
     */
    @GetMapping
    public ResponseEntity<List<MemberEntity>> getAll() {
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

    @PostMapping("/search")
    public ResponseEntity<SearchResponse<MemberSearchItem>> search(@RequestBody MemberFilter filter) {
        CustomLogger.info("Search member with filter: " + filter);
        return new ResponseEntity<>(memberService.search(filter), HttpStatus.OK);
    }

    /**
     * Retrieves a member by their unique ID.
     *
     * @param id The unique ID of the member to retrieve.
     *
     * @return A ResponseEntity containing the retrieved MemberEntity object and a status code of OK (200),
     * or a 404 status code if the member is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberEntity> getByID(@PathVariable Long id) {
        MemberEntity foundMemberEntity = memberService.findByID(id);
        if (foundMemberEntity != null) {
            return new ResponseEntity<>(foundMemberEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new memberEntity.
     *
     * @param memberEntity The MemberEntity object representing the memberEntity to be created.
     *
     * @return A ResponseEntity containing the created MemberEntity object and a status code of CREATED (201).
     *
     * @throws MethodArgumentNotValidException if the memberEntity object is not valid.
     */
    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberEntity memberEntity) {
        return new ResponseEntity<>(memberService.create(memberEntity), HttpStatus.CREATED);
    }

    /**
     * Updates an existing memberEntity's information.
     *
     * @param memberEntity The MemberEntity object representing the updated memberEntity information.
     *
     * @return A ResponseEntity containing the updated MemberEntity object and a status code of OK (200),
     * or a 404 status code if the memberEntity is not found.
     *
     * @throws MethodArgumentNotValidException if the memberEntity object is not valid.
     */
    @PutMapping
    public ResponseEntity<MemberDTO> updateMember(@Valid @RequestBody MemberEntity memberEntity) {
        MemberDTO member = memberService.update(memberEntity);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
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
     *
     * @throws MethodArgumentNotValidException if the member ID is empty or null.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MemberEntity> deleteMember(@NotEmpty @PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
