package com.mastercode.fitmaster.repository;

import com.mastercode.fitmaster.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    Package getByPackageID(Long id);
}
