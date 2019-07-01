package com.taotao.mapper;

import java.util.List;

import com.taotao.entity.ContentCategory;

public interface ContentCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContentCategory record);

    int insertSelective(ContentCategory record);

    ContentCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContentCategory record);

    int updateByPrimaryKey(ContentCategory record);
    /**
     * 查询全部内容分类
     * @return
     */
    List<ContentCategory> selectAll(long parentid);
    /**
     * 修改parentid为0
     * @param parentid
     * @return
     */
    int updateZeroById(long parentid);
}