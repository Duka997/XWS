package xml.team7.zahtjevservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oglasi_u_korpi")
public class OglasUKorpi {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @OneToMany(mappedBy = "oglas")
    private Set<ZahtjevZaIznajmljivanje> zahtjevi;

    @Column
    private Long userCartId;    //id usera kod kog se nalazi oglas

    @Column
    private Long AdId;
}
