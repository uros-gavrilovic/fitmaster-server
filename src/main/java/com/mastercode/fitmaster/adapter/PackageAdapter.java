package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.PackageDTO;
import com.mastercode.fitmaster.dto.membership_package.PackageDto;
import com.mastercode.fitmaster.exception.CustomProcedureMapperException;
import com.mastercode.fitmaster.model.PackageEntity;
import com.mastercode.fitmaster.util.DescriptionUtils;
import com.mastercode.fitmaster.util.JsonUtils;
import com.mastercode.fitmaster.util.constants.ErrorConstants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;

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

    public static PackageDto mapToDTO(Object object) {
        try {
            String[] data = JsonUtils.getValues(object);
            Long id = Long.parseLong(data[0]);
            String name = data[1];
            Integer duration = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            Currency currency = Currency.getInstance(data[4]);

            return new PackageDto(id, name, duration, price, currency.getCurrencyCode());
        } catch (Exception e) {
            throw new CustomProcedureMapperException(e.getMessage());
        }
    }
}
