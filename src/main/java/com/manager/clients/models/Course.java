package com.manager.clients.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidade para Cursos.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COURSES")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;
}
