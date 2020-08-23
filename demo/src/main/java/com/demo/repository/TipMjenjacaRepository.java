package com.demo.repository;

import com.demo.model.TipMjenjaca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipMjenjacaRepository  extends JpaRepository<TipMjenjaca, Long> {
    TipMjenjaca findById(String id);

    List<TipMjenjaca> findAllById(Long markId);
}
