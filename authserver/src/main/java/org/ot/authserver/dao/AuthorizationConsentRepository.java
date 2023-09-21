package org.ot.authserver.dao;

import java.util.Optional;

import org.ot.authserver.entity.AuthorizationConsent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationConsentRepository extends JpaRepository<AuthorizationConsent, AuthorizationConsent.AuthorizationConsentId> {

	Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
	void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}