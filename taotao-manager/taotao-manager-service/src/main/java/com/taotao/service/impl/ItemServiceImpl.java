package com.taotao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.entity.Item;
import com.taotao.mapper.ItemMapper;
import com.taotao.service.ItemService;
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Resource
	private ItemMapper itemMapper;
	
	@Override
	public Item findItemById(long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

}
