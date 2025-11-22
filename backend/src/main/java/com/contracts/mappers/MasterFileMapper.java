package com.contracts.mappers;

import com.contracts.dtos.MasterFileDTO;
import com.domain.MasterFile;

public abstract class MasterFileMapper<E extends MasterFile, D extends MasterFileDTO> extends BaseMapper<E, D> {
    @Override
    public E toEntity(D dto) {
        if (dto == null) return null;
        E entity = super.toEntity(dto);
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public D toDTO(E entity) {
        if (entity == null) return null;
        D dto = super.toDTO(entity);
        dto.setName(entity.getName());
        return dto;
    }
}
