package com.learn.kafka.transactions.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
public class MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}