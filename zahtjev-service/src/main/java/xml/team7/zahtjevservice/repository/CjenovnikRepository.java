package xml.team7.zahtjevservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.zahtjevservice.model.Cjenovnik;

import java.util.List;

@Repository
public interface CjenovnikRepository extends JpaRepository<Cjenovnik, Long> {
    List<Cjenovnik> findAllByUserUsername(String username);
}
