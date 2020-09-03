package xml.team7.voziloservice.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.Vozilo;
import xml.team7.voziloservice.model.Zauzet;

import java.util.List;

@Repository
public interface ZauzetRepository extends JpaRepository<Zauzet, Long> {
    List<Zauzet> findAllByVoziloId(Long carId);

    List<Zauzet> findAllByVoziloAndDooAfter(Vozilo c, DateTime now);
}
