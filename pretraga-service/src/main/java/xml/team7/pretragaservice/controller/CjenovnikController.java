package xml.team7.pretragaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.pretragaservice.dto.CjenovnikDTO;
import xml.team7.pretragaservice.service.CjenovnikService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/cjenovnik")
public class CjenovnikController {
    @Autowired
    private CjenovnikService cjenovnikService;

    @PostMapping("/dodaj")
    private ResponseEntity<?> dodajCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO){
        return this.cjenovnikService.dodajCjenovnik(cjenovnikDTO);
    }

    @PostMapping ("/izmjeni")
    private ResponseEntity<?> izmjeniCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO){
        return this.cjenovnikService.izmjeniCjenovnik(cjenovnikDTO);
    }
}
