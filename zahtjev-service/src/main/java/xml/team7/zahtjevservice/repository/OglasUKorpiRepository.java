package xml.team7.zahtjevservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import xml.team7.zahtjevservice.model.OglasUKorpi;

@Repository
@EnableJpaRepositories
public interface OglasUKorpiRepository extends JpaRepository<OglasUKorpi, Long> {
}
