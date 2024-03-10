package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
    PackageEntity getByPackageID(Long id);
}
