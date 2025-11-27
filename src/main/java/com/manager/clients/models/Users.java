package com.manager.clients.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidade para Usuários.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "email", length = 200, nullable = false, unique = true)
    private String email;


    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "course", length = 200)
    private String course;
}
