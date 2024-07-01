package com.mcb.submission.service.impl;

import com.mcb.submission.exception.FileValidationException;
import com.mcb.submission.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {


    private void checkIfFileIsEmpty(MultipartFile file) {
        log.info("Check if file is empty");
        if (file.isEmpty()) {
            throw new FileValidationException("File is empty");
        }
    }

    private void checkFileSize(MultipartFile file, long maxSize) {
        log.info("Check file size");
        if (file.getSize() > maxSize) {
            throw new FileValidationException("File size exceeds the maximum limit of " + (maxSize / (1024 * 1024)) + " MB");
        }
    }


    private void checkFileExtension(MultipartFile file, List<String> allowedExtension) {
        log.info("Check file extension");
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.contains(".")) {
            throw new FileValidationException("File extension is missing");
        }

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!allowedExtension.contains(fileExtension)) {
            throw new FileValidationException("File extension " + fileExtension + " is not allowed");
        }
    }


    @Override
    public void validateFile(MultipartFile file, long maxSize, List<String> allowedExtensions)
            throws FileValidationException {
        log.info("Validate file, check size, check extension");
        checkIfFileIsEmpty(file);
        checkFileSize(file, maxSize);
        checkFileExtension(file, allowedExtensions);
    }

    @Override
    public void uploadFile(MultipartFile file, String encryptedFileName, String uploadDir) throws IOException {
        log.info("Upload file : start upload file");
        if (file == null || file.isEmpty()) throw new InvalidParameterException("Veuillez attacher un fichier");
        try {
            byte[] bytes = file.getBytes();
            Path pathDirectory = Path.of(uploadDir);
            if (!Files.exists(pathDirectory)) {
                Files.createDirectory(pathDirectory);
            }
            Path filePath = Paths.get(uploadDir, encryptedFileName);

            Files.write(filePath, bytes);
        } catch (IOException e) {
            log.info("An error occurred while uploading file : error {} ", e.getMessage());
            throw e;
        }
    }
}
