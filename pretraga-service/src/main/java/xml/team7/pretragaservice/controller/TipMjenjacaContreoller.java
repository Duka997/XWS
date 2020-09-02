package xml.team7.pretragaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.pretragaservice.dto.TipMjenjacaDTO;
import xml.team7.pretragaservice.service.TipMjenjacaService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/tipmjenjaca", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipMjenjacaContreoller {
    @Autowired
    private TipMjenjacaService tipMjenjacaService;


    @GetMapping("/get")
    public ResponseEntity<?> getKlase() {
        return tipMjenjacaService.getMjenjaci();
    }

    @PostMapping("/dodaj")
    public ResponseEntity add(@RequestBody TipMjenjacaDTO mDTO) {
        tipMjenjacaService.save(mDTO);
        return ResponseEntity.ok().build();
    }
}
