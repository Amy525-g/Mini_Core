package com.minicore.core.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
