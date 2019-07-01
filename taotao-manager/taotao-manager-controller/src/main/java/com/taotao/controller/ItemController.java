package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.amqp.MessageSender;
import com.taotao.entity.Item;
import com.taotao.service.ItemService;
import com.taotao.util.JsonUtils;

@Controller
@RequestMapping("item")
public class ItemController {

	@Resource
	private ItemService itemService;
	
	@Resource
	private MessageSender messageSender;
	
	@ResponseBody
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public Object itemById(@PathVariable("id")long id) {
		messageSender.setRoutingKey("message.select.itemId");
		Item item =  itemService.findItemById(id);
		String json = JsonUtils.objectToJson(item);
		messageSender.sendDataToQueue(json);
		
		return itemService.findItemById(id);
	}
}
