package com.contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.AttachmentDTO;
import com.contracts.dtos.MemberDTO;
import com.contracts.dtos.OrderDTO;
import com.domain.Attachment;
import com.domain.Member;
import com.domain.MemberOrder;

public class OrderMapper extends BaseMapper<MemberOrder, OrderDTO> {

    @Override
    public OrderDTO createDTO() {
        return new OrderDTO();
    }

    @Override
    public MemberOrder createEntity() {
        return new MemberOrder();
    }

    @Override
    public OrderDTO toDTO(MemberOrder entity) {
        if (entity == null) return null;
        OrderDTO dto = super.toDTO(entity);
        dto.setOrderCouponsOnly(entity.getOrderCouponsOnly());
        dto.setCouponsCount(entity.getCouponsCount());
        dto.setRemarks(entity.getRemarks());
        if (entity.getMember() != null)
            dto.setMember((MemberDTO) MapperUtil.fetchMapper(Member.class).toDTO(entity.getMember()));
        IMapper<Attachment, AttachmentDTO> attachmentMapper = MapperUtil.fetchMapper(Attachment.class);
        if (entity.getAttachment1() != null)
            dto.setAttachment1(attachmentMapper.toDTO(entity.getAttachment1()));
        if (entity.getAttachment2() != null)
            dto.setAttachment2(attachmentMapper.toDTO(entity.getAttachment2()));
        if (entity.getAttachment3() != null)
            dto.setAttachment3(attachmentMapper.toDTO(entity.getAttachment3()));
        return dto;
    }

    @Override
    public MemberOrder toEntity(OrderDTO dto) {
        if (dto == null) return null;
        MemberOrder entity = super.toEntity(dto);
        entity.setOrderCouponsOnly(dto.getOrderCouponsOnly());
        entity.setCouponsCount(dto.getCouponsCount());
        entity.setRemarks(dto.getRemarks());
        if (dto.getMember() != null)
            entity.setMember((Member) MapperUtil.fetchMapper(Member.class).toEntity(dto.getMember()));
        IMapper<Attachment, AttachmentDTO> attachmentMapper = MapperUtil.fetchMapper(Attachment.class);
        if (dto.getAttachment1() != null)
            entity.setAttachment1(attachmentMapper.toEntity(dto.getAttachment1()));
        if (dto.getAttachment2() != null)
            entity.setAttachment2(attachmentMapper.toEntity(dto.getAttachment2()));
        if (dto.getAttachment3() != null)
            entity.setAttachment3(attachmentMapper.toEntity(dto.getAttachment3()));
        return entity;
    }
}
