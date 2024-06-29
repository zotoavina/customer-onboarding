package com.mcb.submission.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class CustomerApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String applicationId = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String applyingPurpose;

    @NotEmpty
    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String entityTypeUuid;

    @Column(nullable = false)
    private String activityUuid;

    @NotEmpty
    @Column(nullable = false)
    private String licence;

    @Column(nullable = false)
    private String countryUuid;

    @Column(nullable = false)
    private String registrationNumber;


    @Column(nullable = false)
    private LocalDate dateOfIncorporation;

    @NotEmpty
    @Column(nullable = false)
    private String directorName;

    @NotEmpty
    @Column(nullable = false)
    private String directorPassportNumber;

    @NotEmpty
    @Column(nullable = false)
    private String nameOfApplicant;


    @Email
    @Column(nullable = false)
    private String emailForCom;
    
}
