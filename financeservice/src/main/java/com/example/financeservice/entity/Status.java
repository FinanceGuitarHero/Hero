package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @Column(name = "status_id")
    private Integer id;

    @Column(name = "status_name")
    private String statusName
            ;
    private String description;

}