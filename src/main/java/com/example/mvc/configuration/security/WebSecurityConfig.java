/**
 * 
 */
package com.example.mvc.configuration.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.mvc.configuration.springsecurity.ldap.LDAPGrantedAuthoritiesMapper;
import com.example.mvc.configuration.springsecurity.ldap.SecurityRoles;

/**
 * @author amit
 *
 */

@Configuration
@EnableWebSecurity // Enabled web security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // this adapter class implements WebSecurityConfigurer interface

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/spittles/**").hasAuthority(SecurityRoles.ROLE_SPITTLE.toString()) // this says authenticate request to urls matching this url, and user must have role of 'SPITLE'
		.antMatchers("/files/upload", "/fileupload").authenticated() // this says authenticate request to urls matching this url, and can have any role
		.anyRequest().permitAll().and() // any request other than mentioned above should be accessible witout authentication
		.formLogin(); // mode of authentication should be login page, witthuoud configuring this, it will not redirect u to login page if session expired, or authentication required rather simply give 403 error pgae.
	}
	
	/**
	 * UserDetails Configuration using in memory data
	 */
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication() // we care using in memory data store
		.passwordEncoder(NoOpPasswordEncoder.getInstance()) // default password encoder for password comparision is NoOpPasswordencoder (plain text passwords)
		.withUser("amipatil").password("asdf1234").roles("USER").and() // user with USER role
		.withUser("iamitpatil").password("asdf1234").roles("SPITTLE", "USER"); // USER with spittle and user role
	}*/
	
	/**
	 * UserDetails Configuration using in relational database
	 */
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication().dataSource(dataSource) // use daatasource to get user details 
		.passwordEncoder(NoOpPasswordEncoder.getInstance()); // need to configure passwordEncoder as well.
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()// this method enables LDAP as a spring sec bac
 		//.rolePrefix("") spring automatically adds 'ROLE_' prefix to group names fetched from LDAP in order to match them with authorities (which has ROLE_ as prefix), if we do not want this behavior and want to add blank/any other prefix we can use this method.
		.authoritiesMapper(new LDAPGrantedAuthoritiesMapper()) // seet class level description.
		.userSearchBase("ou=people,ou=system") // Node in tree from where to start searching users, we should provide this as specific as possible to reduce search space by LDAP, it will help finding user in less time as compare to seaching entire tree from ROOT DSE, if we do not provide this spring will search DIT from Root DSE. 
		.userSearchFilter("(uid={0})") // what is name of attribute of entity in LDAP which maintains username of user., in our case we stored username in uid attribute so, we specified this. This is actually RDN.
		.groupSearchBase("ou=groups,ou=system") // Node in tree from where to start searching groups and user is member of, we should provide this as specific as possible to reduce search space by LDAP, it will help finding groups to which user belongs in less time as compared to searching entire DIT from ROOT DSE, if we do not provide this spring will search DIT from Root DSE. 
		.contextSource() // this method is used to configure LDAP server connection details.
		.url("ldap://127.0.0.1:10389")
		.managerDn("uid=admin,ou=system") // It will work without these manager configurations as well. Because, if we do not provide this, spring will create connection to LDAP server as anonymous user, and then will use BIND operation to authenticate and authorize user. But not all LDAP servers (vendors) supports anonymous users and also it is not secure to connect LDAP as a anonymous user.
		.managerPassword("secret"); // so we should provide this manager user (admin user) details so that spring will connect to LDAP as admin user, which is very secure and allows us to add-on additional security usiing certificates.
	}
}
