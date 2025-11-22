package com.services;

import com.Persistence.Persister;
import com.contracts.dtos.SheetDTO;
import com.contracts.mappers.SheetMapper;
import com.domain.Sheet;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utilities.IEntity;
import com.utilities.Result;

import java.util.List;

@Service
public class SheetService {

    private final SheetMapper mapper = new SheetMapper();

    @Transactional
    public SheetDTO create(SheetDTO dto) {
        Sheet entity = mapper.toEntity(dto);
        Result result = Persister.saveOrUpdate(entity);
        return mapper.toDTO(entity);
    }

    public List<SheetDTO> getAll() {
        return Persister.list(Sheet.class).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public SheetDTO getById(Long id) {
        return mapper.toDTO(Persister.findById(Sheet.class, id));
    }

    @Transactional
    public SheetDTO update(Long id, SheetDTO dto) {
        Sheet existing = Persister.findById(Sheet.class, id);
        existing.setFromDate(dto.getFromDate());
        existing.setToDate(dto.getToDate());
        existing.setStatus(mapper.toEntity(dto).getStatus());
        existing.setDetails(mapper.toEntity(dto).getDetails());
        Result result = Persister.saveOrUpdate(existing);
        return mapper.toDTO(existing);
    }

    public void delete(Long id) {
        IEntity entity = Persister.findById(Sheet.class, id);
        Persister.remove(entity);
    }
}
