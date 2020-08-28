package com.demo.repository;

import com.demo.model.Vozilo;
import com.demo.model.Zauzet;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZauzetRepository extends JpaRepository<Zauzet, Long> {

}
