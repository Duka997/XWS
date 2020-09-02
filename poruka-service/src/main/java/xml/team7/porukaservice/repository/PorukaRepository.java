package xml.team7.porukaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.porukaservice.dto.PorukaDTO;
import xml.team7.porukaservice.model.Poruka;

import java.util.List;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long> {

    List<Poruka>findAll();

    List<PorukaDTO> findAllByPrimalacId(Long id);

    List<PorukaDTO> findAllByPosiljalacId(Long id);
}
