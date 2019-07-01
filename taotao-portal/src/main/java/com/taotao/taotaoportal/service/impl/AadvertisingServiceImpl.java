package com.taotao.taotaoportal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.taotaoportal.pojo.Content;
import com.taotao.taotaoportal.service.AadvertisingService;
import com.taotao.util.HttpClientUtil;
import com.taotao.util.JsonUtils;
import com.taotao.util.TaotaoResult;

@Service
public class AadvertisingServiceImpl implements AadvertisingService {

	@Value("${advertising.url}")
	private String url ;
	
	@Override
	public String getContentAdver() {
		String content = HttpClientUtil.doGet(url);
		List<Map<String,Object>> maps = new ArrayList<>();

		if (!StringUtils.isEmpty(content)) {
			TaotaoResult result = TaotaoResult.formatToList(content, Content.class);
			List<Content> list = (List<Content>) result.getData();
			
			// 原始数据封装成页面上需要的数据
			for (Content c : list) {
				Map<String,Object> map = new HashMap<>();
				map.put("srcB", c.getPic());
				map.put("height", 240);
				map.put("alt", c.getTitle());
				map.put("width", 670);
				map.put("src", c.getPic());
				map.put("widthB", 550);
				map.put("href", c.getUrl());
				map.put("heightB", 240);
				maps.add(map);
			}
		}
		String result = JsonUtils.objectToJson(maps);
		return result;
	}

}
