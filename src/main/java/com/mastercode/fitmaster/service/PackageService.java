package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.PackageAdapter;
import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.model.PackageEntity;
import com.mastercode.fitmaster.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService implements AbstractService<PackageEntity, PackageDTO> {

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    PackageAdapter packageAdapter;

    public List<PackageEntity> getAll() {
        return packageRepository.findAll();
    }

    @Override
    public PackageEntity findByID(Long id) {
        return packageRepository.getByPackageID(id);
    }

    @Override
    public List<PackageDTO> getAllDTOs() {
        return packageAdapter.entitiesToDTOs(packageRepository.findAll());
    }

    @Override
    public PackageEntity create(PackageEntity entity) {
        return packageRepository.saveAndFlush(entity);
    }

    @Override
    public PackageEntity update(PackageEntity entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        packageRepository.deleteById(id);
    }
}
