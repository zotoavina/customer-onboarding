package com.mcb.submission.service;

import com.mcb.submission.exception.FileValidationException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void validateFile(MultipartFile file) throws FileValidationException;
}
