package com.demo.repository;

import com.demo.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface VoziloRepository  extends JpaRepository<Vozilo, Long> {
    List<Vozilo> findAllByUserId(Long id);
}