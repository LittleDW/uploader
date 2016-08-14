package com.ifly.upload.controller;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ifly.upload.constant.Constant;
import com.ifly.upload.utility.Utility;

@Controller
public class UploadSpringMvcController {
	
	private static final Logger logger = Logger
		.getLogger(MethodHandles.Lookup.class);
	
	@RequestMapping(value = "/uploadSpringMvcSingleFile",method = RequestMethod.POST)
	public String uploadSingleFile(@RequestParam("file")MultipartFile mf,ModelMap modelMap){
		String fileName = "";
		modelMap.addAttribute("file",mf);
		if (!mf.isEmpty()) {
			try {
				Utility.makeDirectory(Constant.UPLOAD_DIRECTORY);
				fileName = mf.getOriginalFilename();
				FileCopyUtils.copy(mf.getBytes(),new File(Constant.UPLOAD_DIRECTORY + fileName));
				/*BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(new File(Constant.UPLOAD_DIRECTORY + fileName)));
				bos.write(mf.getBytes());
				bos.flush();
				bos.close();*/
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return "uploadSpringMvcResult";
	}
	
	@RequestMapping(value = "/uploadSpringMvcMultiFile",method = RequestMethod.POST)
	public String uploadMultiFile(@RequestParam("mfArr")MultipartFile[] mfArr,ModelMap modelMap){
		String fileName = "";
		modelMap.addAttribute("mfArr",mfArr);
		if (null != mfArr && mfArr.length > 0) {
			try {
				Utility.makeDirectory(Constant.UPLOAD_DIRECTORY);
				for (MultipartFile mf : mfArr) {
					fileName = mf.getOriginalFilename();
					FileCopyUtils.copy(mf.getBytes(),new File(Constant.UPLOAD_DIRECTORY + fileName));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return "uploadSpringMvcResult";
	}
	
}
