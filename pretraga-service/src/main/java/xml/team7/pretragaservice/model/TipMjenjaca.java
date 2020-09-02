package xml.team7.pretragaservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "tipmjenjaca")
public class TipMjenjaca {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private String naziv;

        @Column
        private Boolean obrisan;

        public TipMjenjaca() {
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

        public Boolean getObrisan() {
                return obrisan;
        }

        public void setObrisan(Boolean obrisan) {
                this.obrisan = obrisan;
        }
}
