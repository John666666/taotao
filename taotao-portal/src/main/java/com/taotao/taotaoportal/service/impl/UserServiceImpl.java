package com.taotao.taotaoportal.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.taotaoportal.pojo.User;
import com.taotao.taotaoportal.service.UserService;
import com.taotao.taotaoportal.util.CookieUtils;
import com.taotao.util.HttpClientUtil;
import com.taotao.util.TaotaoResult;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${check.url}")
	private String checkUrl;
	
	@Value("${register.url}")
	private String registerUrl;
	
	@Value("${login.url}")
	private String loginUrl;
	
	@Value("${getUser.url}")
	private String getUserUrl;
	

	@Override
	public String check(String param, int type) {
		String url = checkUrl + "/" + param + "/" + type;
		System.out.println(registerUrl);
		System.out.println(url);
		String str = HttpClientUtil.doGet(url);
		System.out.println(str);
		return str;
	}
	

	@Override
	public TaotaoResult register(User user) {
		Map<String, String> map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		String str = HttpClientUtil.doPost(registerUrl, map);
		System.out.println(str);
		TaotaoResult result = TaotaoResult.format(str);
		if (result.getStatus() == 200) {
			return TaotaoResult.ok();
		} else {
			return TaotaoResult.build(400, "注册失败");
		}
		
	}


	@Override
	public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		map.put("u", username);
		map.put("p", password);
		String str = HttpClientUtil.doPost(loginUrl, map);
		TaotaoResult taotaoResult = TaotaoResult.format(str);
		String uuid = (String) taotaoResult.getData();
		CookieUtils.setCookie(request, response, "loginCookiekey", uuid, 30 * 60 * 1000, true);
		return taotaoResult;
	}


	@Override
	public TaotaoResult getUser(String ticket) {
		Map<String, String> map = new HashMap<>();
		map.put("ticket", ticket);
		String str = HttpClientUtil.doPost(getUserUrl, map);
		TaotaoResult taotaoResult = TaotaoResult.format(str);
		return taotaoResult;
	}


	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, "loginCookiekey");
	}

}
