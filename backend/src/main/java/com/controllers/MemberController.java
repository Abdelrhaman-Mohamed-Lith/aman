package com.controllers;

import com.contracts.dtos.MemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.services.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO request) {
        MemberDTO created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) {
        MemberDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getMembers() {
        List<MemberDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @RequestBody MemberDTO request) {
        MemberDTO updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
