package com.demo.controller;


import com.demo.dto.KorpaDTO;
import com.demo.dto.OglasDTO;
import com.demo.dto.OglasUKorpiDTO;
import com.demo.model.Oglas;
import com.demo.service.OglasService;
import com.demo.service.ZahtjevZaIznajmljivanjeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/oglas")
public class OglasController {

    @Autowired
    private OglasService oglasService;

    @Autowired
    private ZahtjevZaIznajmljivanjeService zahtjevZaIznajmljivanjeService;

    private ModelMapper modelMapper;

    @GetMapping(value = "/get")
    public ResponseEntity<?> getAds() {
        return this.oglasService.getAds();
    }

    @PostMapping("/dodajUKorpu/{userId}")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity add(@RequestBody OglasUKorpiDTO oglasUKorpiDTO, @PathVariable Long userId) {
        oglasService.saveInCart(oglasUKorpiDTO, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getCartAds/{userId}")
    public ResponseEntity<?> getCartAds(@PathVariable Long userId) {
        return this.oglasService.getCartAds(userId);
    }

    @PostMapping(value = "/remove/{id}")
    public ResponseEntity<?> removeAdFromCart(@PathVariable Long id) {
        return this.oglasService.removeAdFromCart(id);
    }

    @PostMapping(value = "/request/{userId}")
    public ResponseEntity<?> newRequests(@PathVariable Long userId, @RequestBody KorpaDTO shoppingCart) {
        return this.zahtjevZaIznajmljivanjeService.newRequests(shoppingCart, userId);
    }
}
