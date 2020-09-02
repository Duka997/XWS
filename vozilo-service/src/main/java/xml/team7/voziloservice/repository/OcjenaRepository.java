package xml.team7.voziloservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.Ocjena;

import java.util.List;

@Repository
public interface OcjenaRepository extends JpaRepository<Ocjena, Long> {
    List<Ocjena> findAllByVoziloId(Long voziloId);

    Ocjena findByUserIdAndOglasId(Long id, Long id1);
}
