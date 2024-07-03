CREATE TABLE `applying_purpose`
(
    `id`           int          NOT NULL AUTO_INCREMENT,
    `purpose_name` varchar(255) NOT NULL,
    `purposeuuid`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKg3bw76298atmlcojh6jk54sbu` (`purposeuuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- dataref_db.business_activity definition

CREATE TABLE `business_activity`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `activity_name` varchar(255) NOT NULL,
    `activityuuid`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKos1lgjb2gtblcfscbc64cgbn3` (`activityuuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- dataref_db.entity_type definition

CREATE TABLE `entity_type`
(
    `id`              int          NOT NULL AUTO_INCREMENT,
    `entity_typeuuid` varchar(255) NOT NULL,
    `name`            varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKr1xfvnsyr6cyp08pvh72nx8un` (`entity_typeuuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO applying_purpose
    (purpose_name, purposeuuid)
VALUES ('Investment portfolio', 'f877bc1d-9728-4203-a82a-9623c1c4627e');
INSERT INTO applying_purpose
    (purpose_name, purposeuuid)
VALUES ('Account to operate locally', 'a836d386-cf4e-4ae0-9bb2-536bd82a2e81');
INSERT INTO applying_purpose
    (purpose_name, purposeuuid)
VALUES ('Account to operate overseas', 'fb4ac5ad-3b31-407f-9400-01409ad8f508');
INSERT INTO applying_purpose
    (purpose_name, purposeuuid)
VALUES ('Energy & commodities financing', 'a1573d4b-6ef1-4df5-a05f-4658ca433337');

INSERT INTO business_activity
    (activity_name, activityuuid)
VALUES ('Banking', 'b5d39bb9-cd8a-4349-bfc5-3616291e233d');

INSERT INTO business_activity
    (activity_name, activityuuid)
VALUES ('Fishing', 'f904a764-3d8a-419b-bce6-d5d2e296f100');

INSERT INTO business_activity
    (activity_name, activityuuid)
VALUES ('Manufacturing', 'e4891e88-4410-41ff-95ae-e2039c4c6224');


INSERT INTO entity_type
    (entity_typeuuid, name)
VALUES ('ead0b8a2-fcb3-4f9b-ba56-153b4e9eea5d', 'Trust');

INSERT INTO entity_type
    (entity_typeuuid, name)
VALUES ('24b75a02-a143-48e9-a2c4-9d6e48d20812', 'Association');

INSERT INTO entity_type
    (entity_typeuuid, name)
VALUES ('b7bd2234-84f0-417d-8625-928c72b78127', 'Private Company');