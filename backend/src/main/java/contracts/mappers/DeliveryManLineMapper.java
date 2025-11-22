package contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.DeliveryManRegionLineDTO;
import com.contracts.dtos.RegionDTO;
import com.contracts.mappers.IMapper;
import com.domain.DeliveryManRegionLine;
import com.domain.Region;

public class DeliveryManLineMapper implements IMapper<DeliveryManRegionLine, DeliveryManRegionLineDTO> {
    @Override
    public DeliveryManRegionLineDTO toDTO(DeliveryManRegionLine entity) {
        DeliveryManRegionLineDTO dto = new DeliveryManRegionLineDTO();
        dto.setId(entity.getId());
        dto.setRegion((RegionDTO) MapperUtil.fetchMapper(Region.class).toDTO(entity.getRegion()));
        return dto;
    }

    @Override
    public DeliveryManRegionLine toEntity(DeliveryManRegionLineDTO dto) {
        DeliveryManRegionLine line = new DeliveryManRegionLine();
        line.setId(dto.getId());
        line.setRegion((Region) MapperUtil.fetchMapper(Region.class).toEntity(dto.getRegion()));
        return line;
    }
}
