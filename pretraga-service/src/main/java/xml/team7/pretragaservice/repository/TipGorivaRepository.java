package xml.team7.pretragaservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.pretragaservice.model.TipGoriva;

import java.util.List;

@Repository
public interface TipGorivaRepository   extends JpaRepository<TipGoriva, Long> {
    TipGoriva findById(String id);

    List<TipGoriva> findAllById(Long markId);
}
