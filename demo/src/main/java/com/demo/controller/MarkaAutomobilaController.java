package com.demo.controller;

import com.demo.dto.MarkaAutomobilaDTO;
import com.demo.model.MarkaAutomobila;
import com.demo.service.MarkaAutomobilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/marka")
public class MarkaAutomobilaController {
    @Autowired
    private MarkaAutomobilaService markaAutomobilaService;


    @PostMapping("/dodaj")
    public ResponseEntity add(@RequestBody MarkaAutomobilaDTO mDTO) {
        System.out.println("Cao");
        markaAutomobilaService.save(mDTO);
        System.out.println("Caos");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> loadAll() throws AccessDeniedException {
        return this.markaAutomobilaService.getAll();
    }


    @GetMapping()
    public ResponseEntity<?> getAll() {
        return this.markaAutomobilaService.getAll();
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarkaAutomobila> edit(@RequestBody MarkaAutomobilaDTO markaAutomobilaDTO) throws AccessDeniedException {
        return new ResponseEntity<>(this.markaAutomobilaService.edit(markaAutomobilaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) throws AccessDeniedException {
        markaAutomobilaService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Katarina radila(ne brisati)
    @GetMapping(value = "/models/{markId}")
    public ResponseEntity<?> getAllModels(@PathVariable Long markId) {
        return this.markaAutomobilaService.getAllModels(markId);
    }
}
