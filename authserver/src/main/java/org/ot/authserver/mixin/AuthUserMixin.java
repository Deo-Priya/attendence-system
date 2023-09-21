package org.ot.authserver.mixin;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthUserMixin {

	@JsonCreator
	public AuthUserMixin(@JsonProperty("id") Long id, @JsonProperty("username") String username,
			@JsonProperty("password") String password, @JsonProperty("enabled") boolean enabled,
			@JsonProperty("accountNonExpired") boolean accountNonExpired,
			@JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
			@JsonProperty("accountNonLocked") boolean accountNonLocked,
			@JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {
	}
	
}
