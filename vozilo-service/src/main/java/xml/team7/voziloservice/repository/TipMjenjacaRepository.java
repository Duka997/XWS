package xml.team7.voziloservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.TipMjenjaca;

import java.util.List;

@Repository
public interface TipMjenjacaRepository  extends JpaRepository<TipMjenjaca, Long> {
    TipMjenjaca findById(String id);

    List<TipMjenjaca> findAllById(Long markId);
}
