package com.demo.repository;

import com.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String s);
}