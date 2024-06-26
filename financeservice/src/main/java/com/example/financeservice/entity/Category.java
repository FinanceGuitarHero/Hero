package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    private String description;
}