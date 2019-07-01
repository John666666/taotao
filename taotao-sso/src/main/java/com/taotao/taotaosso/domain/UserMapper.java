package com.taotao.taotaosso.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.taotao.taotaosso.domain.User;

@Mapper
public interface UserMapper {
	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectUserByUsernamAndPassword(@Param("username") String username, @Param("password") String password);

	User selectYesOrNo(@Param("param") String param, @Param("type") int type);

}