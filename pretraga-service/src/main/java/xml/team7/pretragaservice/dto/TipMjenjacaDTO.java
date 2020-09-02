package xml.team7.pretragaservice.dto;

import lombok.*;
import xml.team7.pretragaservice.model.TipMjenjaca;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TipMjenjacaDTO {

    private Long id;
    private String naziv;
    private boolean obrisan;

    public TipMjenjacaDTO() {
    }

    public TipMjenjacaDTO(TipMjenjaca tipMjenjaca) {
        this.id = tipMjenjaca.getId();
        this.naziv = tipMjenjaca.getNaziv();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }
}
