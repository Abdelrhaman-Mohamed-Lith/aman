package com.controllers;

import com.contracts.dtos.DeliveryManDTO;
import com.domain.DeliveryMan;
import com.services.DeliveryManService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/delivery-men")
public class DeliveryManController {

    private final DeliveryManService service;

    public DeliveryManController(DeliveryManService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeliveryManDTO> createDeliveryMan(@RequestBody DeliveryManDTO request) {
        DeliveryManDTO created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryManDTO> getDeliveryMan(@PathVariable(name = "id") Long id) {
        DeliveryManDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryManDTO>> getDeliveryMans() {
        List<DeliveryManDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryManDTO> updateDeliveryMan(@PathVariable(name = "id") Long id, @RequestBody DeliveryManDTO request) {
        DeliveryManDTO updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryMan(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/import")
    public ResponseEntity<?> importDeliveryMans(@RequestParam("file") MultipartFile file) {
        List<DeliveryMan> imported = service.importExcel(file);
        return ResponseEntity.ok(imported);
    }
}
