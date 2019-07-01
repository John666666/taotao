package com.taotao.taotaorest.service;

import java.util.List;

import com.taotao.taotaorest.domain.Content;

public interface ContentService {

	/**
	 * 根据内容分类Id获取内容信息
	 * 打广告   id 89
	 * @param catId
	 * @return
	 */
	public List<Content> getContentByCatId(long catId);
	
	
}
