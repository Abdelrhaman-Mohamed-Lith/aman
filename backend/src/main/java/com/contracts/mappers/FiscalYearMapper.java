package com.contracts.mappers;

import com.contracts.dtos.FiscalYearDTO;
import com.domain.FiscalYear;

public class FiscalYearMapper extends MasterFileMapper<FiscalYear, FiscalYearDTO> {

    @Override
    public FiscalYear toEntity(FiscalYearDTO dto) {
        if (dto == null) return null;
        FiscalYear entity = super.toEntity(dto);
        entity.setFromDate(dto.getFromDate());
        entity.setToDate(dto.getToDate());
        return entity;
    }

    @Override
    public FiscalYearDTO toDTO(FiscalYear entity) {
        if (entity == null) return null;
        FiscalYearDTO dto = super.toDTO(entity);
        dto.setFromDate(entity.getFromDate());
        dto.setToDate(entity.getToDate());
        return dto;
    }

    @Override
    public FiscalYearDTO createDTO() {
        return new FiscalYearDTO();
    }

    @Override
    public FiscalYear createEntity() {
        return new FiscalYear();
    }
}
