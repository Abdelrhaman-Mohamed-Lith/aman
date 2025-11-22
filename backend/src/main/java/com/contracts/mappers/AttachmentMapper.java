package com.contracts.mappers;

import com.contracts.dtos.AttachmentDTO;
import com.domain.Attachment;

public class AttachmentMapper implements IMapper<Attachment, AttachmentDTO> {

    public AttachmentDTO toDTO(Attachment entity) {
        if (entity == null) return null;

        AttachmentDTO dto = new AttachmentDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getFileName());
        dto.setFileType(entity.getFileType());
        dto.setFileSize(entity.getFileSize());
        dto.setStoragePath(entity.getStoragePath());
        return dto;
    }

    public Attachment toEntity(AttachmentDTO dto) {
        if (dto == null) return null;

        Attachment entity = new Attachment();
        entity.setId(dto.getId());
        entity.setFileName(dto.getFileName());
        entity.setFileType(dto.getFileType());
        entity.setFileSize(dto.getFileSize());
        entity.setStoragePath(dto.getStoragePath());
        return entity;
    }
}
