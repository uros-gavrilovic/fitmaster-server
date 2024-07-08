package com.mastercode.fitmaster.dto.statistics;

public record MembershipActivityStatistic(
        int activeCount,
        int inactiveCount,
        int pendingCount,
        int bannedCount
) {}
