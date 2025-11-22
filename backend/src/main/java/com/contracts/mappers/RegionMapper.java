package com.contracts.mappers;

import com.contracts.dtos.RegionDTO;
import com.domain.Region;

public class RegionMapper extends MasterFileMapper<Region, RegionDTO> {

    @Override
    protected RegionDTO createDTO() {
        return new RegionDTO();
    }

    @Override
    protected Region createEntity() {
        return new Region();
    }
}
