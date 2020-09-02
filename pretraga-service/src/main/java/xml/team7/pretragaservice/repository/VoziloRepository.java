package xml.team7.pretragaservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import xml.team7.pretragaservice.model.Vozilo;

import java.util.List;
import java.util.Set;

@Repository
@EnableJpaRepositories
public interface VoziloRepository  extends JpaRepository<Vozilo, Long> {
}