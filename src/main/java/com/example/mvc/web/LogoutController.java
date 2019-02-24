package com.example.mvc.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException {
		
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
