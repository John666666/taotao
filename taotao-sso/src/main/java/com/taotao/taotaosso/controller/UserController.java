package com.taotao.taotaosso.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.taotaosso.domain.User;
import com.taotao.taotaosso.service.UserService;
import com.taotao.util.TaotaoResult;

@RestController
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;
	
	@GetMapping("{param}/{type}")
	public boolean dataYesOrNo(@PathVariable String param, @PathVariable int type) {
		return userService.dataYesOrNo(param, type);
	}
	
	
	@PostMapping("register")
	public TaotaoResult register(User user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		return userService.register(user);
	}
	
	@PostMapping("login")
	public TaotaoResult login(@RequestParam("u") String username, @RequestParam("p") String password) {
		return userService.login(username, password);
	}
	
	@PostMapping("getUser")
	public TaotaoResult getUser(@RequestParam("ticket") String ticket) {
		return userService.getUser(ticket);
	}

}
