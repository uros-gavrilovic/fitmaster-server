package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.model.Package;
import com.mastercode.fitmaster.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    public List<Package> getAll() {
        return packageService.getAll();
    }

}
