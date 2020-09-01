package xml.team7.voziloservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "komentar")
public class Komentar {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tekst;

    @Column
    private boolean odobren;

    @ManyToOne
    @JoinColumn(name = "vozilo_id")
    private Vozilo vozilo;

    @ManyToOne
    @JoinColumn(name = "oglas_id")
    private Oglas oglas;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
