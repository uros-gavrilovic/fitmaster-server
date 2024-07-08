package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.statistics.MembershipActivityStatistic;
import com.mastercode.fitmaster.service.StatisticsService;
import com.mastercode.fitmaster.util.CustomLogger;
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
    private StatisticsService statisticsService;

    /**
     * Retrieves statistics related to member activity.
     *
     * @return A ResponseEntity containing the member statistics and a status code of OK (200).
     */
    @GetMapping("/members-activity")
    public ResponseEntity<MembershipActivityStatistic> getTotalMembers() {
        CustomLogger.info("Retrieving member statistics ...");
        return new ResponseEntity<>(statisticsService.getMemberStatistics(), HttpStatus.OK);
    }
}
