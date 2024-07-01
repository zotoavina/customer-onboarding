package com.mcb.submission.service;

import com.mcb.submission.persistence.entity.CustomerApplication;
import lombok.NonNull;

import java.util.Optional;

public interface SubmissionService {

    @NonNull
    Optional<CustomerApplication> findApplicationByApplicationId(String applicationUUID);


    void validateApplicationData(@NonNull CustomerApplication customerApplication);

    @NonNull
    CustomerApplication validateAndSaveApplication(@NonNull CustomerApplication customerApplication);

    @NonNull
    CustomerApplication findApplicationByApplicationIdOrElseThrow(String applicationUUID);
}