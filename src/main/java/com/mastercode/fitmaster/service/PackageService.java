package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.PackageAdapter;
import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.model.Package;
import com.mastercode.fitmaster.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService implements AbstractService<Package, PackageDTO> {
    
    @Autowired
    PackageRepository packageRepository;

    @Autowired
    PackageAdapter packageAdapter;

    public List<Package> getAll() {
        return packageRepository.findAll();
    }

    @Override
    public Package findByID(Long id) {
        return packageRepository.getByPackageID(id);
    }

    @Override
    public List<PackageDTO> getAllDTOs() {
        return packageAdapter.entitiesToDTOs(packageRepository.findAll());
    }

    @Override
    public Package create(Package entity) {
        return packageRepository.saveAndFlush(entity);
    }

    @Override
    public Package update(Package entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        packageRepository.deleteById(id);
    }
}
