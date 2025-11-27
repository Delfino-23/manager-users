package com.manager.clients.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidade para Administradores.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ADMINISTRATORS")
public class Administrators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "cpf", length = 11)
    private String cpf;
}
