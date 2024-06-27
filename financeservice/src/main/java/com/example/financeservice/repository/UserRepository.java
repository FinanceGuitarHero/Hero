package com.example.financeservice.repository;

import com.example.financeservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByTg_id(String tg_id);

    Optional<AppUser> findByTg_id(String tg_id);
}
