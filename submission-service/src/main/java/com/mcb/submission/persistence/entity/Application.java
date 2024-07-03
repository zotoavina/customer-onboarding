package com.mcb.submission.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String applicationId = UUID.randomUUID().toString();

    @Column(nullable = false)
    @NotEmpty
    private String applyingPurposeUuid;

    @Column(nullable = false)
    private String applyingPurpose;

    @NotEmpty
    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    @NotEmpty
    private String entityTypeUuid;

    @Column(nullable = false)
    private String entityTypeName;

    @NotEmpty
    @Column(nullable = false)
    private String activityUuid;

    @Column(nullable = false)
    private String activityName;

    private String licence;

    @Column(nullable = false)
    @NotEmpty
    private String countryName;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(regexp = "\\d+", message = "Registration number must contain only numeric values")
    private String registrationNumber;


    @Column(nullable = false)
    private LocalDate dateOfIncorporation;

    @NotEmpty
    @Column(nullable = false)
    private String directorName;

    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Director passport number must be alphanumeric and uppercase")
    @Column(nullable = false)
    private String directorPassportNumber;

    @NotEmpty
    @Column(nullable = false)
    private String nameOfApplicant;


    @Email
    @Column(nullable = false)
    private String emailForCom;

    @Column(nullable = false)
    private LocalDateTime submissionDate = LocalDateTime.now();

    private LocalDateTime modificationDate;

    @ManyToOne(optional = false)
    private ApplicationStatus currentStatus;

    public boolean checkStatus(String status){
        Objects.requireNonNull(status);
        Objects.requireNonNull(currentStatus);
        return status.equals(currentStatus.getStatusCode());
    }
}
