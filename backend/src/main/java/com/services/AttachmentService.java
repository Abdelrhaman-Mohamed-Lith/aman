package com.services;

import com.Persistence.Persister;
import com.contracts.dtos.AttachmentDTO;
import com.contracts.mappers.AttachmentMapper;
import com.domain.Attachment;
import org.springframework.stereotype.Service;
import com.utilities.IEntity;
import com.utilities.Result;

import java.util.List;

@Service
public class AttachmentService {

    private final AttachmentMapper mapper = new AttachmentMapper();

    public AttachmentDTO create(AttachmentDTO dto) {
        Attachment entity = mapper.toEntity(dto);
        Result result = Persister.saveOrUpdate(entity);
        return mapper.toDTO(entity);
    }

    public List<AttachmentDTO> getAll() {
        return Persister.list(Attachment.class).stream().map(mapper::toDTO)
                .toList();
    }

    public AttachmentDTO getById(Long id) {
        return mapper.toDTO(Persister.findById(Attachment.class, id));
    }

    public AttachmentDTO update(Long id, AttachmentDTO dto) {
        Attachment existing = Persister.findById(Attachment.class, id);
        existing.setFileName(dto.getFileName());
        existing.setFileType(dto.getFileType());
        existing.setFileSize(dto.getFileSize());
        existing.setStoragePath(dto.getStoragePath());
        Result result = Persister.saveOrUpdate(existing);
        return mapper.toDTO(existing);
    }

    public void delete(Long id) {
        IEntity entity = Persister.findById(Attachment.class, id);
        Persister.remove(entity);
    }
}
