package com.contracts;

import com.contracts.mappers.*;
import com.domain.*;
import contracts.mappers.DeliveryManLineMapper;

import java.util.HashMap;
import java.util.Map;

public class MapperUtil {
    private static Map<Class<?>, IMapper<?, ?>> mappers;

    public static <E, D> IMapper<E, D> fetchMapper(Class<?> klass) {
        if (mappers != null)
            return (IMapper<E, D>) mappers.get(klass);
        initMappers();
        return (IMapper<E, D>) mappers.get(klass);
    }

    private static void initMappers() {
        mappers = new HashMap<>();
        mappers.put(Attachment.class, new AttachmentMapper());
        mappers.put(Member.class, new MemberMapper());
        mappers.put(Region.class, new RegionMapper());
        mappers.put(MemberOrder.class, new OrderMapper());
        mappers.put(Sheet.class, new SheetMapper());
        mappers.put(SheetLine.class, new SheetLineMapper());
        mappers.put(DeliveryMan.class, new DeliveryManMapper());
        mappers.put(DeliveryManRegionLine.class, new DeliveryManLineMapper());
    }
}
