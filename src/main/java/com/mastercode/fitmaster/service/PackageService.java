package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Package;
import com.mastercode.fitmaster.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;

    public List<Package> getAll() {
        return packageRepository.findAll();
    }
}
