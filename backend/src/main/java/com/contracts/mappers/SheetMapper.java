package com.contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.SheetDTO;
import com.contracts.dtos.SheetLineDTO;
import com.domain.Sheet;
import com.domain.SheetLine;
import com.domain.Status;

import java.util.List;
import java.util.stream.Collectors;

public class SheetMapper extends BaseMapper<Sheet, SheetDTO> {
    public SheetDTO toDTO(Sheet entity) {
        if (entity == null) return null;
        SheetDTO dto = new SheetDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setCreationDate(entity.getCreationDate());
        dto.setFromDate(entity.getFromDate());
        dto.setToDate(entity.getToDate());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        IMapper<SheetLine, SheetLineDTO> mapper = MapperUtil.fetchMapper(Sheet.class);
        if (entity.getDetails() != null) {
            List<SheetLineDTO> lineDTOs = entity.getDetails().stream()
                    .map(line -> {
                        SheetLineDTO lineDTO = mapper.toDTO(line);
                        lineDTO.setSheet(dto);
                        return lineDTO;
                    })
                    .collect(Collectors.toList());
            dto.setDetails(lineDTOs);
        }
        return dto;
    }

    public Sheet toEntity(SheetDTO dto) {
        if (dto == null) return null;

        Sheet entity = new Sheet();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setCreationDate(dto.getCreationDate());
        entity.setFromDate(dto.getFromDate());
        entity.setToDate(dto.getToDate());
        entity.setStatus(dto.getStatus() != null ? Status.valueOf(dto.getStatus()) : null);
        IMapper<SheetLine, SheetLineDTO> mapper = MapperUtil.fetchMapper(Sheet.class);
        if (dto.getDetails() != null) {
            List<SheetLine> lines = dto.getDetails().stream()
                    .map(lineDTO -> {
                        SheetLine line = mapper.toEntity(lineDTO);
                        line.setSheet(entity);
                        return line;
                    })
                    .peek(line -> line.setSheet(entity))
                    .collect(Collectors.toList());
            entity.setDetails(lines);
        }
        return entity;
    }

    @Override
    protected SheetDTO createDTO() {
        return new SheetDTO();
    }

    @Override
    protected Sheet createEntity() {
        return new Sheet();
    }
}
