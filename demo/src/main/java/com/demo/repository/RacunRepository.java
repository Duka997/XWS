package com.demo.repository;

import com.demo.model.Racun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long> {

    List<Racun> findAllByUserUsernameAndPaid(String username, boolean b);

    List<Racun> findAllByUserIdAndPaid(Long id, boolean b);

    List<Racun> findAllByUserUsername(String username);
}
