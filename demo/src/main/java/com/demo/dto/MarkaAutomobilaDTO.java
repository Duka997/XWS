package com.demo.dto;

import com.demo.model.MarkaAutomobila;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
public class MarkaAutomobilaDTO {
    private Long id;
    private String nazivMarke;
    private String model;
    private boolean obrisan;

    public MarkaAutomobilaDTO() {
    }

    public MarkaAutomobilaDTO(MarkaAutomobila markaAutomobila) {
        this.id = markaAutomobila.getId();
        this.nazivMarke = markaAutomobila.getNazivMarke();
        this.model = markaAutomobila.getModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivMarke() {
        return nazivMarke;
    }

    public void setNazivMarke(String nazivMarke) {
        this.nazivMarke = nazivMarke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }
}
