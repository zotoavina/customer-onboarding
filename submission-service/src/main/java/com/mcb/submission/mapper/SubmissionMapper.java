package com.mcb.submission.mapper;

import com.mcb.submission.persistence.entity.CustomerApplication;

import java.util.Map;

public interface SubmissionMapper {

    CustomerApplication convert(Map<String, String> submissionParam);
}
