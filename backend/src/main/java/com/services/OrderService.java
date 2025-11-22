package com.services;

import com.Persistence.Persister;
import com.contracts.dtos.OrderDTO;
import com.contracts.mappers.OrderMapper;
import com.domain.MemberOrder;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utilities.IEntity;
import com.utilities.Result;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper mapper = new OrderMapper();

    @Transactional
    public OrderDTO create(OrderDTO dto) {
        MemberOrder entity = mapper.toEntity(dto);
        Result result = Persister.saveOrUpdate(entity);
        return mapper.toDTO(entity);
    }

    public List<OrderDTO> getAll() {
        return Persister.list(MemberOrder.class).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public OrderDTO getById(Long id) {
        return mapper.toDTO(Persister.findById(MemberOrder.class, id));
    }

    @Transactional
    public OrderDTO update(Long id, OrderDTO dto) {
        MemberOrder existing = Persister.findById(MemberOrder.class, id);
        existing.setCards(dto.getCards());
        existing.setCouponsCount(dto.getCouponsCount());
        existing.setMember(dto.getMember() != null ? mapper.toEntity(dto).getMember() : null);
        existing.setAttachment1(dto.getAttachment1() != null ? mapper.toEntity(dto).getAttachment1() : null);
        existing.setAttachment2(dto.getAttachment2() != null ? mapper.toEntity(dto).getAttachment2() : null);
        existing.setAttachment3(dto.getAttachment3() != null ? mapper.toEntity(dto).getAttachment3() : null);
        Result result = Persister.saveOrUpdate(existing);
        return mapper.toDTO(existing);
    }

    public void delete(Long id) {
        IEntity entity = Persister.findById(MemberOrder.class, id);
        Persister.remove(entity);
    }
}
