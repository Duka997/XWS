package xml.team7.zahtjevservice.repository;


import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import xml.team7.zahtjevservice.model.Oglas;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OglasRepository extends JpaRepository<Oglas, Long> {

}
