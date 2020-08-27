package com.demo.dto;

import com.demo.model.OglasUKorpi;
import com.demo.model.Vozilo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OglasUKorpiDTO {

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
    private Long userCartId;    //id usera kod kog se nalazi oglas
    private Long adId;

    public OglasUKorpiDTO(OglasUKorpi o) {
        this.id = o.getId();
        this.dostupan = o.isDostupan();
        this.mjestoPreuzimanja = o.getMjestoPreuzimanja();
        this.dozvoljenaKilometraza = o.getDozvoljenaKilometraza();
        this.od = o.getOd();
        this.doo = o.getDoo();
        this.vozilo = new VoziloDTO(o.getVozilo());
        this.user = new UserDTO(o.getUser());
        this.userCartId = o.getUserCartId();
        this.adId = o.getAdId();
    }
}
