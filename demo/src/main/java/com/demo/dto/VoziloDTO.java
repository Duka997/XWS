package com.demo.dto;

import com.demo.model.Vozilo;

import java.util.List;

public class VoziloDTO {

    private Long id;
    private double cijena;
    private double kilometraza;
    private double mozePreciKM;
    private int brSjedistaZaDjecu;
    private double ocjena;
    private MarkaAutomobilaDTO markaAutomobila;
    private TipGorivaDTO tipGoriva;
    private TipMjenjacaDTO tipMjenjaca;
    private KlasaAutomobilaDTO klasaAutomobila;
    private Boolean imaAndroid;
    private boolean coliisionDamageWavier;
    private UserDTO agent;
    private List<String> slike;

    public VoziloDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public double getKilometraza() {
        return kilometraza;
    }

    public void setKilometraza(double kilometraza) {
        this.kilometraza = kilometraza;
    }

    public double getMozePreciKM() {
        return mozePreciKM;
    }

    public void setMozePreciKM(double mozePreciKM) {
        this.mozePreciKM = mozePreciKM;
    }

    public int getBrSjedistaZaDjecu() {
        return brSjedistaZaDjecu;
    }

    public void setBrSjedistaZaDjecu(int brSjedistaZaDjecu) {
        this.brSjedistaZaDjecu = brSjedistaZaDjecu;
    }

    public double getOcjena() {
        return ocjena;
    }

    public void setOcjena(double ocjena) {
        this.ocjena = ocjena;
    }

    public MarkaAutomobilaDTO getMarkaAutomobila() {
        return markaAutomobila;
    }

    public void setMarkaAutomobila(MarkaAutomobilaDTO markaAutomobila) {
        this.markaAutomobila = markaAutomobila;
    }

    public TipGorivaDTO getTipGoriva() {
        return tipGoriva;
    }

    public void setTipGoriva(TipGorivaDTO tipGoriva) {
        this.tipGoriva = tipGoriva;
    }

    public TipMjenjacaDTO getTipMjenjaca() {
        return tipMjenjaca;
    }

    public void setTipMjenjaca(TipMjenjacaDTO tipMjenjaca) {
        this.tipMjenjaca = tipMjenjaca;
    }

    public KlasaAutomobilaDTO getKlasaAutomobila() {
        return klasaAutomobila;
    }

    public void setKlasaAutomobila(KlasaAutomobilaDTO klasaAutomobila) {
        this.klasaAutomobila = klasaAutomobila;
    }

    public Boolean getImaAndroid() {
        return imaAndroid;
    }

    public void setImaAndroid(Boolean imaAndroid) {
        this.imaAndroid = imaAndroid;
    }

    public boolean isColiisionDamageWavier() {
        return coliisionDamageWavier;
    }

    public void setColiisionDamageWavier(boolean coliisionDamageWavier) {
        this.coliisionDamageWavier = coliisionDamageWavier;
    }

    public UserDTO getAgent() {
        return agent;
    }

    public void setAgent(UserDTO agent) {
        this.agent = agent;
    }

    public List<String> getSlike() {
        return slike;
    }

    public void setSlike(List<String> slike) {
        this.slike = slike;
    }
}
