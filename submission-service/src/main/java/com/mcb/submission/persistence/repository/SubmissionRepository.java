package com.mcb.submission.persistence.repository;

import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.persistence.entity.ApplicationStatus;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Application, Integer> {

    @NonNull
    Optional<Application> findCustomerApplicationByApplicationId(String applicationUUID);

    List<Application> findCustomerApplicationByCurrentStatus(ApplicationStatus status);

    @Query("SELECT c.currentStatus.statusCode, COUNT(c) FROM Application  c GROUP BY c.currentStatus")
    List<Object[]> countByStatus();
}
