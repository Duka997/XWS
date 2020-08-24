package com.demo.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@AllArgsConstructor
@Table(name = "vozilo")
public class Slika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="LONGBLOB")
    private String info;

    @Column(columnDefinition="LONGBLOB")
    private String tip;

    @Lob
    @Column(name = "slika", columnDefinition="LONGBLOB")
    private Blob image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vozilo_id")
    private Vozilo vozilo;

    public Slika() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }
}
