package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy=JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String ucidn;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private  String role;

    @Column
    private boolean isEnabled;

    @Column
    private boolean isAdmin;

    @Column
    private String imeKompanije;

    @Column
    private String poslovniID;

    @OneToMany(mappedBy = "user")
    private Set<Vozilo> vozila;

    @OneToMany(mappedBy = "user")
    private Set<Oglas> oglasi;

    @OneToMany(mappedBy = "user")
    private Set<Cjenovnik> cjenovnici;

    @OneToMany(mappedBy = "user")
    private Set<Komentar> komentari;

    @OneToMany(mappedBy = "user")
    private Set<Ocjena> ocjene;

    @OneToMany(mappedBy = "posiljalac")
    private Set<Poruka> poslatePoruke;

    @OneToMany(mappedBy = "primalac")
    private Set<Poruka> primljenePoruke;
}