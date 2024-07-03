-- submission_db.application_status definition

CREATE TABLE `application_status`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `status_code` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKho1rdslxotic9cbj41mdc1jsx` (`status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- submission_db.application definition

CREATE TABLE `application`
(
    `id`                       int          NOT NULL AUTO_INCREMENT,
    `activity_name`            varchar(255) NOT NULL,
    `activity_uuid`            varchar(255) NOT NULL,
    `application_id`           varchar(255) NOT NULL,
    `applying_purpose`         varchar(255) NOT NULL,
    `applying_purpose_uuid`    varchar(255) NOT NULL,
    `company_name`             varchar(255) NOT NULL,
    `country_name`             varchar(255) NOT NULL,
    `date_of_incorporation`    date         NOT NULL,
    `director_name`            varchar(255) NOT NULL,
    `director_passport_number` varchar(255) NOT NULL,
    `email_for_com`            varchar(255) NOT NULL,
    `entity_type_name`         varchar(255) NOT NULL,
    `entity_type_uuid`         varchar(255) NOT NULL,
    `licence`                  varchar(255) DEFAULT NULL,
    `modification_date`        datetime(6) DEFAULT NULL,
    `name_of_applicant`        varchar(255) NOT NULL,
    `registration_number`      varchar(255) NOT NULL,
    `submission_date`          datetime(6) NOT NULL,
    `current_status_id`        int          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKkf7a8vpf1qtib7lr9j1nqnj7` (`application_id`),
    KEY                        `FKr7rw2byh3ry4bfbe1movyhb4f` (`current_status_id`),
    CONSTRAINT `FKr7rw2byh3ry4bfbe1movyhb4f` FOREIGN KEY (`current_status_id`) REFERENCES `application_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- submission_db.application_document definition

CREATE TABLE `application_document`
(
    `id`                  int          NOT NULL AUTO_INCREMENT,
    `applicationuuid`     varchar(255) NOT NULL,
    `creation_date`       datetime(6) DEFAULT NULL,
    `documentuuid`        varchar(255) NOT NULL,
    `encrypted_file_name` varchar(255) NOT NULL,
    `file_name`           varchar(255) NOT NULL,
    `path`                varchar(255) NOT NULL,
    `application_id`      int          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK3q0w0qq0jo92y7dydfpq1ahmf` (`documentuuid`),
    KEY                   `FK949sx1qkharvq1jcw1em3me1s` (`application_id`),
    CONSTRAINT `FK949sx1qkharvq1jcw1em3me1s` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- submission_db.manager_profile definition

CREATE TABLE `manager_profile`
(
    `id`           int          NOT NULL AUTO_INCREMENT,
    `profile_code` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKcnj7x6j3akw319gp5t7lvn8xv` (`profile_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- submission_db.manager definition

CREATE TABLE `manager`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `email`      varchar(255) NOT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    `pwd`        varchar(255) NOT NULL,
    `uuid`       varchar(255) NOT NULL,
    `profile_id` int          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKch4c0h9mgdd2c5lategqkpsyi` (`email`),
    UNIQUE KEY `UKlvhb7ue2o30lga4i5cfj6e0mc` (`uuid`),
    KEY          `FKt5heartejg521t14vdagkeokc` (`profile_id`),
    CONSTRAINT `FKt5heartejg521t14vdagkeokc` FOREIGN KEY (`profile_id`) REFERENCES `manager_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO manager_profile
    (profile_code)
VALUES ('PROCESSOR');

INSERT INTO manager_profile
    (profile_code)
VALUES ('APPROVER');

INSERT INTO manager (email, pwd, first_name, last_name, uuid, profile_id)
values ('processor@gmail.com', '$2a$10$Z2W7H.l2Y01ucyjJRa0a5.S60vK4S2GnNJeFV2nZPsxF1amvPc9X2', 'John', 'carter',
        '6562abbb-0ccd-4d1e-b6a4-7f39127a4548', 1);

INSERT INTO manager (email, pwd, first_name, last_name, uuid, profile_id)
values ('approver@gmail.com', '$2a$10$Z2W7H.l2Y01ucyjJRa0a5.S60vK4S2GnNJeFV2nZPsxF1amvPc9X2', 'Jack', 'Doe',
        '6584d599-eab6-4f40-94f2-9f5c85c929e3', 2);
