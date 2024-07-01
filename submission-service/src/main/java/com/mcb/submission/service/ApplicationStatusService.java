package com.mcb.submission.service;

import com.mcb.submission.persistence.entity.ApplicationStatus;

import java.util.Optional;

public interface ApplicationStatusService {
    Optional<ApplicationStatus> findByStatusCode(String statusCode);

    ApplicationStatus findByStatusCodeOrElseThrow(String statusCode);
}
