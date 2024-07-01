package com.mcb.submission.mapper.impl;

import com.mcb.submission.mapper.SubmissionMapper;
import com.mcb.submission.persistence.entity.Application;

import java.time.LocalDate;
import java.util.Map;

public class SubmissionMapperImpl implements SubmissionMapper {
    @Override
    public Application convert(Map<String, String> submissionParam) {
        var application = new Application();
        application.setActivityUuid(submissionParam.get("activityUuid"));
        application.setApplyingPurposeUuid(submissionParam.get("purposeUuid"));
        application.setCompanyName(submissionParam.get("companyName"));
        application.setCountryName(submissionParam.get("countryName"));
        application.setEntityTypeUuid(submissionParam.get("entityTypeUuid"));
        application.setLicence(submissionParam.get("licence"));
        application.setDirectorName(submissionParam.get("directorName"));
        application.setDirectorPassportNumber(submissionParam.get("directorPassportNumber"));
        application.setNameOfApplicant(submissionParam.get("nameOfApplicant"));
        application.setEmailForCom(submissionParam.get("emailForCom"));
        application.setRegistrationNumber(submissionParam.get("registrationNumber"));

        String incorporationDate = submissionParam.get("incorporationDate");
        application.setDateOfIncorporation(LocalDate.parse(incorporationDate));

        return application;
    }
}
