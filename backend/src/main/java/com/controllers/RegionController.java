package com.controllers;

import com.contracts.dtos.RegionDTO;
import com.domain.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.services.RegionService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionService service;

    public RegionController(RegionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO request) {
        RegionDTO created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getRegion(@PathVariable(name = "id") Long id) {
        RegionDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<RegionDTO>> getRegions() {
        List<RegionDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> updateRegion(@PathVariable(name = "id") Long id, @RequestBody RegionDTO request) {
        RegionDTO updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/import")
    public ResponseEntity<?> importRegions(@RequestParam("file") MultipartFile file) {
        List<Region> imported = service.importExcel(file);
        return ResponseEntity.ok(imported);
    }
}
