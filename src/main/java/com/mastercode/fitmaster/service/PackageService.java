package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.PackageAdapter;
import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.model.PackageEntity;
import com.mastercode.fitmaster.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
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
    public PackageDTO create(PackageEntity entity) {
        return packageAdapter.entityToDTO(packageRepository.saveAndFlush(entity));
    }

    @Override
    public PackageDTO update(PackageEntity entity) {
        throw new UnsupportedOperationException(); // TODO: Implement
    }

    @Override
    public void delete(Long id) {
        packageRepository.deleteById(id);
    }
}
