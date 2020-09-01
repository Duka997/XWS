package xml.team7.voziloservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.Komentar;

import java.util.List;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar,Long> {

    List<Komentar> findByVoziloIdAndOdobren(Long id, boolean b);
}
