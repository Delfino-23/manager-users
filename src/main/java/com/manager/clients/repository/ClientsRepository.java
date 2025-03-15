package com.manager.clients.repository;

import com.manager.clients.models.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsModel, Long> {}
