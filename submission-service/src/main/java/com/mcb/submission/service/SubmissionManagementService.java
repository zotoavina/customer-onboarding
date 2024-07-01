package com.mcb.submission.service;

import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.persistence.entity.ApplicationStatus;

import java.util.List;

public interface SubmissionManagementService {

    List<Application> getListOfSubmissionBasedOnStatus(ApplicationStatus status);

    List<Application> getListOfSubmissionBasedOnStatus(String status);

    Application updateCustomerApplication(Application application);

    void proceedApplication(String applicationId);

    void rejectApplication(String applicationId);

    void approveApplication(String applicationId);

}
