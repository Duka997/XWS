package com.demo.repository;

import com.demo.model.Izvjestaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Izvjestaj, Long> {
}
