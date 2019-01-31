package com.example.mvc.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLogger implements ServletRequestListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogger.class);
	private static final String SEPARATOR = "-----------------------------------";	
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
		LOGGER.info(SEPARATOR + "Logging returning request for URI :: " + httpServletRequest.getRequestURI() + " METHOD :: " + httpServletRequest.getMethod());
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
		LOGGER.info(SEPARATOR + "Logging incoming request for URI :: " + httpServletRequest.getRequestURI() + " METHOD :: " + httpServletRequest.getMethod());
	}
}
