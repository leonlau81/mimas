package com.vanseed.mimas.mq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaProducer {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private KafkaTemplate<String, String> template;

	@RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
	public void sendMsg() {
		String topic ="kfkTopic";
		try {
			template.send(topic, "message_1");
			template.send(topic, "message_2");
			template.send(topic, "message_3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
