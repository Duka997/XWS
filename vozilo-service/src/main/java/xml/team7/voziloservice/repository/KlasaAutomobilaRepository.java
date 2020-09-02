package xml.team7.voziloservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.KlasaAutomobila;

import java.util.List;

@Repository
public interface KlasaAutomobilaRepository extends JpaRepository<KlasaAutomobila, Long> {
    KlasaAutomobila findById(String id);

    List<KlasaAutomobila> findAllById(Long markId);
}