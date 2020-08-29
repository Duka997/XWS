package com.demo.controller;

import com.demo.dto.OcjenaDTO;
import com.demo.service.OcjenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ocjena")
public class OcjenaController {

    @Autowired
    private OcjenaService ocjenaService;

    @PostMapping()
    private ResponseEntity<?> kreirajOcjenu(@RequestBody OcjenaDTO ocenaDTO){
        return this.ocjenaService.kreirajOcjenu(ocenaDTO);
    }

    @GetMapping(value = "/vozilo/{voziloId}")
    private ResponseEntity<?> getOcjeneZaAuto(@PathVariable Long voziloId){
        return this.ocjenaService.getOcjeneZaAuto(voziloId);
    }
}
