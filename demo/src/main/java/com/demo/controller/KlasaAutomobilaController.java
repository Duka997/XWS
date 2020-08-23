package com.demo.controller;

import com.demo.dto.KlasaAutomobilaDTO;
import com.demo.model.KlasaAutomobila;
import com.demo.service.KlasaAutomobilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/klasa")
public class KlasaAutomobilaController {

    @Autowired
    private KlasaAutomobilaService klasaAutomobilaService;


    @PostMapping("/dodaj")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity add(@RequestBody KlasaAutomobilaDTO mDTO) {
        klasaAutomobilaService.save(mDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> loadAll() {
        return this.klasaAutomobilaService.findAll();
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return this.klasaAutomobilaService.findAll();
    }


    @PutMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KlasaAutomobila> edit(@RequestBody KlasaAutomobilaDTO klasaAutomobilaDTO) throws AccessDeniedException {
        return new ResponseEntity<>(this.klasaAutomobilaService.edit(klasaAutomobilaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) throws AccessDeniedException {
        klasaAutomobilaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
