package com.mcb.submission.persistence.repository;

import com.mcb.submission.persistence.entity.ApplicationStatus;
import com.mcb.submission.persistence.entity.CustomerApplication;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<CustomerApplication, Integer> {

    @NonNull
    Optional<CustomerApplication> findCustomerApplicationByApplicationId(String applicationUUID);

    List<CustomerApplication> findCustomerApplicationByCurrentStatus(ApplicationStatus status);

    @Query("SELECT c.currentStatus, COUNT(c) FROM CustomerApplication  c GROUP BY c.currentStatus")
    List<Object[]> countByStatus();
}
