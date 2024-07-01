package com.mcb.submission.service;

import com.mcb.submission.persistence.entity.Application;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface SubmissionService {

    @NonNull
    Optional<Application> findApplicationByApplicationId(String applicationUUID);


    void validateApplicationData(@NonNull Application application);

    @NonNull
    Application validateAndSaveApplication(@NonNull Application application,
                                           @NonNull MultipartFile document) throws IOException;

    @NonNull
    Application findApplicationByApplicationIdOrElseThrow(String applicationUUID);
}