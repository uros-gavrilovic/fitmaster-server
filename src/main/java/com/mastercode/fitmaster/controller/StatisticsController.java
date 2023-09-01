package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The StatisticsController class handles HTTP requests related to fitness statistics.
 */
@RestController
@RequestMapping("api/statistics")
public class StatisticsController {

    @Autowired
    private MemberService memberService;

    /**
     * Retrieves statistics related to member activity.
     *
     * @return A ResponseEntity containing the member statistics and a status code of OK (200).
     */
    @GetMapping("/members-activity")
    public ResponseEntity<Object> getTotalMembers() {
        Object statistics = memberService.getMemberStatistics();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
