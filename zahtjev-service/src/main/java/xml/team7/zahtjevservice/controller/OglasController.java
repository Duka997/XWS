package xml.team7.zahtjevservice.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.zahtjevservice.dto.KorpaDTO;
import xml.team7.zahtjevservice.dto.OglasUKorpiDTO;
import xml.team7.zahtjevservice.service.OglasService;
import xml.team7.zahtjevservice.service.ZahtjevZaIznajmljivanjeService;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping(value = "/api/oglas")
public class OglasController {

    @Autowired
    private OglasService oglasService;

    @Autowired
    private ZahtjevZaIznajmljivanjeService zahtjevZaIznajmljivanjeService;

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

