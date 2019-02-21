/**
 * 
 */
package com.example.mvc.configuration.springsecurity.custom;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**Custom implementation of UserDetailsService
 * @author amit
 */
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// User class is concrete implementation of UserDetials interface
		// SimpleGrantedAuthority class is concrete implementation of org.springframework.security.core.GrantedAuthority interface
		
		//return new User("amipatil", "asdf1234", Arrays.asList(SecurityRoles.ROLE_SPITTLE)); // OR
		return new User("amipatil", "asdf1234", Arrays.asList(new SimpleGrantedAuthority("ROLE_SPITTLE"))); 
	}

}
