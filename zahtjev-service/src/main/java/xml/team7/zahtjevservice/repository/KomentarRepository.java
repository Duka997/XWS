package xml.team7.zahtjevservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.zahtjevservice.model.Komentar;

import java.util.List;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar,Long> {
    List<Komentar> findAllByVoziloIdAndOdobren(Long id, boolean b);

    List<Komentar> findAllByOdobren(boolean b);

    Komentar findByUserIdAndOglasId(Long id, Long id1);
}
