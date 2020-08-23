package com.demo.model;


import com.demo.dto.MarkaAutomobilaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name="markaautomobila")
public class MarkaAutomobila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nazivMarke;

    @Column
    private String model;

    @Column
    private boolean obrisan;

    public MarkaAutomobila() {
    }

    public MarkaAutomobila(MarkaAutomobilaDTO mDTO) {
        this.id = mDTO.getId();
        this.nazivMarke = mDTO.getNazivMarke();
        this.model = mDTO.getModel();
        this.obrisan = mDTO.isObrisan();
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
