package com.taotao.taotaosso.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.taotaosso.domain.User;
import com.taotao.taotaosso.domain.UserMapper;
import com.taotao.taotaosso.redis.RedisUtil;
import com.taotao.taotaosso.service.UserService;
import com.taotao.util.JWT;
import com.taotao.util.MD5;
import com.taotao.util.TaotaoResult;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RedisUtil redisUtil;

	@Override
	public TaotaoResult login(String username, String password) {
		User user = userMapper.selectUserByUsernamAndPassword(username, MD5.MD5Encode(password));
		if (user != null) {
			// 生成ticket
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			// 加密把user存到redis中, key = ticket, value = user
			redisUtil.set(uuid, JWT.sign(user, 30 * 60 * 1000), 30 * 60);
			
			return TaotaoResult.ok(uuid);
		}
		return TaotaoResult.build(400, "用户名或密码错误!");
	}

	@Override
	public Boolean dataYesOrNo(String param, int type) {
		User user = userMapper.selectYesOrNo(param, type);
		if (user != null) {
			return false;
		}
		return true;
	}

	@Override
	public TaotaoResult register(User user) {
		user.setPassword(MD5.MD5Encode(user.getPassword()));
		System.out.println(user);
		int row = userMapper.insertSelective(user);
		if (row > 0) {
			return TaotaoResult.ok();
		}
		return TaotaoResult.build(400, "注册失败. 请校验数据后请再提交数据.", null);
	}

	@Override
	public TaotaoResult getUser(String ticket) {
		String str = (String) redisUtil.get(ticket);
		if (StringUtils.isNoneEmpty(str)) {
			return TaotaoResult.ok(JWT.unsign(str, User.class));
		}
		return TaotaoResult.build(400, "登录信息已过期, 请重新登陆!");
	}


}
