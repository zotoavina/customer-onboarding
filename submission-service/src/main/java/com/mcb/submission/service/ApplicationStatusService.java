package com.mcb.submission.service;

import com.mcb.submission.persistence.entity.ApplicationStatus;

public interface ApplicationStatusService {

    ApplicationStatus findByStatusCodeOrElseThrow(String statusCode);
}
