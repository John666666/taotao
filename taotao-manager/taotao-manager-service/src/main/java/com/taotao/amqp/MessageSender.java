package com.taotao.amqp;

import org.springframework.amqp.core.AmqpTemplate;

public class MessageSender {

    private AmqpTemplate amqpTemplate;

    public AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}
	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	 private String routingKey;
	 
	
	public String getRoutingKey() {
		return routingKey;
	}
	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	/**
     * 发送消息
     * @param obj
     */
    public void sendDataToQueue(Object obj) {
        amqpTemplate.convertAndSend(this.routingKey,obj);
    }
}
