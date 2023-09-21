package org.ot.authserver.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.ot.authserver.entity.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthServerConfiguration {

	@Bean
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {

		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());

		// Redirect to the login page when not authenticated from the authorization
		// endpoint
		http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
				new LoginUrlAuthenticationEntryPoint("/login"), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
				.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));

		return http.build();
	}

	/*
	 * @Bean public ClientSettings clientSettings() { return
	 * ClientSettings.builder().requireAuthorizationConsent(true).requireProofKey(
	 * false).build(); }
	 * 
	 * @Bean TokenSettings tokenSettings() { return TokenSettings.builder()
	 * .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
	 * .accessTokenTimeToLive(Duration.ofDays(1)) .build(); }
	 */

	@Bean
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder()
				.issuer("http://auth-service:8000")
				.build();
	}

	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> tokenCusomizer(){
		return context -> {
			Authentication auth = context.getPrincipal();
			UserPrincipal user = (UserPrincipal) auth.getPrincipal();
			Set<String> roles = auth.getAuthorities().stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toSet());
			context.getClaims().claim("role", roles)
								.claim("id", user.getId());
		};
	}
	
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
		RSAKey rsaKey = generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<SecurityContext>(jwkSet);
	}

	private static RSAKey generateRsa() throws NoSuchAlgorithmException {
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
	}

	private static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);

		return keyPairGenerator.generateKeyPair();
	}

}
