package com.mcb.submission.mapper;

import com.mcb.submission.persistence.entity.Application;

import java.util.Map;

public interface SubmissionMapper {

    Application convert(Map<String, String> submissionParam);
}
