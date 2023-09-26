package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Membership getByMembershipID(Long id);

    Set<Membership> findAllByMemberMemberID(Long memberID);
}

