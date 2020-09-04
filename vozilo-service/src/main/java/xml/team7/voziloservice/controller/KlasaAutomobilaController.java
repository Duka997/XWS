package xml.team7.voziloservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.voziloservice.dto.KlasaAutomobilaDTO;
import xml.team7.voziloservice.model.KlasaAutomobila;
import xml.team7.voziloservice.service.KlasaAutomobilaService;

import java.nio.file.AccessDeniedException;

@CrossOrigin(value = "*")
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
    public ResponseEntity<KlasaAutomobila> edit(@RequestBody KlasaAutomobilaDTO klasaAutomobilaDTO) throws AccessDeniedException {
        return new ResponseEntity<>(this.klasaAutomobilaService.edit(klasaAutomobilaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws AccessDeniedException {
        klasaAutomobilaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
