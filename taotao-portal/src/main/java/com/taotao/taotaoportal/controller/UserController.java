package com.taotao.taotaoportal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.taotaoportal.pojo.User;
import com.taotao.taotaoportal.service.UserService;
import com.taotao.util.TaotaoResult;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@PostMapping("register")
	public TaotaoResult register(User user) {
		return userService.register(user);
	}
	
	@ResponseBody
	@GetMapping("check/{param}/{type}")
	public Object check(@PathVariable String param, @PathVariable int type) {
		Map<String, Boolean> map = new HashMap<>();
		if (userService.check(param, type).equals("true")) {
			map.put("data", true);
		} else {
			map.put("data", false);
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("login")
	public TaotaoResult login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		return userService.login(username, password, request, response);
	}
	
	
	@ResponseBody
	@GetMapping("getUser/{ticket}")
	public TaotaoResult getUser(@PathVariable("ticket") String ticket) {
		TaotaoResult taotaoResult =  userService.getUser(ticket);
		return taotaoResult;
	}
	
	
	@GetMapping("logout")
	public Object logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		return "redirect:http://www.taotao.com";
	}

}
