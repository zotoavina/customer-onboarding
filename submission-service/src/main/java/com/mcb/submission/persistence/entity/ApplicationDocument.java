package com.mcb.submission.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ApplicationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Application application;

    @Column(nullable = false)
    private String applicationUUID;

    @Column(nullable = false, unique = true)
    private String documentUUID = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String encryptedFileName;

    @Column(nullable = false)
    private String path;

    private LocalDateTime creationDate = LocalDateTime.now();
}
