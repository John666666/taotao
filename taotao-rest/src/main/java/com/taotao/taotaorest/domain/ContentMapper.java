package com.taotao.taotaorest.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);
    
    /**
     * 根据内容分类获取内容信息
     * @param categoryId
     * @return
     */
    List<Content> selectBycatId(long categoryId);
    
    
}