package com.mcb.submission.service.impl;

import com.mcb.submission.exception.FileValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {

    @InjectMocks
    private FileServiceImpl fileService;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testValidateFile_FileIsEmpty() {
        when(multipartFile.isEmpty()).thenReturn(true);

        assertThrows(FileValidationException.class, () -> {
            fileService.validateFile(multipartFile);
        }, "File is empty");
    }

    @Test
     void testValidateFile_FileSizeExceedsLimit() {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getSize()).thenReturn(6 * 1024 * 1024L); // 6 MB

        assertThrows(FileValidationException.class, () -> {
            fileService.validateFile(multipartFile);
        }, "File size exceeds the maximum limit of 5 MB");
    }

    @Test
    void testValidateFile_FileExtensionNotAllowed() {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getSize()).thenReturn(1024 * 1024L); // 1 MB
        when(multipartFile.getOriginalFilename()).thenReturn("test.exe");

        assertThrows(FileValidationException.class, () -> {
            fileService.validateFile(multipartFile);
        }, "File extension exe is not allowed");
    }

    @Test
     void testValidateFile_FileExtensionMissing() {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getSize()).thenReturn(1024 * 1024L); // 1 MB
        when(multipartFile.getOriginalFilename()).thenReturn("testfile");

        assertThrows(FileValidationException.class, () -> {
            fileService.validateFile(multipartFile);
        }, "File extension is missing");
    }

    @Test
     void testValidateFile_Success() {
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getSize()).thenReturn(1024 * 1024L); // 1 MB
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");

        // This should pass without throwing an exception
        fileService.validateFile(multipartFile);
    }
}