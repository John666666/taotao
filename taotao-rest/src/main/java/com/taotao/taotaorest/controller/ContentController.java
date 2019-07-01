package com.taotao.taotaorest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.taotaorest.domain.Content;
import com.taotao.taotaorest.service.ContentService;
import com.taotao.util.TaotaoResult;

@RestController
@RequestMapping("content")
public class ContentController {

	@Resource
	private ContentService contentService;
	@GetMapping("{cid}")
	public Object BigAdvertising(@PathVariable long cid) {
		List<Content> list = contentService.getContentByCatId(cid);
		return TaotaoResult.ok(list);
	}
	
}
