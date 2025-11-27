package com.manager.clients.repository;

import com.manager.clients.models.Instructors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações CRUD e consultas relacionadas a Instrutores.
 */
@Repository
public interface InstructorsRepository extends JpaRepository<Instructors, Long> {
	Optional<Instructors> findByEmail(String email);
}
