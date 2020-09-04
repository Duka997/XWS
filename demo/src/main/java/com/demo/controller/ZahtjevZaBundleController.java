package com.demo.controller;

import com.demo.service.ZahtjevZaBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
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
