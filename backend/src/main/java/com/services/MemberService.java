package com.services;

import com.Persistence.Persister;
import com.contracts.dtos.MemberDTO;
import com.contracts.mappers.MemberMapper;
import com.domain.Governorate;
import com.domain.Member;
import com.domain.Syndicate;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utilities.IEntity;
import com.utilities.ObjectChecker;
import com.utilities.Result;

import java.util.List;

@Service
public class MemberService {

    private final MemberMapper mapper = new MemberMapper();

    @Transactional
    public MemberDTO create(MemberDTO dto) {
        Member entity = mapper.toEntity(dto);
        entity.setCode(entity.getRegistrationNumber() + "_" + calcSyndicatePrefix(entity.getSyndicate()));
        Result result = Persister.saveOrUpdate(entity);
        if (result.isFailed())
            throw new RuntimeException(result.getMessage());
        return mapper.toDTO(entity);
    }

    private String calcSyndicatePrefix(Syndicate syndicate) {
        if (ObjectChecker.areEqual(syndicate, Syndicate.Medical))
            return "1";
        if (ObjectChecker.areEqual(syndicate, Syndicate.Pharmaceutical))
            return "2";
        if (ObjectChecker.areEqual(syndicate, Syndicate.Dentists))
            return "3";
        return "4";
    }

    public List<MemberDTO> getAll() {
        return Persister.list(Member.class).stream().map(mapper::toDTO).toList();
    }

    public MemberDTO getById(Long id) {
        return mapper.toDTO(Persister.findById(Member.class, id));
    }

    @Transactional
    public MemberDTO update(Long id, MemberDTO dto) {
        Member existing = mapper.toEntity(dto)/* Persister.findById(Member.class, id)*/;
//        existing.setCode(dto.getCode());
//        existing.setRegistrationNumber(dto.getRegistrationNumber());
//        existing.setMedicalCardNumber(dto.getMedicalCardNumber());
//        if (ObjectChecker.isNotEmptyOrZeroOrNull(dto.getSyndicate()))
//            existing.setSyndicate(Syndicate.valueOf(dto.getSyndicate()));
//        existing.setWhatsappNumber(dto.getWhatsappNumber());
//        existing.setPhone1(dto.getPhone1());
//        existing.setPhone2(dto.getPhone2());
//        if (ObjectChecker.isNotEmptyOrZeroOrNull(dto.getGovernorate()))
//            existing.setGovernorate(Governorate.valueOf(dto.getGovernorate()));
//        existing.setAddress(dto.getAddress());
//        existing.setRegion(dto.getRegion() != null ? mapper.toEntity(dto).getRegion() : null);
//        existing.setRemarks(dto.getRemarks());
        Result result = Persister.saveOrUpdate(existing);
        if (result.isFailed())
            throw new RuntimeException(result.getMessage());
        return mapper.toDTO(existing);
    }

    public void delete(Long id) {
        IEntity entity = Persister.findById(Member.class, id);
        Persister.remove(entity);
    }
}
