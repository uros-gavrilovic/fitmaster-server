package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.PackageAdapter;
import com.mastercode.fitmaster.adapter.ProcedureDTOAdapter;
import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.dto.membership_package.*;
import com.mastercode.fitmaster.exception.PackageHasActiveMembershipsException;
import com.mastercode.fitmaster.model.PackageEntity;
import com.mastercode.fitmaster.repository.PackageRepository;
import com.mastercode.fitmaster.util.DescriptionUtils;
import com.mastercode.fitmaster.util.constants.ApplicationConstants;
import com.mastercode.fitmaster.util.constants.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PackageService implements AbstractService <PackageEntity,
                                                        PackageDTO,
                                                        PackageSearchItem,
                                                        PackageFilter,
                                                        PackageSingleView> {

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

    public Long createProcedure(CreatePackageRequest request) {
        return packageRepository.createProcedure(
            request.name(),
            request.duration(),
            request.price(),
            Optional.ofNullable(request.currency())
                .orElse(ApplicationConstants.DEFAULT_CURRENCY)
        );
    }

    public Long updateProcedure(CreatePackageRequest request) throws PackageHasActiveMembershipsException {
        try {
            return packageRepository.updateProcedure(
                request.id(),
                request.name(),
                request.duration(),
                request.price(),
                Optional.ofNullable(request.currency())
                    .orElse(ApplicationConstants.DEFAULT_CURRENCY)
            );
        } catch (Exception e) {
            throw new PackageHasActiveMembershipsException(
                DescriptionUtils.getErrorDescription(ErrorConstants.PACKAGE_HAS_ACTIVE_MEMBERSHIPS),
                HttpStatus.FORBIDDEN
            );
        }
    }

    public void deleteProcedure(Long id) {
        packageRepository.deleteProcedure(id);
    }

    public List<PackageProcedureSearchItem> searchProcedure(PackageFilter filter) {
        return packageRepository.searchProcedure(
            filter.getLimit().orElse(10),
            filter.getOffset().orElse(0L),
            filter.getName().orElse(null),
            filter.getDuration().orElse(null),
            filter.getPrice().orElse(null),
            filter.getCurrency()
                .orElse(null),
            filter.getOrders().stream()
                .findFirst().map(order -> order.get(0).property())
                .orElse(null),
            filter.getOrders().stream()
                .findFirst().map(order -> order.get(0).direction().name())
                .orElse(null))
            .stream()
            .map(result -> ProcedureDTOAdapter.mapToPackageProcedureSearchItem(result))
            .toList();
    }
}
