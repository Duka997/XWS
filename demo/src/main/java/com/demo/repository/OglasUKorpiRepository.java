package com.demo.repository;

import com.demo.model.OglasUKorpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OglasUKorpiRepository extends JpaRepository<OglasUKorpi, Long> {
}
