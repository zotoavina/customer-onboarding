package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.persistence.entity.ApplicationDocument;
import com.mcb.submission.persistence.repository.DocumentRepository;
import com.mcb.submission.service.DocumentService;
import com.mcb.submission.service.FileService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    private final FileService fileService;

    @Value("${document.upload.directory}")
    private String docUploadDir;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024L;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "png", "pdf", "docx");


    public DocumentServiceImpl(DocumentRepository repository, FileService fileService) {
        this.documentRepository = repository;
        this.fileService = fileService;
    }

    @Override
    public Optional<ApplicationDocument> findDocumentByUUID(String uuid) {
        log.info("Get application document using uuid {}", uuid);
        return documentRepository.findApplicationDocumentByApplicationUUID(uuid);
    }

    @Override
    public void saveDocument(@NonNull Application application, @NonNull MultipartFile file) throws IOException {
        Objects.requireNonNull(application);
        Objects.requireNonNull(file);
        log.info("Saving application document, application id : {}", application.getApplicationId());
        fileService.validateFile(file, MAX_FILE_SIZE, ALLOWED_EXTENSIONS);
        String filename = file.getOriginalFilename();
        assert filename != null;
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        var document = new ApplicationDocument();
        document.setApplication(application);
        document.setApplicationUUID(application.getApplicationId());
        document.setFileName(filename);
        document.setEncryptedFileName(document.getDocumentUUID() + "." + extension);
        document.setPath(docUploadDir);

        fileService.uploadFile(file, document.getEncryptedFileName(), docUploadDir);

        documentRepository.save(document);
        log.info("Application document, application id : {} saved", application.getApplicationId());
    }
}
