package com.controllers;

import com.contracts.dtos.SheetDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.services.SheetService;

import java.util.List;

@RestController
@RequestMapping("/sheets")
public class SheetController {

    private final SheetService service;

    public SheetController(SheetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SheetDTO> createSheet(@RequestBody SheetDTO request) {
        SheetDTO created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SheetDTO> getSheet(@PathVariable Long id) {
        SheetDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SheetDTO>> getSheets() {
        List<SheetDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SheetDTO> updateSheet(@PathVariable(name = "id") Long id, @RequestBody SheetDTO request) {
        SheetDTO updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSheet(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
