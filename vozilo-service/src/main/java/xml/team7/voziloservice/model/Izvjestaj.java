package xml.team7.voziloservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Izvjestaj {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String komentar;

    @Column
    private double predjeniKilometri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vozilo_id", nullable = false)
    private Vozilo vozilo;
}
