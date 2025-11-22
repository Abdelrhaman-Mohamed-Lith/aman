package com.services;

import com.Persistence.Persister;
import com.contracts.dtos.RegionDTO;
import com.contracts.mappers.RegionMapper;
import com.domain.Region;
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
public class RegionService {

    private final RegionMapper mapper = new RegionMapper();

    @Transactional
    public RegionDTO create(RegionDTO dto) {
        Region entity = mapper.toEntity(dto);
        Result result = Persister.saveOrUpdate(entity);
        if (result.isFailed()) throw new RuntimeException(result.getMessage());
        return mapper.toDTO(entity);
    }

    public List<RegionDTO> getAll() {
        return Persister.list(Region.class).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public RegionDTO getById(Long id) {
        return mapper.toDTO(Persister.findById(Region.class, id));
    }

    @Transactional
    public RegionDTO update(Long id, RegionDTO dto) {
        Region existing = Persister.findById(Region.class, id);
        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        Result result = Persister.saveOrUpdate(existing);
        if (result.isFailed()) throw new RuntimeException(result.getMessage());
        return mapper.toDTO(existing);
    }

    public void delete(Long id) {
        IEntity entity = Persister.findById(Region.class, id);
        Persister.remove(entity);
    }

    public List<Region> importExcel( MultipartFile file) {
        List<Region> regions = new ArrayList<>();
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            // Skip header row → start at row 1
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Double code = row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                Region region = new Region();
                region.setCode(ObjectChecker.toStringOrEmpty(code));
                region.setName(name);
                regions.add(region);
            }
            for (Region region : regions) {

                Persister.saveOrUpdate(region);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to import Excel file", e);
        }

        return regions;
    }
}
