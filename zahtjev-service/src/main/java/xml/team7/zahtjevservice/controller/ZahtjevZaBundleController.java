package xml.team7.zahtjevservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.zahtjevservice.service.ZahtjevZaBundleService;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping(value = "/api/bundle")
public class ZahtjevZaBundleController {

    @Autowired
    private ZahtjevZaBundleService zahtjevZaBundleService;

    @PostMapping(value = "/accept/{id}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long id) {
        return this.zahtjevZaBundleService.acceptBundle(id);
    }

    @PostMapping(value = "/cancel/{id}")
    public ResponseEntity<?> cancelRequest(@PathVariable Long id) {
        return this.zahtjevZaBundleService.cancelBundle(id);
    }
}
