package com.mcb.submission.service.impl;

import com.mcb.submission.exception.FileValidationException;
import com.mcb.submission.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024L;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "png", "pdf", "docx");


    private void checkIfFileIsEmpty(MultipartFile file) {
        log.info("Check if file is empty");
        if (file.isEmpty()) {
            throw new FileValidationException("File is empty");
        }
    }

    private void checkFileSize(MultipartFile file) {
        log.info("Check file size");
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileValidationException("File size exceeds the maximum limit of " + (MAX_FILE_SIZE / (1024 * 1024)) + " MB");
        }
    }

    private void checkFileExtension(MultipartFile file) {
        log.info("Check file extension");
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.contains(".")) {
            throw new FileValidationException("File extension is missing");
        }

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            throw new FileValidationException("File extension " + fileExtension + " is not allowed");
        }
    }

    @Override
    public void validateFile(MultipartFile file) throws FileValidationException {
        checkIfFileIsEmpty(file);
        checkFileSize(file);
        checkFileExtension(file);
    }
}
