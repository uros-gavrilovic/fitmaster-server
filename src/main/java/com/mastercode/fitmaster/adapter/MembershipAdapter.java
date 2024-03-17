package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.MembershipDTO;
import com.mastercode.fitmaster.model.MembershipEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembershipAdapter extends AbstractAdapter<MembershipEntity, MembershipDTO> {
    @Override
    public MembershipDTO entityToDTO(MembershipEntity entity) {
        if (entity == null) return null;

        return MembershipDTO.builder()
                .membershipID(entity.getMembershipID())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .active(entity.isActive())
                .build();
    }
}
