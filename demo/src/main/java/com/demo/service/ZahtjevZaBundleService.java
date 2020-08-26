package com.demo.service;

import com.demo.dto.BundleDTO;
import com.demo.dto.ZahtjevDTO;
import com.demo.model.BundleZahtjev;
import com.demo.model.StatusZahtjeva;
import com.demo.model.ZahtjevZaIznajmljivanje;
import com.demo.repository.ZahtjevZaBundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ZahtjevZaBundleService {

    @Autowired
    private ZahtjevZaIznajmljivanjeService zahtjevZaIznajmljivanjeService;

    @Autowired
    private ZahtjevZaBundleRepository zahtjevZaBundleRepository;

    public void newBundles(List<BundleDTO> bundles) {
        for (BundleDTO bundle : bundles) {
            BundleZahtjev rentBundle = new BundleZahtjev();
            rentBundle.setStatusZahtjeva(StatusZahtjeva.PENDING);
            rentBundle.setZahtjevi(new HashSet<>());
            for (ZahtjevDTO request : bundle.getRequests()) {
                ZahtjevZaIznajmljivanje rentRequest = this.zahtjevZaIznajmljivanjeService.newRequest(request, rentBundle);
                rentBundle.getZahtjevi().add(rentRequest);
            }
            this.zahtjevZaBundleRepository.save(rentBundle);
        }
    }
}
