package com.demo.service;

import com.demo.dto.KorpaDTO;
import com.demo.dto.ZahtjevDTO;
import com.demo.model.*;
import com.demo.repository.UserRepository;
import com.demo.repository.ZahtjevZaIznajmljivanjeRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtjevZaIznajmljivanjeService {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ZahtjevZaIznajmljivanjeRepository zahtjevZaIznajmljivanjeRepository;

    @Autowired
    private ZahtjevZaBundleService zahtjevZaBundleService;

    @Autowired
    private OglasService oglasService;

    @Autowired
    private UserRepository userRepository;

    public List<ZahtjevDTO> getRequestDTOS(List<ZahtjevZaIznajmljivanje> requests) {
        List<ZahtjevDTO> retVal = new ArrayList<>();
        for (ZahtjevZaIznajmljivanje request : requests) {
            retVal.add(ZahtjevDTO.builder()
                    .id(request.getId())
                    .doo(request.getDoo())
                    .od(request.getOd())
                    .mjestoPreuzimanja(request.getMjestoPreuzimanja())
                    .oglasId(request.getOglas().getId())
                    .status(String.valueOf(request.getStatus()))
                    .userId(request.getUser().getId())
                    .bundleId(request.getBundle() != null ? request.getBundle().getId() : -1)
                    .build());
        }
        return retVal;
    }

    public ResponseEntity<?> newRequests(KorpaDTO shoppingCart, Long userId) {
        this.zahtjevZaBundleService.newBundles(shoppingCart.getBundles(), userId);

        for (ZahtjevDTO request : shoppingCart.getRequests()) {
            ZahtjevZaIznajmljivanje rentRequest = newRequest(request, null, userId);
            rentRequest = this.zahtjevZaIznajmljivanjeRepository.save(rentRequest);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ZahtjevZaIznajmljivanje newRequest(ZahtjevDTO request, BundleZahtjev bundle, Long userId) {
        Oglas ad = this.oglasService.getAdById(request.getOglasId());
        User user = this.userService.getUserById(userId);

        ZahtjevZaIznajmljivanje rentRequest = new ZahtjevZaIznajmljivanje();
        rentRequest.setMjestoPreuzimanja(request.getMjestoPreuzimanja());
        rentRequest.setOd(request.getOd());
        rentRequest.setDoo(request.getDoo());
        rentRequest.setDatumKreiranja(new DateTime());
        rentRequest.setOglas(ad);
        rentRequest.setStatus(StatusZahtjeva.PENDING);
        rentRequest.setBundle(bundle);

        rentRequest.setUser(user);

        return rentRequest;
    }


}
