package com.ifly.upload.controller;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ifly.upload.constant.Constant;
import com.ifly.upload.utility.Utility;

@Controller
public class UploadProgressController {
	
	private static final Logger logger = Logger
		.getLogger(MethodHandles.Lookup.class);
	
	@RequestMapping(value="/uploadProgressSingleFile",method=RequestMethod.POST)
	public void uploadProgressSingleFile(@RequestParam("file")MultipartFile mf,HttpServletResponse response){
		if (!mf.isEmpty()) {
			try {
				Utility.makeDirectory(Constant.UPLOAD_DIRECTORY);
				String  fileName = mf.getOriginalFilename();
				FileCopyUtils.copy(mf.getBytes(),new File(Constant.UPLOAD_DIRECTORY + fileName));
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		Utility.write(response,"upload success");
	}
	
	@RequestMapping(value="/uploadProgressMultiFile",method=RequestMethod.POST)
	public void uploadProgressMultiFile(HttpServletRequest request,HttpServletResponse response){
		try {
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)request;
			MultiValueMap<String,MultipartFile> multiFileMap = mhsr.getMultiFileMap();
			if (null != multiFileMap) {
				Utility.makeDirectory(Constant.UPLOAD_DIRECTORY);
				
				for (Map.Entry<String,List<MultipartFile>> entry : multiFileMap.entrySet()) {
					for (MultipartFile mf : entry.getValue()) {
						String fileName = mf.getOriginalFilename();
						FileCopyUtils.copy(mf.getBytes(),new File(Constant.UPLOAD_DIRECTORY + fileName));
					}
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		Utility.write(response,"upload success");
	}
	
}
