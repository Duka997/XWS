package xml.team7.zahtjevservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.zahtjevservice.model.BundleZahtjev;

@Repository
public interface ZahtjevZaBundleRepository extends JpaRepository<BundleZahtjev, Long> {

}
