package com.taotao.service;

import java.util.List;

import com.taotao.entity.ContentCategory;
import com.taotao.util.TaotaoResult;

public interface ContentCategoryService {
	/**
	 * 展示所有内容分类
	 * @return
	 */
	 public List<ContentCategory> selectContentCategoryAll(long parentid);
	 /**
	  * 动态添加商品内容分类
	  * @param parentId
	  * @param name
	  * @return
	  */
	 public TaotaoResult addContentCategory(long parentId,String name);
	 /**
	  * 重命名
	  * @param name
	  * @return
	  */
	 public ContentCategory up(long id,String name );
	 /**
	  * 删除
	  * @param id
	  * @return
	  */
	 public int delete(long id);
}
