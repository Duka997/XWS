package xml.team7.zahtjevservice.dto;

import java.sql.Blob;

public class SlikaDTO {
    private String info;
    private String tip;
    private Blob slika;

    public void setInfo(String info) {
        this.info = info;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setSlika(Blob slika) {
        this.slika = slika;
    }

    public String getInfo() {
        return info;
    }

    public String getTip() {
        return tip;
    }

    public Blob getSlika() {
        return slika;
    }
}
