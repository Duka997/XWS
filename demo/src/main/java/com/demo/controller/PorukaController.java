package com.demo.controller;

import com.demo.dto.PorukaDTO;
import com.demo.service.PorukaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/poruka")
public class PorukaController {

    @Autowired
    private PorukaService porukaService;

    @GetMapping(value = "/primljene/{Id}")
   // @PreAuthorize("hasRole('ROLE_AGENT') || hasRole('ROLE_USER')")
    public List<PorukaDTO> getMojePoruke(@PathVariable("Id") Long Id){
        return porukaService.getMojePoruke(Id);
    }

    @GetMapping(value = "/poslate/{Id}")
    @PreAuthorize("hasRole('ROLE_AGENT') || hasRole('ROLE_USER')")
    public List<PorukaDTO> getPoslatePoruke(@PathVariable("Id") Long Id){
        return porukaService.getPoslatePoruke(Id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_AGENT') || hasRole('ROLE_USER')")
    public void posaljiPoruku(@RequestBody PorukaDTO porukaDTO) {
        porukaService.posaljiPoruku(porukaDTO);
    }


}
