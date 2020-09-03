package xml.team7.zahtjevservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.zahtjevservice.model.StatusZahtjeva;
import xml.team7.zahtjevservice.model.User;
import xml.team7.zahtjevservice.model.ZahtjevZaIznajmljivanje;

import java.util.List;

@Repository
public interface ZahtjevZaIznajmljivanjeRepository extends JpaRepository<ZahtjevZaIznajmljivanje, Long> {

    List<ZahtjevZaIznajmljivanje> findAllByUserId(Long id);

    List<ZahtjevZaIznajmljivanje> findAllByOglas_User_Id(Long id);

    List<ZahtjevZaIznajmljivanje> findByOglasId(Long id);

}
