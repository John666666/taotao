package com.taotao.mapper;

import org.springframework.stereotype.Repository;

import com.taotao.entity.Item;
@Repository
public interface ItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}