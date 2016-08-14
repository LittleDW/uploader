package com.ifly.upload.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ifly.upload.constant.Constant;
import com.ifly.upload.utility.Utility;

@WebServlet(value="/uploadServlet")
@MultipartConfig(maxFileSize = Constant.MAX_FILE_SIZE,
				 maxRequestSize = Constant.MAX_REQUEST_SIZE,
				 fileSizeThreshold = Constant.FILE_SIZE_THRESHOLD)
public class UploadServletServlet extends HttpServlet {

	private static final long serialVersionUID = -6281579443389544621L;
	
	private String getFileName(Part part){
		String cd = part.getHeader(Constant.CONTENT_DISPOSITION);
		String[] itemArr = cd.split(Constant.SEMICOLON);
		for (String item : itemArr) {
			if (null != item && item.trim().startsWith(Constant.FILE_NAME)) {
				return item.substring(item.indexOf(Constant.EQUAL) + 2,item.length() - 1);
			}
		}
		return "";
	}
	
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response)throws ServletException,IOException{
		Utility.makeDirectory(Constant.UPLOAD_DIRECTORY);
		for (Part part : request.getParts()) {
			String fileName = getFileName(part);
			part.write(Constant.UPLOAD_DIRECTORY + fileName);
		}
		request.setAttribute("msg","upload success");
		String url = "/WEB-INF/view/uploadServletResult.jsp";
		getServletContext().getRequestDispatcher(url).forward(request,response);
	}
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response)throws ServletException,IOException{
		this.doPost(request,response);
	}


}
