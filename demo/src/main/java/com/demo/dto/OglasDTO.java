package com.demo.dto;

import org.joda.time.DateTime;

public class OglasDTO {

    private Long id;
    private boolean dostupan;
    private VoziloDTO vozilo;
    private String mestoPreuzimanja;
    private DateTime od;
    private DateTime doo;
    private Long userId;
    private Long cjenovnikID;

    public OglasDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDostupan() {
        return dostupan;
    }

    public void setDostupan(boolean dostupan) {
        this.dostupan = dostupan;
    }

    public VoziloDTO getVozilo() {
        return vozilo;
    }

    public void setVozilo(VoziloDTO vozilo) {
        this.vozilo = vozilo;
    }

    public String getMestoPreuzimanja() {
        return mestoPreuzimanja;
    }

    public void setMestoPreuzimanja(String mestoPreuzimanja) {
        this.mestoPreuzimanja = mestoPreuzimanja;
    }

    public DateTime getOd() {
        return od;
    }

    public void setOd(DateTime od) {
        this.od = od;
    }

    public DateTime getDoo() {
        return doo;
    }

    public void setDoo(DateTime doo) {
        this.doo = doo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCjenovnikID() {
        return cjenovnikID;
    }

    public void setCjenovnikID(Long cjenovnikID) {
        this.cjenovnikID = cjenovnikID;
    }
}