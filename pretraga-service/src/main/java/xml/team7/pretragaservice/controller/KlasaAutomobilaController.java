package xml.team7.pretragaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.pretragaservice.dto.KlasaAutomobilaDTO;
import xml.team7.pretragaservice.service.KlasaAutomobilaService;

import java.nio.file.AccessDeniedException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/klasa")
public class KlasaAutomobilaController {

    @Autowired
    private KlasaAutomobilaService klasaAutomobilaService;

    @GetMapping("/get")
    public ResponseEntity<?> getKlase() {
        return klasaAutomobilaService.getKlase();
    }

    @PostMapping("/dodaj")
    public ResponseEntity add(@RequestBody KlasaAutomobilaDTO mDTO) {
        klasaAutomobilaService.save(mDTO);
        return ResponseEntity.ok().build();
    }
}
