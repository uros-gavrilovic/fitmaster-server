package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity getByMemberID(Long id);

    Optional<MemberEntity> findByUsername(String username);
}
