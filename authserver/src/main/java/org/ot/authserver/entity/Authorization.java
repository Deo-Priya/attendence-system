package org.ot.authserver.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sb_authorization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authorization {

	@Id
	@Column
	private String id;
	private String registeredClientId;
	private String principalName;
	private String authorizationGrantType;
	@Column(length = 1000)
	private String authorizedScopes;
	@Column(columnDefinition = "text", length = 4000)
	private String attributes;
	@Column(length = 500)
	private String state;
	@Column(columnDefinition = "text", length = 4000)
	private String authorizationCodeValue;
	private Instant authorizationCodeIssuedAt;
	private Instant authorizationCodeExpiresAt;
	private String authorizationCodeMetadata;
	@Column(columnDefinition = "text", length = 4000)
	private String accessTokenValue;
	private Instant accessTokenIssuedAt;
	private Instant accessTokenExpiresAt;
	@Column(columnDefinition = "text", length = 2000)
	private String accessTokenMetadata;
	private String accessTokenType;
	@Column(length = 1000)
	private String accessTokenScopes;
	@Column(columnDefinition = "text", length = 4000)
	private String refreshTokenValue;
	private Instant refreshTokenIssuedAt;
	private Instant refreshTokenExpiresAt;
	@Column(columnDefinition = "text", length = 2000)
	private String refreshTokenMetadata;
	@Column(columnDefinition = "text", length = 4000)
	private String oidcIdTokenValue;
	private Instant oidcIdTokenIssuedAt;
	private Instant oidcIdTokenExpiresAt;
	@Column(columnDefinition = "text", length = 2000)
	private String oidcIdTokenMetadata;
	@Column(columnDefinition = "text", length = 2000)
	private String oidcIdTokenClaims;
}