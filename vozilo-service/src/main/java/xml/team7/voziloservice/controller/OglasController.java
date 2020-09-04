package xml.team7.voziloservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.voziloservice.dto.OglasDTO;
import xml.team7.voziloservice.service.OglasService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/oglas")
public class OglasController {

    @Autowired
    private OglasService oglasService;


    
    @PostMapping("/dodaj")
    public ResponseEntity<?> addNew(@RequestBody OglasDTO oglasDTO) {
        return this.oglasService.noviOglas(oglasDTO);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> getAds() {
        return this.oglasService.getAds();
    }



    @GetMapping( value = "/mjesta")
    public ResponseEntity<?> pretraziMjesta() {
        return this.oglasService.pretraziMjesta();
    }

}

