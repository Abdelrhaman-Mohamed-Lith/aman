package com.contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.AbsSheetLineDTO;
import com.contracts.dtos.OrderDTO;
import com.domain.AbsSheetLine;
import com.domain.MemberOrder;
import com.domain.Status;

public abstract class AbsSheetLineMapper<E extends AbsSheetLine, D extends AbsSheetLineDTO> implements IMapper<E, D> {
    @Override
    public D toDTO(E entity) {
        if (entity == null) return null;
        D dto = createDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        if (entity.getOrder() != null)
            dto.setOrder((OrderDTO) MapperUtil.fetchMapper(MemberOrder.class).toDTO(entity.getOrder()));
        return dto;
    }

    @Override
    public E toEntity(D dto) {
        if (dto == null) return null;
        E entity = createEntity();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus() != null ? Status.valueOf(dto.getStatus()) : null);
        if (dto.getOrder() != null)
            entity.setOrder((MemberOrder) MapperUtil.fetchMapper(MemberOrder.class).toEntity(dto.getOrder()));
        return entity;
    }
}
