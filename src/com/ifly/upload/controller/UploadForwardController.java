package com.ifly.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploadForwardController {

	@RequestMapping(value = "/forward2UploadServlet",method = RequestMethod.GET)
	public String forward2UploadServlet(){
		return "uploadServlet";
	}
	
	@RequestMapping(value = "/forward2UploadSpringMvc",method = RequestMethod.GET)
	public String forward2UploadSpringMvc(){
		return "uploadSpringMvc";
	}
	
	@RequestMapping(value = "/forward2UploadAjax",method = RequestMethod.GET)
	public String forward2UploadAjax(){
		return "uploadAjax";
	}
	
	@RequestMapping(value = "/forward2UploadProgressSingle",method = RequestMethod.GET)
	public String forward2UploadProgress(){
		return "uploadProgressSingle";
	}
	
	@RequestMapping(value = "/forward2UploadProgressMulti",method = RequestMethod.GET)
	public String forward2UploadProgressMulti(){
		return "uploadProgressMulti";
	}

}
