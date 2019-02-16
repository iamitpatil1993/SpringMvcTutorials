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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

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
		.antMatchers("/spittles/**").hasRole("SPITTLE") // this says authenticate request to urls matching this url, and user must have role of 'SPITLE'
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
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication().dataSource(dataSource) // use daatasource to get user details 
		.passwordEncoder(NoOpPasswordEncoder.getInstance()); // need to configure passwordEncoder as well.
	}
}
