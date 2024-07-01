package com.mcb.submission.service;

import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.persistence.entity.ApplicationDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface DocumentService {

    Optional<ApplicationDocument> findDocumentByUUID(String uuid);

    void saveDocument(Application application, MultipartFile file) throws IOException;
}
