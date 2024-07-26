package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.dto.membership_package.CreatePackageRequest;
import com.mastercode.fitmaster.dto.membership_package.PackageFilter;
import com.mastercode.fitmaster.dto.membership_package.PackageProcedureSearchItem;
import com.mastercode.fitmaster.dto.response.CustomResponseEntity;
import com.mastercode.fitmaster.exception.PackageHasActiveMembershipsException;
import com.mastercode.fitmaster.model.PackageEntity;
import com.mastercode.fitmaster.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The PackageController class handles HTTP requests related to packages.
 *
 * @author Uroš Gavrilović
 */
@RestController
@RequestMapping(value = "/api/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    /**
     * Retrieves a list of all packages.
     *
     * @return A list of PackageEntity objects.
     */
    @GetMapping
    public List<PackageEntity> getAll() {
        return packageService.getAll();
    }

    /**
     * Retrieves a list of PackageDTO objects.
     *
     * @return A ResponseEntity containing a list of PackageDTO objects.
     */
    @GetMapping("/dto")
    public ResponseEntity<List<PackageDTO>> getDTOs() {
        return new ResponseEntity<>(packageService.getAllDTOs(), HttpStatus.OK);
    }

    /**
     * Retrieves a package by its ID.
     *
     * @param id The ID of the package to retrieve.
     *
     * @return A ResponseEntity containing the retrieved PackageEntity object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PackageEntity> getPackageById(@PathVariable Long id) {
        return new ResponseEntity<>(packageService.findByID(id), HttpStatus.OK);
    }

    /**
     * Creates a new package.
     *
     * @param entity The PackageEntity object to create.
     *
     * @return A ResponseEntity containing the created PackageEntity object.
     */
    @PostMapping("/save")
    public ResponseEntity<PackageDTO> savePackage(@RequestBody PackageEntity entity) {
        return new ResponseEntity<>(packageService.create(entity), HttpStatus.CREATED);
    }

    /**
     * Deletes a package by its ID.
     *
     * @param id The ID of the package to delete.
     *
     * @return A ResponseEntity with a status of NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PackageEntity> deletePackage(@PathVariable Long id) {
        packageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/procedure")
    public ResponseEntity<Long> createProcedure(@Valid @RequestBody CreatePackageRequest request) {
        return new ResponseEntity<>(packageService.createProcedure(request), HttpStatus.CREATED);
    }

    @PutMapping("/procedure")
    public ResponseEntity<Long> updateProcedure(@Valid @RequestBody CreatePackageRequest request) {
        return new ResponseEntity<>(packageService.updateProcedure(request), HttpStatus.OK);
    }

//    @PutMapping("/procedure")
//    public ResponseEntity<CustomResponseEntity> updateProcedure(@Valid @RequestBody CreatePackageRequest request) {
//        try {
//            return new ResponseEntity<>(
//                CustomResponseEntity.ofData(packageService.updateProcedure(request)),
//                HttpStatus.OK
//            );
//        } catch (PackageHasActiveMembershipsException e) {
//            return new ResponseEntity<>(
//                CustomResponseEntity.ofError(e.getTitle(), e.getMessage()),
//                HttpStatus.FORBIDDEN
//            );
//        }
//    }

    @DeleteMapping("/procedure/{id}")
    public ResponseEntity deleteProcedure(@PathVariable Long id) {
        packageService.deleteProcedure(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/procedure/search")
    public ResponseEntity<List<PackageProcedureSearchItem>> searchProcedure(@RequestBody PackageFilter filter) {
        return new ResponseEntity<>(packageService.searchProcedure(filter), HttpStatus.OK);
    }
}
