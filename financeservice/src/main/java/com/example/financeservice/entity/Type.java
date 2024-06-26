package com.example.financeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "types")
@Data
public class Type {

    @Id
    private Integer id;
    private String type_name;
    private String description;

}
