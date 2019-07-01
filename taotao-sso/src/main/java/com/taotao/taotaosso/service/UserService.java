package com.taotao.taotaosso.service;

import com.taotao.taotaosso.domain.User;
import com.taotao.util.TaotaoResult;

public interface UserService {
	
	public Boolean dataYesOrNo(String param, int type);
	
	public TaotaoResult login(String username, String password);
	
	public TaotaoResult register(User user);
	
	public TaotaoResult getUser(String ticket);
}
