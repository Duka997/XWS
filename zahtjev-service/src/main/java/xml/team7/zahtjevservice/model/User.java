package xml.team7.zahtjevservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Timestamp;
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
    private String email;

    @Column
    private String imeKompanije;


    @Column
    private String address;

    @OneToMany(mappedBy = "user")
    private Set<Vozilo> vozila;

    @OneToMany(mappedBy = "user")
    private Set<Oglas> oglasi;

    @OneToMany(mappedBy = "user")
    private Set<Cjenovnik> cjenovnici;

    @Column
    private Integer numCancelled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImeKompanije() {
        return imeKompanije;
    }

    public void setImeKompanije(String imeKompanije) {
        this.imeKompanije = imeKompanije;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(Set<Vozilo> vozila) {
        this.vozila = vozila;
    }

    public Set<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(Set<Oglas> oglasi) {
        this.oglasi = oglasi;
    }

    public Set<Cjenovnik> getCjenovnici() {
        return cjenovnici;
    }

    public void setCjenovnici(Set<Cjenovnik> cjenovnici) {
        this.cjenovnici = cjenovnici;
    }
}

