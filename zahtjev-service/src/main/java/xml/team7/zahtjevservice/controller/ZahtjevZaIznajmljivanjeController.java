package xml.team7.zahtjevservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.zahtjevservice.service.ZahtjevZaIznajmljivanjeService;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping(value = "/api/request")
public class ZahtjevZaIznajmljivanjeController {

    @Autowired
    private ZahtjevZaIznajmljivanjeService zahtjevZaIznajmljivanjeService;

    @GetMapping(value = "/getRequests/{userId}")
    public ResponseEntity<?> getAds(@PathVariable Long userId) {
        return this.zahtjevZaIznajmljivanjeService.getAll(userId);
    }

    @GetMapping(value = "/getRequestsHistory/{userId}")
    public ResponseEntity<?> getRequestsHistory(@PathVariable Long userId) {
        return this.zahtjevZaIznajmljivanjeService.getAllHistory(userId);
    }

    @PostMapping(value = "/accept/{id}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long id) {
        return this.zahtjevZaIznajmljivanjeService.acceptRequest(id);
    }

    @PostMapping(value = "/cancel/{id}")
    public ResponseEntity<?> cancelRequest(@PathVariable Long id) {
        return this.zahtjevZaIznajmljivanjeService.cancelRequest(id);
    }
}
