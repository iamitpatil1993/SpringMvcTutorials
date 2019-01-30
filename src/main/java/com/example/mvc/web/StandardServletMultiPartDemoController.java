package com.example.mvc.web;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * This controller is to demonstrate multi-part file upload using Servlet 3.0 built in support for multipart form submission.
 * @author amit
 *
 */

@Controller
@RequestMapping(path = {"/files/upload"})
public class StandardServletMultiPartDemoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardServletMultiPartDemoController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String uploadMultipartFile(@RequestPart("file") MultipartFile file) throws IllegalStateException, IOException {
		LOGGER.info("Inside file upload handler...");
		LOGGER.info("Recieved file, name :: {}, contentType :: {}, size :: {}", new Object[] {file.getOriginalFilename(), file.getContentType(), file.getSize()});
		File filePath = new File("/home/amit/" + file.getOriginalFilename());
		file.transferTo(filePath);
		LOGGER.info("Transfered file to path :: {}", filePath.getPath());
		return "File uploaded successfully";
	}
}
