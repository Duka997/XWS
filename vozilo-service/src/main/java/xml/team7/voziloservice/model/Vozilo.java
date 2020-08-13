package xml.team7.voziloservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "vozilo", fetch = FetchType.LAZY)
    private Set<Komentar> komentari;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "vozilo", fetch = FetchType.LAZY)
    private Set<Ocjena> ocjene;

    @OneToMany(mappedBy = "vozilo")
    private Set<Oglas> oglasi;
}
