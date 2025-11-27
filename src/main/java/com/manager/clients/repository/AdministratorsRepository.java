package com.manager.clients.repository;

import com.manager.clients.models.Administrators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações CRUD e consultas relacionadas a Administradores.
 */
@Repository
public interface AdministratorsRepository extends JpaRepository<Administrators, Long> {
	Optional<Administrators> findByEmail(String email);
}
