package com.taotao.taotaoportal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.taotaoportal.pojo.User;
import com.taotao.util.TaotaoResult;

public interface UserService {
	
	public TaotaoResult register(User user);
	
	public String check(String param, int type);
	
	public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

	public TaotaoResult getUser(String uuid);
	
	public void logout(HttpServletRequest request, HttpServletResponse response);
}
