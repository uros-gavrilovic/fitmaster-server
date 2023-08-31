package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.model.Package;
import org.springframework.stereotype.Component;

@Component
public class PackageAdapter extends AbstractAdapter<Package, PackageDTO> {

    @Override
    public Package dtoToEntity(PackageDTO dto) {
        if (dto == null) return null;
        final Package entity = new Package();

        entity.setPackageID(dto.getPackageID());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setCurrency(dto.getCurrency());

        return entity;
    }

    @Override
    public PackageDTO entityToDTO(Package entity) {
        if (entity == null) return null;
        final PackageDTO dto = new PackageDTO();

        dto.setPackageID(entity.getPackageID());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setCurrency(entity.getCurrency());

        return dto;
    }

}
