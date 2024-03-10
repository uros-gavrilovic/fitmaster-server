package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.model.PackageEntity;
import org.springframework.stereotype.Component;

@Component
public class PackageAdapter extends AbstractAdapter<PackageEntity, PackageDTO> {

    @Override
    public PackageEntity dtoToEntity(PackageDTO dto) {
        if (dto == null) return null;
        final PackageEntity entity = new PackageEntity();

        entity.setPackageID(dto.getPackageID());
        entity.setName(dto.getName());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        entity.setCurrency(dto.getCurrency());

        return entity;
    }

    @Override
    public PackageDTO entityToDTO(PackageEntity entity) {
        if (entity == null) return null;
        final PackageDTO dto = new PackageDTO();

        dto.setPackageID(entity.getPackageID());
        dto.setName(entity.getName());
        dto.setDuration(entity.getDuration());
        dto.setPrice(entity.getPrice());
        dto.setCurrency(entity.getCurrency());

        return dto;
    }

}
