package com.mcb.submission.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    void validateFile(MultipartFile file, long maxSize, List<String> allowedExtensions);

    void uploadFile(MultipartFile file, String encryptedFileName, String uploadDir) throws IOException;
}
