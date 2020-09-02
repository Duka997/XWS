package xml.team7.voziloservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xml.team7.voziloservice.model.Cjenovnik;

@Getter
@Setter
@AllArgsConstructor
public class CjenovnikDTO {
        private Long id;
        private double cijenaPoDanu;
        private double cijenaPoKM;
        private double cijenaCDW;
        private double popust;
        private String userUsername;

    public CjenovnikDTO() {
    }

    public CjenovnikDTO(Cjenovnik cjenovnik) {
        this.id = cjenovnik.getId();
        this.cijenaPoDanu = cjenovnik.getCijenaPoDanu();
        this.cijenaPoKM = cjenovnik.getCijenaPoKM();
        this.cijenaCDW = cjenovnik.getCijenaCDW();
        this.popust = cjenovnik.getPopust();
        this.userUsername = cjenovnik.getUser().getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCijenaPoDanu() {
        return cijenaPoDanu;
    }

    public void setCijenaPoDanu(double cijenaPoDanu) {
        this.cijenaPoDanu = cijenaPoDanu;
    }

    public double getCijenaPoKM() {
        return cijenaPoKM;
    }

    public void setCijenaPoKM(double cijenaPoKM) {
        this.cijenaPoKM = cijenaPoKM;
    }

    public double getCijenaCDW() {
        return cijenaCDW;
    }

    public void setCijenaCDW(double cijenaCDW) {
        this.cijenaCDW = cijenaCDW;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
