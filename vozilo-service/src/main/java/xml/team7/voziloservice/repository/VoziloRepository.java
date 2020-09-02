package xml.team7.voziloservice.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.Vozilo;

import java.util.List;
import java.util.Set;

@Repository
@EnableJpaRepositories
public interface VoziloRepository  extends JpaRepository<Vozilo, Long> {
    Set<Vozilo> findAllByUser_Id(Long id);
    
    List<Vozilo> findAllByUserId(Long id);
}