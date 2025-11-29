package com.contracts.mappers;

import com.contracts.dtos.RegionDTO;
import com.domain.Region;

public class RegionMapper extends MasterFileMapper<Region, RegionDTO> {

    @Override
    public RegionDTO createDTO() {
        return new RegionDTO();
    }

    @Override
    public Region createEntity() {
        return new Region();
    }
}
