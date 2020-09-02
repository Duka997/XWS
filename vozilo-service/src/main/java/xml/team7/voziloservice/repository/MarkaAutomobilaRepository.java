package xml.team7.voziloservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.voziloservice.model.MarkaAutomobila;

import java.util.List;

@Repository
public interface MarkaAutomobilaRepository extends JpaRepository<MarkaAutomobila, Long> {
    MarkaAutomobila findById(String id);

    List<MarkaAutomobila> findAllById(Long markId);
}
