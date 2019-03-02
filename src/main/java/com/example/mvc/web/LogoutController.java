package com.example.mvc.web;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

	/**
	 * This handler demonstrates logout from application programmatically. Here we are not relying on spring security built-in logout handler.
	 * We are programmatically handling complete logout functionality by using spring security Auth APIs.
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@GetMapping
	//public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, principal principal) throws ServletException { // we can do this as well, but principal does not gives us much information other than username, so below is more useful
	public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @AuthenticationPrincipal UserDetails userDetails) throws ServletException {

		/**
		 * Principal object gives us only username.
		 * String usernaem = principal.getName();
		 */
		
		/* @AuthenticationPrincipal injects instance of UserDetails used during Authentication. we can use it here.
		 * Since, UserDetails object is one used at authentication, if we have custom user details class which has additional fields then, we can cast object to our type and get additional information from object.
		 * 
		 * For example, in case of LDAP authentication, instance will be of type LdapUserDetils, so we can cast this object and get Ldap specific details from it.
		*/
		System.out.println("username :: "  + userDetails.getUsername());
		
		try {
			LdapUserDetails  userDetails2 = (LdapUserDetails) userDetails;
			System.out.println("userDetails :: " + userDetails2.getUsername());	
		}catch (ClassCastException e) {
			// if implementation class is not of type LDAP (in case we are using different authentication method) above casting will break.
			// So, we should not depend on impelemetation class unless it is guaranted that we won't change Authentication provider LDAP
		}

		/* as per https://docs.spring.io/spring-security/site/docs/3.2.1.CI-SNAPSHOT/reference/htmlsingle/#servletapi-logout
			spring have added support to HttpServletrequest.lougout method. So, calling logout method on httpServletRequest causes session termination and remove remember me tokens
			In Servlet 3.0 container Spring logout functionality is integrated with servlet and you just invoke logout() on your HttpServletRequest. Still need to write valid response content.
		*/
		httpServletRequest.logout(); // works only in Servlet container version >= 3.0
	
		// we can achieve logout by using below mentioned spring APIs as well.
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
		}*/

		// Writing response/redirection is completely our responsibility and we need to manage it as per our needs.
		return "redirect:login?logout";
	}
}
