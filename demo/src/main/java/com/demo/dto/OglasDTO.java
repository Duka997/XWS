package com.demo.dto;

import com.demo.model.*;
import org.joda.time.DateTime;

import java.util.Set;

public class OglasDTO {

    private Long id;
    private boolean dostupan;
    private String mjestoPreuzimanja;
    private double dozvoljenaKilometraza;
    private DateTime od;
    private DateTime doo;
    private VoziloDTO vozilo;
    private UserDTO user;
    //private Set<ZahtjevZaIznajmljivanjeDTO> zahtjevi;
    //private Set<KomentarDTO> komentari;
    //private Set<OcjenaDTO> ocjene;
    //private CjenovnikDTO cjenovnik;

    public OglasDTO(Long id, boolean dostupan, String mjestoPreuzimanja, double dozvoljenaKilometraza, DateTime od, DateTime doo, VoziloDTO vozilo, UserDTO user/*, Set<ZahtjevZaIznajmljivanje> zahtjevi, Set<Komentar> komentari, Set<Ocjena> ocjene, Cjenovnik cjenovnik*/) {
        this.id = id;
        this.dostupan = dostupan;
        this.mjestoPreuzimanja = mjestoPreuzimanja;
        this.dozvoljenaKilometraza = dozvoljenaKilometraza;
        this.od = od;
        this.doo = doo;
        this.vozilo = vozilo;
        this.user = user;
        /*this.zahtjevi = zahtjevi;
        this.komentari = komentari;
        this.ocjene = ocjene;
        this.cjenovnik = cjenovnik;*/
    }

    public OglasDTO(Oglas oglas) {
        this.id = oglas.getId();
        this.dostupan = oglas.isDostupan();
        this.mjestoPreuzimanja = oglas.getMjestoPreuzimanja();
        this.dozvoljenaKilometraza = oglas.getDozvoljenaKilometraza();
        this.od = oglas.getOd();
        this.doo = oglas.getDoo();
        this.vozilo = new VoziloDTO(oglas.getVozilo());
        this.user = new UserDTO(oglas.getUser());
        /*this.zahtjevi = oglas.getZahtjevi();
        this.komentari = oglas.getKomentari();
        this.ocjene = oglas.getOcjene();
        this.cjenovnik = oglas.getCjenovnik();*/
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

    public String getMjestoPreuzimanja() {
        return mjestoPreuzimanja;
    }

    public void setMjestoPreuzimanja(String mjestoPreuzimanja) {
        this.mjestoPreuzimanja = mjestoPreuzimanja;
    }

    public double getDozvoljenaKilometraza() {
        return dozvoljenaKilometraza;
    }

    public void setDozvoljenaKilometraza(double dozvoljenaKilometraza) {
        this.dozvoljenaKilometraza = dozvoljenaKilometraza;
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

    public VoziloDTO getVozilo() {
        return vozilo;
    }

    public void setVozilo(VoziloDTO vozilo) {
        this.vozilo = vozilo;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
/*
    public Set<ZahtjevZaIznajmljivanje> getZahtjevi() {
        return zahtjevi;
    }

    public void setZahtjevi(Set<ZahtjevZaIznajmljivanje> zahtjevi) {
        this.zahtjevi = zahtjevi;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Set<Ocjena> getOcjene() {
        return ocjene;
    }

    public void setOcjene(Set<Ocjena> ocjene) {
        this.ocjene = ocjene;
    }

    public Cjenovnik getCjenovnik() {
        return cjenovnik;
    }

    public void setCjenovnik(Cjenovnik cjenovnik) {
        this.cjenovnik = cjenovnik;
    }*/
}
