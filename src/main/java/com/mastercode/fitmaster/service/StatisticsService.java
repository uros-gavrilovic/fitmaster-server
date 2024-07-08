package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.dto.statistics.MembershipActivityStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import static org.jooq.impl.DSL.*;
import static com.mastercode.fitmaster.service.jooq.tables.Member.MEMBER;
import static com.mastercode.fitmaster.service.jooq.tables.Membership.MEMBERSHIP;

@Service
public class StatisticsService {

    @Autowired
    private JooqService jooqService;


    public MembershipActivityStatistic getMemberStatistics() {
        final LocalDate currentDate = LocalDate.now();

        var activeMembershipCondition =
                selectOne()
                        .from(MEMBERSHIP)
                        .where(MEMBERSHIP.MEMBER_ID.eq(MEMBER.ID)
                                .and(MEMBERSHIP.START_DATE.le(currentDate))
                                .and(MEMBERSHIP.END_DATE.ge(currentDate)));

        var hasMembershipCondition =
                selectOne()
                        .from(MEMBERSHIP)
                        .where(MEMBERSHIP.MEMBER_ID.eq(MEMBER.ID));

        return jooqService.getDslContext()
                .select(
                        sum(when(MEMBER.IS_BANNED.isFalse().and(exists(activeMembershipCondition)), 1).otherwise(0)).as("activeCount"),
                        sum(when(MEMBER.IS_BANNED.isFalse().andNotExists(activeMembershipCondition).and(exists(hasMembershipCondition)), 1).otherwise(0)).as("inactiveCount"),
                        sum(when(MEMBER.IS_BANNED.isFalse().andNotExists(hasMembershipCondition), 1).otherwise(0)).as("pendingCount"),
                        sum(when(MEMBER.IS_BANNED.isTrue(), 1).otherwise(0)).as("bannedCount")
                )
                .from(MEMBER)
                .fetchOneInto(MembershipActivityStatistic.class);
    }
}
