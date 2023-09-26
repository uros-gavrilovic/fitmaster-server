package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member getByMemberID(Long id);


    @Query(nativeQuery = true, value = "SELECT\n" + "    SUM(CASE WHEN latest_memberships.end_date > CURRENT_DATE THEN 1 ELSE 0 END) AS active_members,\n" + "    SUM(CASE WHEN latest_memberships.end_date <= CURRENT_DATE THEN 1 ELSE 0 END) AS inactive_members\n" + "FROM (\n" + "    SELECT\n" + "        mb.id AS member_id,\n" + "        MAX(ms.end_date) AS end_date\n" + "    FROM members mb\n" + "    LEFT JOIN memberships ms ON mb.id = ms.member_id\n" + "    GROUP BY mb.id\n" + ") AS latest_memberships;")
    Map<String, Object> getMemberStatistics();

    @Query("select m from Member m where m.username = ?1")
    Optional<Member> findByUsername(String username);
}
