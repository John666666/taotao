package com.taotao.search.service;

import com.taotao.util.TaotaoResult;

public interface ItemService {

	public TaotaoResult search(String keywords,int pageNo,int  pageSize);
}
