package com.contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.OrderDTO;
import com.contracts.dtos.SheetLineDTO;
import com.domain.MemberOrder;
import com.domain.SheetLine;
import com.domain.Status;

public class SheetLineMapper implements IMapper<SheetLine, SheetLineDTO> {
    @Override
    public SheetLineDTO toDTO(SheetLine entity) {
        if (entity == null) return null;
        SheetLineDTO dto = new SheetLineDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        if (entity.getOrder() != null)
            dto.setOrder((OrderDTO) MapperUtil.fetchMapper(MemberOrder.class).toDTO(entity.getOrder()));
        return dto;
    }

    @Override
    public SheetLine toEntity(SheetLineDTO dto) {
        if (dto == null) return null;

        SheetLine entity = new SheetLine();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus() != null ? Status.valueOf(dto.getStatus()) : null);
        if (dto.getOrder() != null)
            entity.setOrder((MemberOrder) MapperUtil.fetchMapper(MemberOrder.class).toEntity(dto.getOrder()));
        return entity;
    }
}
