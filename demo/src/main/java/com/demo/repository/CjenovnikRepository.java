package com.demo.repository;

import com.demo.model.Cjenovnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CjenovnikRepository extends JpaRepository<Cjenovnik, Long> {
}
