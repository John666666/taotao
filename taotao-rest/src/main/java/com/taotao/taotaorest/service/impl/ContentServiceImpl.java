package com.taotao.taotaorest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.taotaorest.domain.Content;
import com.taotao.taotaorest.domain.ContentMapper;
import com.taotao.taotaorest.redis.RedisUtil;
import com.taotao.taotaorest.service.ContentService;
import com.taotao.util.JsonUtils;

@Service
@Transactional

public class ContentServiceImpl implements ContentService {

	@Resource
	private ContentMapper contentMapper;
	
	@Resource
	private RedisUtil redisUtil;
	
	@Value("${tbcontent.key}")
	private String key;

	@Override
	public List<Content> getContentByCatId(long catId) {
		//首先获取redis 中，是否存在大广告的信息
		String contentJson = (String) redisUtil.get(key+catId);
		if (!StringUtils.isEmpty(contentJson)) {
			List<Content> list = JsonUtils.jsonToList(contentJson, Content.class);
			return list;
		}
		// redis 不存在
		List<Content> list = contentMapper.selectBycatId(catId);
		// 放入redis 中
		redisUtil.set(key+catId, JsonUtils.objectToJson(list));
		return list;
	}
}
