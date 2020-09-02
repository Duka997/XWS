package xml.team7.pretragaservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.pretragaservice.dto.PretragaDTO;
import xml.team7.pretragaservice.service.OglasService;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping(value = "/api/oglas")
public class OglasController {

    @Autowired
    private OglasService oglasService;

    @PostMapping(value = "/pretraga/{page}/{sort}")
    public ResponseEntity<?> pretraziOglase(@RequestBody PretragaDTO pretraga, @PathVariable int page, @PathVariable String sort) {
        return this.oglasService.pretraziOglase(pretraga,page, sort);
    }

    @GetMapping( value = "/mjesta")
    public ResponseEntity<?> pretraziMjesta() {
        return this.oglasService.pretraziMjesta();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneOglasById(@PathVariable Long id) {
        return this.oglasService.getOneOglasById(id);
    }
}
