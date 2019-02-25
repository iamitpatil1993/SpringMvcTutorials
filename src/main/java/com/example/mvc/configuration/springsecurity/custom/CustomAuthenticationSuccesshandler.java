/**
 * 
 */
package com.example.mvc.configuration.springsecurity.custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * <p>This class is custom login handler, this class can be used to do post successful login custom application logic. we can do anything here, update database, update request, update session, custom redirection logic, role specific redirection logic etc.</p> 
 * 
 * <p>In order to define such a class, we need to implement AuthenticationSuccessHandler interface and provide implementation for  onAuthenticationSuccess().</p>
 * 
 * <p> Here we could have simply implemented AuthenticationSuccessHandler interface and could have provided implementation for  onAuthenticationSuccess(), which is fine in normal case,
 * but, I wanted to support 'Saved request redirection', which is, if user requested tried to access page/url which is protected and requires authentication and user is not yet authenticated, in that case spring will redirect user to login page.
 * But, since we have provided our own login handler here, we had to implement that logic to find previous request and redirect to that request (which is called 'Refere'.
 * 
 * But, spring already provides such a functionality using it's built in implementation of AuthenticationSuccessHandler SavedRequestAwareAuthenticationSuccessHandler. So, I just extended that class instead of implementing AuthenticationSuccessHandler interface,
 * and delegated request redirection to it by calling it's onAuthenticationSuccess, after my custom processing is done.
 *
 * if, our application has custom redirection rules and do not need to support to redirect saved request, then no need to extend SavedRequestAwareAuthenticationSuccessHandler, just implement AuthenticationSuccessHandler and provude custom redirection logic.

 * @author amit
 *
 */
@Component
public class CustomAuthenticationSuccesshandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationSuccesshandler.class);
	private RedirectStrategy redirectStrategy;

	public CustomAuthenticationSuccesshandler() {
		this.redirectStrategy = new DefaultRedirectStrategy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationSuccessHandler#
	 * onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		LOGGER.info("Inside onAuthenticationSuccess ...");
		LOGGER.info("User Details are, username :: {}, authorities :: {}",
				new Object[] { authentication.getPrincipal(), authentication });

		// Your custom logic goes here. Do whatever you want.
		
		
		// Since, we are providing our custom post login success handler, it's our responsibility to decide where to redirect and handle redirect.
		// We can redirect any where as per our business logic. Like, admin user should be redirected to admin console, normal user should get redirected to,
		// home page ... etc.
		
		// but in order to redirect we should use RedirectStrategy instance of spring, which helps and encapsulates logic for redirection in entire application.
		// we can declare bean of it as a singleton on web config, or we can create instance of it on the fly.
		
		// RedirectStategy is an interface, it has only one concrete implementation, which is defaultRedirectStrategy. We can create instance of it with new keyword and no-arg contructor
		
		// I am creating instance of it on the fly, we can decalre it as a notmal bean as well in web config.
		// redirectStrategy.sendRedirect(request, response, "/anyUrlYou want");
		
		// Question is, Why we need to use this class if we can redirect using response.sendRedirect()?
		// -> We need to add application context (here /mvc) to redirection url in case of response.sendRedirect(), RedirectStategy manages that for us, so we use it.
		
		
		
		
		// Now, What is below line?
		// -> if we do not want to add any redirection specific logi in this handler and just want to add other logic, or there are cases wherein we want, spring's default
		// login success url should get opened, and also if we want to open url requested before login, which was protected url (required authentication) after successfull login, so that user don't need to manyually again go to that protected url, we can delegate request to super class onAuthenticationSuccessHandler()
		super.onAuthenticationSuccess(request, response, authentication); // This is Spring's built in implementation of AuthenticationSuccessHandler, which has speciality to redirect request to resource/url which caused login or called before login.
	}
}
