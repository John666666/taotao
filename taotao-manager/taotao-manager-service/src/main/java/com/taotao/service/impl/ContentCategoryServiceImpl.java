package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.entity.ContentCategory;
import com.taotao.mapper.ContentCategoryMapper;
import com.taotao.service.ContentCategoryService;
import com.taotao.util.TaotaoResult;

@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Resource
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public List<ContentCategory> selectContentCategoryAll(long parentid) {
		List<ContentCategory> contentCategory = contentCategoryMapper.selectAll(parentid);
		return contentCategory;
	}

	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		Date date = new Date();
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setParentId(parentId);
		ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		parent.setIsParent(true);
		contentCategoryMapper.updateByPrimaryKeySelective(parent);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setName(name);
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		contentCategoryMapper.insertSelective(contentCategory);
		return TaotaoResult.ok();
	}

	@Override
	public ContentCategory up(long id,String name) {
		ContentCategory	contentCategory = new ContentCategory();
		 contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		Date date = new Date();
		contentCategory.setUpdated(date);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return contentCategory;
	}

	@Override
	public int delete(long id) {
		long parentId = contentCategoryMapper.selectByPrimaryKey(id).getParentId();
		List<ContentCategory> list = contentCategoryMapper.selectAll(parentId);
		if(list.size() <= 1) {
			contentCategoryMapper.deleteByPrimaryKey(id);
			contentCategoryMapper.updateZeroById(parentId);
		}else {
			contentCategoryMapper.deleteByPrimaryKey(id);
		}
		deleteson(id);
		return 0;
	}
	
	void deleteson(long id) {
		List<ContentCategory> list1 = contentCategoryMapper.selectAll(id);
		if(list1.size() != 0) {
			for (ContentCategory contentCategory : list1) {
				deleteson( contentCategory.getId());
				contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
			}
		}
	}

}
