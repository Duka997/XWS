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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oglasi_u_korpi")
public class OglasUKorpi {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "oglas")
    private Set<ZahtjevZaIznajmljivanje> zahtjevi;

    @OneToMany(mappedBy = "oglas")
    private Set<Komentar> komentari;

    @OneToMany(mappedBy = "oglas")
    private Set<Ocjena> ocjene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cjenovnik_id")
    private Cjenovnik cjenovnik;

    @Column
    private Long userCartId;    //id usera kod kog se nalazi oglas
}
