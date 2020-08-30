package com.demo.dto;

import com.demo.model.KlasaAutomobila;

public class KlasaAutomobilaDTO {
    private Long id;
    private String naziv;
    private boolean obrisan;

    public KlasaAutomobilaDTO() {
    }

    public KlasaAutomobilaDTO(KlasaAutomobila klasaAutomobila) {
        this.id = klasaAutomobila.getId();
        this.naziv = klasaAutomobila.getNaziv();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }
}
