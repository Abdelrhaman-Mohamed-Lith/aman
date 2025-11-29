package com.contracts.mappers;

import com.contracts.dtos.MemberDTO;
import com.domain.Member;
import com.domain.Syndicate;
import com.domain.Governorate;
import com.utilities.ObjectChecker;

public class MemberMapper extends MasterFileMapper<Member, MemberDTO> {
    @Override
    public MemberDTO createDTO() {
        return new MemberDTO();
    }

    @Override
    public Member createEntity() {
        return new Member();
    }

    @Override
    public MemberDTO toDTO(Member entity) {
        if (entity == null) return null;
        MemberDTO dto = super.toDTO(entity);
        dto.setRegistrationNumber(entity.getRegistrationNumber());
        dto.setMedicalCardNumber(entity.getMedicalCardNumber());
        dto.setSyndicate(entity.getSyndicate() != null ? entity.getSyndicate().name() : null);
        dto.setWhatsappNumber(entity.getWhatsappNumber());
        dto.setPhone1(entity.getPhone1());
        dto.setPhone2(entity.getPhone2());
        dto.setGovernorate(entity.getGovernorate() != null ? entity.getGovernorate().name() : null);
        dto.setAddress(entity.getAddress());
        dto.setRemarks(entity.getRemarks());
        if (entity.getRegion() != null)
            dto.setRegion(new RegionMapper().toDTO(entity.getRegion()));
        return dto;
    }

    @Override
    public Member toEntity(MemberDTO dto) {
        if (dto == null) return null;
        Member entity = super.toEntity(dto);
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setMedicalCardNumber(dto.getMedicalCardNumber());
        entity.setWhatsappNumber(dto.getWhatsappNumber());
        entity.setPhone1(dto.getPhone1());
        entity.setPhone2(dto.getPhone2());
        entity.setAddress(dto.getAddress());
        entity.setRemarks(dto.getRemarks());
        if (ObjectChecker.isNotEmptyOrZeroOrNull(dto.getSyndicate()))
            entity.setSyndicate(Syndicate.valueOf(dto.getSyndicate()));
        if (ObjectChecker.isNotEmptyOrZeroOrNull(dto.getGovernorate()))
            entity.setGovernorate(Governorate.valueOf(dto.getGovernorate()));
        if (ObjectChecker.isNotEmptyOrZeroOrNull(dto.getRegion()))
            entity.setRegion(new RegionMapper().toEntity(dto.getRegion()));
        return entity;
    }
}
