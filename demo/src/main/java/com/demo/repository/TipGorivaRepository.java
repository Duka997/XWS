package com.demo.repository;

import com.demo.model.TipGoriva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipGorivaRepository   extends JpaRepository<TipGoriva, Long> {
    TipGoriva findById(String id);

    List<TipGoriva> findAllById(Long markId);
}
