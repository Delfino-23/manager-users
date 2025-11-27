package com.manager.clients.repository;

import com.manager.clients.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório para operações CRUD e consultas relacionadas a Usuários.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);
}
