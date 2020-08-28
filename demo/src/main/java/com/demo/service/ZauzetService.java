package com.demo.service;

import com.demo.model.ZahtjevZaIznajmljivanje;
import com.demo.model.Zauzet;
import com.demo.repository.ZauzetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZauzetService {

    @Autowired
    private ZauzetRepository zauzetRepository;

    public void saveRequestAsOccupied(ZahtjevZaIznajmljivanje request) {
        Zauzet occupied = new Zauzet();
        occupied.setCar(request.getOglas().getVozilo());
        occupied.setDateFrom(request.getOd());
        occupied.setDateTo(request.getDoo());
        this.zauzetRepository.save(occupied);
    }
}
