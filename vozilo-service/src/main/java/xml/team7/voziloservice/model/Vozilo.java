package xml.team7.voziloservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@Table(name = "vozilo")
public class Vozilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double cijena;

    @Column
    private double kilometraza;

    @Column
    private double mozePreciKM;

    @Column
    private boolean imaAndroid;

    @Column
    private boolean coliisionDamageWavier;

    @Column
    private int brSjedistaZaDjecu;

    @Column
    private double ocjena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "markaAutomobila_id")
    private MarkaAutomobila markaAutomobila;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klasaAutomobila_id")
    private KlasaAutomobila klasaAutomobila;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipGoriva_id")
    private TipGoriva tipGoriva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipMjenjaca_id")
    private TipMjenjaca tipMjenjaca;

    @OneToMany(mappedBy = "vozilo", fetch = FetchType.LAZY)
    private Set<Izvjestaj> izvjestaji;

    @OneToMany(mappedBy = "vozilo", fetch = FetchType.LAZY)
    private Set<Komentar> komentari;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "vozilo", fetch = FetchType.LAZY)
    private Set<Ocjena> ocjene;

    @OneToMany(mappedBy = "vozilo")
    private Set<Oglas> oglasi;

    @Column
    private boolean bundle;

    @OneToMany(mappedBy = "vozilo", cascade = CascadeType.ALL)
    private Set<Slika> slike;

    public Vozilo() {
    }

    public Long getId() {
        return id;
    }

    public boolean isBundle() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    public Set<Slika> getSlike() {
        return slike;
    }

    public void setSlike(Set<Slika> slike) {
        this.slike = slike;
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

    public boolean isImaAndroid() {
        return imaAndroid;
    }

    public void setImaAndroid(boolean imaAndroid) {
        this.imaAndroid = imaAndroid;
    }

    public boolean isColiisionDamageWavier() {
        return coliisionDamageWavier;
    }

    public void setColiisionDamageWavier(boolean coliisionDamageWavier) {
        this.coliisionDamageWavier = coliisionDamageWavier;
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

    public MarkaAutomobila getMarkaAutomobila() {
        return markaAutomobila;
    }

    public void setMarkaAutomobila(MarkaAutomobila markaAutomobila) {
        this.markaAutomobila = markaAutomobila;
    }

    public KlasaAutomobila getKlasaAutomobila() {
        return klasaAutomobila;
    }

    public void setKlasaAutomobila(KlasaAutomobila klasaAutomobila) {
        this.klasaAutomobila = klasaAutomobila;
    }

    public TipGoriva getTipGoriva() {
        return tipGoriva;
    }

    public void setTipGoriva(TipGoriva tipGoriva) {
        this.tipGoriva = tipGoriva;
    }

    public TipMjenjaca getTipMjenjaca() {
        return tipMjenjaca;
    }

    public void setTipMjenjaca(TipMjenjaca tipMjenjaca) {
        this.tipMjenjaca = tipMjenjaca;
    }

    public Set<Izvjestaj> getIzvjestaji() {
        return izvjestaji;
    }

    public void setIzvjestaji(Set<Izvjestaj> izvjestaji) {
        this.izvjestaji = izvjestaji;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Ocjena> getOcjene() {
        return ocjene;
    }

    public void setOcjene(Set<Ocjena> ocjene) {
        this.ocjene = ocjene;
    }

    public Set<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(Set<Oglas> oglasi) {
        this.oglasi = oglasi;
    }
}
