/**
 * 
 */
package com.example.mvc.configuration.springsecurity.ldap;

import java.util.Collection;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

/**
 * <p>We are adding this Mapper class just for example, actually if LDAP group names are same as </br>
 *  our application defined authority/role names then we spring automatically mapsthe LDAP group
 *  with our application defined roles/authorities. </p>
 *  
 *  <p> If LDAP group and role/authority names in our application name do not match, then we need to define this mapper,
 *  which maps LDAP groups with authorities defined in our application. </p>
 *  
 *  <p>NOTE: Spring automatically adds 'ROLE_' prefix to LDAP group names fetched from LDAP, this is because
 *  spring authorities starts with ROLE_XX.
 *  So, we need to consider that prefix as well while string comparison. </p>
 *  
 *  If we do not want spring to append any prefix to LDAP group names before matching them with application
 *  authorities we can provide empty string to rolPrefix("") method of AuthenticationManagerBuilder in configure().
 *  
 * @author amit
 *
 */
public class LDAPGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(LDAPGrantedAuthoritiesMapper.class);
	private final String ROLE_SPITTLE = "ROLE_SPITTLE";
	
	/**
	 * Spring will provide LDAP group names as a argument, we need to return corresponding application defined authorities.
	 * @param authorities Group names from LDAP
	 * @return Corresponding application authorities for LDAP group names
	 */
	@Override
	public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
		LOGGER.info("In with authorities :: {}", authorities);
		EnumSet<SecurityRoles> roles = EnumSet.noneOf(SecurityRoles.class); //empty EnumSet
		
		for(GrantedAuthority authority : authorities) {
			if (ROLE_SPITTLE.equals(authority.getAuthority())) {
				roles.add(SecurityRoles.ROLE_SPITTLE);
			}
		}
		LOGGER.info("Out with authorities :: {}", roles);
		return roles;
	}

}
