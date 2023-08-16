package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.model.Package;
import com.mastercode.fitmaster.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping
    public List<Package> getAll() {
        return packageService.getAll();
    }

    @GetMapping("/dto")
    public ResponseEntity<List<PackageDTO>> getDTOs() {
        return new ResponseEntity<>(packageService.getAllDTOs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable Long id) {
        return new ResponseEntity<>(packageService.findByID(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Package> deletePackage(@PathVariable Long id) {
        packageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
    public ResponseEntity<Package> savePackage(@RequestBody Package entity) {
        return new ResponseEntity<>(packageService.create(entity), HttpStatus.CREATED);
    }

}
