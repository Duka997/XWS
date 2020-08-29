package com.demo.model;

import com.demo.dto.UserDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy=JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

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
    private String address;

    @Column
    private boolean enabled;

    @Column
    private boolean deleted;

    @Column
    private boolean isAdmin;

    @Column
    private String imeKompanije;

    @Column
    private Timestamp lastPasswordResetDate;

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

//    @OneToMany(mappedBy = "posiljalac")
//    private Set<Poruka> poslatePoruke;
//
//    @OneToMany(mappedBy = "primalac")
//    private Set<Poruka> primljenePoruke;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_privileges",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public User(UserDTO user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.isAdmin = user.isAdmin();
        this.enabled = user.isEnabled();
        this.deleted = user.isDeleted();
        this.address = user.getAddress();
        this.imeKompanije = user.getImeKompanije();
        this.poslovniID = user.getPoslovniID();
        this.lastPasswordResetDate = user.getLastPasswordResetDate();
        this.email = user.getEmail();
    }

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getImeKompanije() {
        return imeKompanije;
    }

    public void setImeKompanije(String imeKompanije) {
        this.imeKompanije = imeKompanije;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getPoslovniID() {
        return poslovniID;
    }

    public void setPoslovniID(String poslovniID) {
        this.poslovniID = poslovniID;
    }

    public Set<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(Set<Vozilo> vozila) {
        this.vozila = vozila;
    }



    public Set<Cjenovnik> getCjenovnici() {
        return cjenovnici;
    }

    public void setCjenovnici(Set<Cjenovnik> cjenovnici) {
        this.cjenovnici = cjenovnici;
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

//    public Set<Poruka> getPoslatePoruke() {
//        return poslatePoruke;
//    }
//
//    public void setPoslatePoruke(Set<Poruka> poslatePoruke) {
//        this.poslatePoruke = poslatePoruke;
//    }
//
//    public Set<Poruka> getPrimljenePoruke() {
//        return primljenePoruke;
//    }
//
//    public void setPrimljenePoruke(Set<Poruka> primljenePoruke) {
//        this.primljenePoruke = primljenePoruke;
//    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Set<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(Set<Oglas> oglasi) {
        this.oglasi = oglasi;
    }
}