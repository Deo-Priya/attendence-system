package org.ot.authserver.dao;

import java.util.Optional;

import org.ot.authserver.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AuthUser, Long>{
	
	Optional<AuthUser> findByUsername(String userName);

}
