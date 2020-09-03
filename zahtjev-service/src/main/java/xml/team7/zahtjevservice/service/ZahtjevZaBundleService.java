package xml.team7.zahtjevservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.dto.BundleDTO;
import xml.team7.zahtjevservice.dto.ZahtjevDTO;
import xml.team7.zahtjevservice.exception.InvalidOperationException;
import xml.team7.zahtjevservice.exception.NotFoundException;
import xml.team7.zahtjevservice.model.BundleZahtjev;
import xml.team7.zahtjevservice.model.StatusZahtjeva;
import xml.team7.zahtjevservice.model.ZahtjevZaIznajmljivanje;
import xml.team7.zahtjevservice.repository.ZahtjevZaBundleRepository;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class ZahtjevZaBundleService {

    @Autowired
    private ZahtjevZaIznajmljivanjeService zahtjevZaIznajmljivanjeService;

    @Autowired
    private ZahtjevZaBundleRepository zahtjevZaBundleRepository;

    public void newBundles(List<BundleDTO> bundles, Long userId) {
        for (BundleDTO bundle : bundles) {
            BundleZahtjev rentBundle = new BundleZahtjev();
            rentBundle.setStatusZahtjeva(StatusZahtjeva.PENDING);
            rentBundle.setZahtjevi(new HashSet<>());
            for (ZahtjevDTO request : bundle.getRequests()) {
                ZahtjevZaIznajmljivanje rentRequest = this.zahtjevZaIznajmljivanjeService.newRequest(request, rentBundle, userId);
                rentBundle.getZahtjevi().add(rentRequest);
            }
            this.zahtjevZaBundleRepository.save(rentBundle);
        }
    }

    public ResponseEntity<?> acceptBundle(Long bundleId) {
        BundleZahtjev rentBundle = this.zahtjevZaBundleRepository.findById(bundleId).orElseThrow(() -> new NotFoundException("Bundle with given id was not found"));
        if (!rentBundle.getStatusZahtjeva().equals(StatusZahtjeva.PENDING))
            throw new InvalidOperationException("Rent request cannot be accepted, it's not in pending state, but has status: " + rentBundle.getStatusZahtjeva());

        rentBundle.setStatusZahtjeva(StatusZahtjeva.RESERVED);
        rentBundle.setStatusZahtjeva(StatusZahtjeva.PAID);

        for (ZahtjevZaIznajmljivanje request: rentBundle.getZahtjevi()) {
            if (request.getStatus().equals(StatusZahtjeva.PENDING))
                this.zahtjevZaIznajmljivanjeService.acceptRequest2(request);
        }

        this.zahtjevZaBundleRepository.save(rentBundle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> cancelBundle(Long bundleId) {
        BundleZahtjev bundle = this.zahtjevZaBundleRepository.findById(bundleId).orElseThrow(() -> new NotFoundException("Bundle with given id was not found"));
        if (!bundle.getStatusZahtjeva().equals(StatusZahtjeva.PENDING))
            throw new InvalidOperationException("Rent bundle cannot be refused, it's not in pending state, but has status: " + bundle.getStatusZahtjeva());
        bundle.setStatusZahtjeva(StatusZahtjeva.CANCELED);

        for (ZahtjevZaIznajmljivanje request: bundle.getZahtjevi()) {
            request.setStatus(StatusZahtjeva.CANCELED);
        }
        this.zahtjevZaBundleRepository.save(bundle);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
