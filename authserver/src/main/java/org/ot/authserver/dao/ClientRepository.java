package org.ot.authserver.dao;

import java.util.Optional;

import org.ot.authserver.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

	Optional<Client> findByClientId(String clientId);
}
