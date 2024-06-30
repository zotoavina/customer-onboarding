package com.mcb.submission.service;

import com.mcb.submission.persistence.entity.ApplicationStatus;
import com.mcb.submission.persistence.entity.CustomerApplication;

import java.util.List;

public interface SubmissionManagementService {

    List<CustomerApplication> getListOfSubmissionBasedOnStatus(ApplicationStatus status);

    List<CustomerApplication> getListOfSubmissionBasedOnStatus(String status);

    CustomerApplication updateCustomerApplication(CustomerApplication application);

    void proceedApplication(String applicationId);

    void rejectApplication(String applicationId);

    void approveApplication(String applicationId);

}
