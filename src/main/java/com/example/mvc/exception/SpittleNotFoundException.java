package com.example.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * 1. This annotation is not specific to exception handling, rather, it is general purpose annotation <br/>
 * 2. This annotation cane be used on controller method to indicate which http status code it sends and it can also be <br/>
 * 		used on exception classes to map http status code and message to be send to response if particular exception occured while processing the request.<br/>
 * 3. When used on controller method it sends configured http status code with data and view specified in controller. (if do not specific reason attribute of this annotation)<br/>
 * 4. If we set, reason attribute of this annotation, then it sends configured http status code along with message in reason attribute. In this case, it writes response using javax.servlet.http.HttpServletResponse.sendError(int sc, java.lang.String msg) method.
 * 			<br/> and since if we use sendError method of servler response object, response stream get closed and flushed and we can not send any data to response. So, in this case we can not send any data to client only message with reason attribute.
 * </p>
 *  <p>
 *  <b>Drawbacks:</b> <br/>
 *  1. We can not make this reason message dynamic and it will be static always. <br/>
 *  2. We can not send custom view to be rendered, container will display default or configured html error page.<br/>
 *  3. We can not send additional request's contextual data to display error in more detailed way.
 *  </p>
 **/
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Spittle not found")
public class SpittleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2996800538675694145L;
	
	public SpittleNotFoundException() {
		super();
	}

	public SpittleNotFoundException(String message) {
		super(message);
	}
}
