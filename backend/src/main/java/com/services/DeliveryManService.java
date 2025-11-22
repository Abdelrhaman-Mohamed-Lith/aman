package com.services;

import com.Persistence.Persister;
import com.contracts.dtos.DeliveryManDTO;
import com.contracts.mappers.DeliveryManMapper;
import com.contracts.mappers.DeliveryManMapper;
import com.domain.DeliveryMan;
import com.domain.DeliveryMan;
import com.utilities.IEntity;
import com.utilities.ObjectChecker;
import com.utilities.Result;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryManService {

    private final DeliveryManMapper mapper = new DeliveryManMapper();

    @Transactional
    public DeliveryManDTO create(DeliveryManDTO dto) {
        DeliveryMan entity = mapper.toEntity(dto);
        Result result = Persister.saveOrUpdate(entity);
        if (result.isFailed()) throw new RuntimeException(result.getMessage());
        return mapper.toDTO(entity);
    }

    public List<DeliveryManDTO> getAll() {
        return Persister.list(DeliveryMan.class).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public DeliveryManDTO getById(Long id) {
        return mapper.toDTO(Persister.findById(DeliveryMan.class, id));
    }

    @Transactional
    public DeliveryManDTO update(Long id, DeliveryManDTO dto) {
        DeliveryMan existing = Persister.findById(DeliveryMan.class, id);
        DeliveryMan entity = mapper.toEntity(dto);
        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setPhone1(dto.getPhone1());
        existing.setPhone2(dto.getPhone2());
        existing.getRegions().clear();
        existing.getRegions().addAll(entity.getRegions());
        Result result = Persister.saveOrUpdate(existing);
        if (result.isFailed()) throw new RuntimeException(result.getMessage());
        return mapper.toDTO(existing);
    }

    public void delete(Long id) {
        IEntity entity = Persister.findById(DeliveryMan.class, id);
        Persister.remove(entity);
    }

    public List<DeliveryMan> importExcel( MultipartFile file) {
        List<DeliveryMan> DeliveryMans = new ArrayList<>();
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            // Skip header row → start at row 1
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Double code = row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                DeliveryMan DeliveryMan = new DeliveryMan();
                DeliveryMan.setCode(ObjectChecker.toStringOrEmpty(code));
                DeliveryMan.setName(name);
                DeliveryMans.add(DeliveryMan);
            }
            for (DeliveryMan DeliveryMan : DeliveryMans) {

                Persister.saveOrUpdate(DeliveryMan);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to import Excel file", e);
        }

        return DeliveryMans;
    }
}
