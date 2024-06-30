package com.mcb.submission.persistence.repository;

import com.mcb.submission.persistence.entity.ApplicationStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Integer> {

    @NonNull
    Optional<ApplicationStatus> findApplicationStatusByStatusCode(String statusCode);
}
