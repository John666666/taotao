package com.taotao.taotaoportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

	//http://localhost:11000/page/index
	@RequestMapping("{page}")
	public String page(@PathVariable String page ) {
		return page;
	}
	
	
}
