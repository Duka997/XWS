package xml.team7.zahtjevservice.model;

import lombok.AllArgsConstructor;

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
        private boolean obrisan;

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

        public boolean isObrisan() {
                return obrisan;
        }

        public void setObrisan(boolean obrisan) {
                this.obrisan = obrisan;
        }
}
