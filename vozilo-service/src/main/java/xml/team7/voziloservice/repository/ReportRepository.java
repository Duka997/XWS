package xml.team7.voziloservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.Izvjestaj;

@Repository
public interface ReportRepository extends JpaRepository<Izvjestaj, Long> {
}
