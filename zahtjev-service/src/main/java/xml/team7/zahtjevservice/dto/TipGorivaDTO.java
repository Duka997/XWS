package xml.team7.zahtjevservice.dto;


import xml.team7.zahtjevservice.model.TipGoriva;

public class TipGorivaDTO {

    private Long id;
    private String naziv;
    private boolean obrisan;

    public TipGorivaDTO() {
    }

    public TipGorivaDTO(TipGoriva tipGoriva) {
        this.id = tipGoriva.getId();
        this.naziv = tipGoriva.getNaziv();
        this.obrisan = tipGoriva.isObrisan();
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
