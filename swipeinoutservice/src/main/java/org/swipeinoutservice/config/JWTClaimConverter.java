package org.swipeinoutservice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class JWTClaimConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {

		Map<String, Object> claims = source.getClaims();
		if (claims == null || claims.isEmpty()) {
			return new ArrayList<GrantedAuthority>();
		}
		Collection<GrantedAuthority> roles = ((List<String>) claims.get("role")).stream()
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return roles;
	}

}
