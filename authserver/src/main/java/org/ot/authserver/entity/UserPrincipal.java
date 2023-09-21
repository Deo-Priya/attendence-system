package org.ot.authserver.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private AuthUser user;
	
	public UserPrincipal() {}
	
	public UserPrincipal(AuthUser user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities().stream().map(a -> a.getAuthority())
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}
	
	public long getId() {
		return user.getId();
	}
	
	public AuthUser getUser() {
		return user;
	}

}
