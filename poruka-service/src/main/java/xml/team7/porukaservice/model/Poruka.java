package xml.team7.porukaservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Poruka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sadrzaj;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime datumSlanja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posiljalac_id")
    private User posiljalac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primalac_id")
    private User primalac;
}
