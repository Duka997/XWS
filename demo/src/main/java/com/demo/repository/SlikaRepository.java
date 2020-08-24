package com.demo.repository;

import com.demo.model.Slika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlikaRepository  extends JpaRepository<Slika, Long> {

}
