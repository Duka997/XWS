package com.demo.controller;

import com.demo.dto.VoziloDTO;
import com.demo.model.Vozilo;
import com.demo.service.VoziloService;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/vozilo")
public class VoziloController {
    @Autowired
    private VoziloService voziloService;

    @PostMapping("/dodaj")
    public ResponseEntity add(@RequestBody VoziloDTO mDTO) throws Base64DecodingException, SQLException, AccessDeniedException {
        voziloService.dodajNovoVozilo(mDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get")
    public ResponseEntity<?> findAllVozila() throws AccessDeniedException {
        return this.voziloService.findAllVozila();
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> findAllVozilaOfUser(@PathVariable Long userId) throws AccessDeniedException {
        return this.voziloService.findAllVozilaOfUser(userId);
    }

    @GetMapping(value = "/statistics/{ownersID}")
    public ResponseEntity<?> getStatistics(@PathVariable Long ownersID){
        return voziloService.getCarStatistics(ownersID);
    }

}
