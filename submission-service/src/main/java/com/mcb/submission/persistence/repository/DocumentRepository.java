package com.mcb.submission.persistence.repository;

import com.mcb.submission.persistence.entity.ApplicationDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<ApplicationDocument, Integer> {

    Optional<ApplicationDocument> findApplicationDocumentByApplicationUUID(String uuid);
}
