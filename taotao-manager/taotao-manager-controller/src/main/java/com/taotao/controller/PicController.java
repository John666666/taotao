package com.taotao.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PicService;
import com.taotao.service.impl.PicServiceImpl;

@Controller
@RequestMapping("/rest/pic/")
public class PicController {
	private Logger log = Logger.getLogger(PicController.class);

	@Resource
	private PicService picService;
	

	@ResponseBody
	@PostMapping(value="upload")
	public Object upload(MultipartFile uploadFile) {
		log.debug("111111111111111111111111");
		//通过PicService 执行上传操作
		return picService.upload(uploadFile);
	}
	
}
