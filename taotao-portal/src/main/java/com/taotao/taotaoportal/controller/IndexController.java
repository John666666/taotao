package com.taotao.taotaoportal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.taotaoportal.service.AadvertisingService;

@Controller
public class IndexController {

	@Resource
	private AadvertisingService aadvertisingService;
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView m = new ModelAndView("index");
		m.addObject("ad1", aadvertisingService.getContentAdver());
		return m;
	}
	
	
	
}
