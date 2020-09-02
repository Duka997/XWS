package xml.team7.pretragaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.pretragaservice.dto.MarkaAutomobilaDTO;
import xml.team7.pretragaservice.service.MarkaAutomobilaService;

import java.nio.file.AccessDeniedException;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/marka")
public class MarkaAutomobilaController {
    @Autowired
    private MarkaAutomobilaService markaAutomobilaService;

    @GetMapping("/get")
    public ResponseEntity<?> getMarke() {
        return markaAutomobilaService.getMarke();
    }

    @GetMapping(value = "/models/{markId}")
    public ResponseEntity<?> getAllModels(@PathVariable Long markId) {
        return this.markaAutomobilaService.getAllModels(markId);
    }
}
