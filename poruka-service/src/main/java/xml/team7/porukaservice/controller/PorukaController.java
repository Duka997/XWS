package xml.team7.porukaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xml.team7.porukaservice.dto.PorukaDTO;
import xml.team7.porukaservice.service.PorukaService;

import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/poruka")
public class PorukaController {

    @Autowired
    private PorukaService porukaService;

    @GetMapping(value = "/primljene/{Id}")
    public List<PorukaDTO> getMojePoruke(@PathVariable("Id") Long Id){
        return porukaService.getMojePoruke(Id);
    }

    @GetMapping(value = "/poslate/{Id}")
    public List<PorukaDTO> getPoslatePoruke(@PathVariable("Id") Long Id){
        return porukaService.getPoslatePoruke(Id);
    }

    @PostMapping
    public void posaljiPoruku(@RequestBody PorukaDTO porukaDTO) {
        porukaService.posaljiPoruku(porukaDTO);
    }


}
