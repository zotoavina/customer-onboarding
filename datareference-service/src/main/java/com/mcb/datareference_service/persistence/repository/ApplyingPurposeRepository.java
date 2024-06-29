package com.mcb.datareference_service.persistence.repository;

import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplyingPurposeRepository extends JpaRepository<ApplyingPurpose, String> {

    Optional<ApplyingPurpose> findByPurposeUUID(String purposeUUID);
}
