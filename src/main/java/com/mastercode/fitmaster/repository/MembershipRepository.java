package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
    MembershipEntity getByMembershipID(Long id);

    //    Set<MembershipEntity> findAllByMemberMemberID(Long memberID);
}

