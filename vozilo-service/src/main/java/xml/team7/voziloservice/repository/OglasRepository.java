package xml.team7.voziloservice.repository;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.Oglas;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OglasRepository extends JpaRepository<Oglas, Long> {

    List<Oglas> findAllByVoziloId(Long id);

    @Query("select o from Oglas o join o.vozilo where o.id = (?1)")
    Oglas findByIdUser(Long id);
}
