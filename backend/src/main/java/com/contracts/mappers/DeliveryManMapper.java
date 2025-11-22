package com.contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.*;
import com.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryManMapper extends MasterFileMapper<DeliveryMan, DeliveryManDTO> {

    public DeliveryManDTO toDTO(DeliveryMan entity) {
        if (entity == null) return null;
        DeliveryManDTO dto =  super.toDTO(entity);
        dto.setPhone1(entity.getPhone1());
        dto.setPhone2(entity.getPhone2());
        IMapper<DeliveryManRegionLine, DeliveryManRegionLineDTO> mapper = MapperUtil.fetchMapper(DeliveryManRegionLine.class);
        if (entity.getRegions() != null) {
            List<DeliveryManRegionLineDTO> lineDTOs = entity.getRegions().stream()
                    .map(line -> {
                        DeliveryManRegionLineDTO lineDTO = mapper.toDTO(line);
                        lineDTO.setDeliveryMan(dto);
                        return lineDTO;
                    })
                    .collect(Collectors.toList());
            dto.setRegions(lineDTOs);
        }
        return dto;
    }

    public DeliveryMan toEntity(DeliveryManDTO dto) {
        if (dto == null) return null;

        DeliveryMan entity = super.toEntity(dto);
        entity.setPhone1(dto.getPhone1());
        entity.setPhone2(dto.getPhone2());
        IMapper<DeliveryManRegionLine, DeliveryManRegionLineDTO> mapper = MapperUtil.fetchMapper(DeliveryManRegionLine.class);
        if (dto.getRegions() != null) {
            List<DeliveryManRegionLine> lines = dto.getRegions().stream()
                    .map(lineDTO -> {
                        DeliveryManRegionLine line = mapper.toEntity(lineDTO);
                        line.setDeliveryMan(entity);
                        return line;
                    })
                    .collect(Collectors.toList());
            entity.setRegions(lines);
        }
        return entity;
    }

    @Override
    protected DeliveryManDTO createDTO() {
        return new DeliveryManDTO();
    }

    @Override
    protected DeliveryMan createEntity() {
        return new DeliveryMan();
    }
}
