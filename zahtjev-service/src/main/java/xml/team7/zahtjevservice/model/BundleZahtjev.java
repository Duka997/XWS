package xml.team7.zahtjevservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BundleZahtjev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private StatusZahtjeva statusZahtjeva;

    @OneToMany(mappedBy = "bundle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ZahtjevZaIznajmljivanje> zahtjevi;
}
