package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.model.PackageEntity;
import org.springframework.stereotype.Component;

@Component
public class PackageAdapter extends AbstractAdapter<PackageEntity, PackageDTO> {
    @Override
    public PackageDTO entityToDTO(PackageEntity entity) {
        if (entity == null) return null;

        return PackageDTO.builder()
                .packageID(entity.getPackageID())
                .name(entity.getName())
                .duration(entity.getDuration())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }

}
