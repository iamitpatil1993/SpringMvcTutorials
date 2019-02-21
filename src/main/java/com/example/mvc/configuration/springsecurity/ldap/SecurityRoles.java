package com.example.mvc.configuration.springsecurity.ldap;

import org.springframework.security.core.GrantedAuthority;

/**
 * This enum defines application defined authorities.
 * We need to implement marker interface GrantedAuthority because, we need to return SET of this 
 * enum from GrantedAuthoritiesMapper.mapAuthorities().
 * 
 * @author amit
 *
 */
public enum SecurityRoles implements GrantedAuthority {
	ROLE_SPITTLE;
	
	@Override
	public String getAuthority() {
		return name();
	}
}
