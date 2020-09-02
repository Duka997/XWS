package xml.team7.zahtjevservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.zahtjevservice.model.MarkaAutomobila;

import java.util.List;

@Repository
public interface MarkaAutomobilaRepository extends JpaRepository<MarkaAutomobila, Long> {
    MarkaAutomobila findById(String id);

    List<MarkaAutomobila> findAllById(Long markId);
}
