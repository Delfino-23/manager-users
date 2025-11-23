package com.manager.clients.repository;

import com.manager.clients.models.Administrators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorsRepository extends JpaRepository<Administrators, Long> {}
