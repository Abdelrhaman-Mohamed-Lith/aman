package com.contracts.mappers;

import com.contracts.dtos.BaseEntityDTO;
import com.domain.BaseEntity;

public abstract class BaseMapper<E extends BaseEntity, D extends BaseEntityDTO> implements IMapper<E, D> {

    /**
     * Convert Entity to DTO
     */
    public D toDTO(E entity) {
        if (entity == null) {
            return null;
        }
        D dto = createDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setCreationDate(entity.getCreationDate());
        return dto;
    }

    /**
     * Convert DTO to Entity
     */
    public E toEntity(D dto) {
        if (dto == null) {
            return null;
        }
        E entity = createEntity();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        if (dto.getCreationDate() != null)
            entity.setCreationDate(dto.getCreationDate());
        return entity;
    }

    /**
     * Each subclass must define how to instantiate its DTO and Entity.
     */
    protected abstract D createDTO();

    protected abstract E createEntity();
}
