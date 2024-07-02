package com.mcb.submission.persistence.repository;

import com.mcb.submission.persistence.entity.Manager;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    @NonNull
    Optional<Manager> findManagerByEmail(String email);
}
