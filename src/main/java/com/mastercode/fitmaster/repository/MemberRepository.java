package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member getByMemberID(Long id);
}
