package org.ot.authserver.config.manager;

import java.util.Optional;

import org.ot.authserver.dao.UserRepository;
import org.ot.authserver.entity.AuthUser;
import org.ot.authserver.entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JpaUserDetailsManager implements UserDetailsManager {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AuthUser> userByUserName = userRepository.findByUsername(username);
		AuthUser user = userByUserName.get();
		if (user == null || !user.getUsername().equals(username)) {
			log.error("Could not find User with username: {}", username);
			throw new UsernameNotFoundException("Invalid credentials");
		}
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

	@Override
	public void createUser(UserDetails user) {
	}

	@Override
	public void updateUser(UserDetails user) {
	}

	@Override
	public void deleteUser(String username) {
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
	}

	@Override
	public boolean userExists(String username) {
		AuthUser savedUser = userRepository.findByUsername(username).get();
		if (savedUser != null && savedUser.getUsername().equals(username)) {
			return true;
		}
		return false;
	}

}
