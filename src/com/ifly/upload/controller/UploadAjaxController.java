package com.ifly.upload.controller;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;

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
public class UploadAjaxController {
	
	private static final Logger logger = Logger
		.getLogger(MethodHandles.Lookup.class);
	
	@RequestMapping(value="/uploadAjaxSingle",method=RequestMethod.POST)
	public void ajaxUpload(@RequestParam("file")MultipartFile mf,HttpServletResponse response){
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
	
	@RequestMapping(value="/uploadAjaxMultiple",method=RequestMethod.POST)
	public void ajaxHtml5Upload(HttpServletRequest request,HttpServletResponse response){
		try {
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)request;
			MultiValueMap<String,MultipartFile> multiFileMap = mhsr.getMultiFileMap();
			if (null != multiFileMap) {
				Utility.makeDirectory(Constant.UPLOAD_DIRECTORY);
				
				/*for (Map.Entry<String,List<MultipartFile>> entry : multiFileMap.entrySet()) {
					for (MultipartFile mf : entry.getValue()) {
						String fileName = mf.getOriginalFilename();
						FileCopyUtils.copy(mf.getBytes(),new File(Constant.UPLOAD_DIRECTORY + fileName));
					}
				}*/
				
				Iterator<String> iter = multiFileMap.keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					List<MultipartFile> mfList = multiFileMap.get(key);
					for (MultipartFile mf : mfList) {
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
