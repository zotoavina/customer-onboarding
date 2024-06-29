package com.mcb.datareference_service.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class EntityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String entityTypeUUID = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String name;
}
