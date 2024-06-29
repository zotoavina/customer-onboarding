package com.mcb.datareference_service.persistence.repository;

import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessActivityRepository extends JpaRepository<BusinessActivity, Integer> {

    Optional<BusinessActivity> findByActivityUUID(String activityUUID);

}
