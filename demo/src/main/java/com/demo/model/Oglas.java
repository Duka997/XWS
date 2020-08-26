package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@Table(name = "oglasi")
public class Oglas {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean dostupan;

    @Column
    private String mjestoPreuzimanja;

    @Column
    private double dozvoljenaKilometraza;

    @Column(name = "od")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime od;

    @Column(name = "doo")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime doo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vozilo_id")
    private Vozilo vozilo;

  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; */

    @OneToMany(mappedBy = "oglas")
    private Set<ZahtjevZaIznajmljivanje> zahtjevi;

    @OneToMany(mappedBy = "oglas")
    private Set<Komentar> komentari;

    @OneToMany(mappedBy = "oglas")
    private Set<Ocjena> ocjene;

   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "cjenovnik_id")
    //private Cjenovnik cjenovnik;

    public Oglas() {
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

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }



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


}
