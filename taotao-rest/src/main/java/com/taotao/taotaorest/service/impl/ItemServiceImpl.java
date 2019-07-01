package com.taotao.taotaorest.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues="messageQueue")
public class ItemServiceImpl {

	@RabbitHandler
	public void showItemInfo(String itemId) {
		System.out.println("接收者："+itemId);
	}
}
