package com.demo.repository;

import com.demo.model.Ocjena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcjenaRepository extends JpaRepository<Ocjena, Long> {
    List<Ocjena> findAllByVoziloId(Long voziloId);

    Ocjena findByUserIdAndOglasId(Long id, Long id1);
}
