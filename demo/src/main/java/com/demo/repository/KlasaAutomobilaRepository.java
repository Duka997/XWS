package com.demo.repository;

import com.demo.model.KlasaAutomobila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlasaAutomobilaRepository  extends JpaRepository<KlasaAutomobila, Long> {
    KlasaAutomobila findById(String id);

    List<KlasaAutomobila> findAllById(Long markId);
}
