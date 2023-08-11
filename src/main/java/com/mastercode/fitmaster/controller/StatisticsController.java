package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/statistics")
public class StatisticsController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/members-activity")
    public ResponseEntity<Object> getTotalMembers() {
        Object statistics = memberService.getMemberStatistics();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
