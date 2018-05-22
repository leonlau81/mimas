package com.vanseed.mimas.mq.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RocketmqListener {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@EventListener(condition = "#event.msgs[0].topic=='TopicTest1' && #event.msgs[0].tags=='TagA'")
	public void rocketmqMsgListen(RocketmqEvent event) {
		//DefaultMQPushConsumer consumer = event.getConsumer();
		try {
			logger.info("com.guosen.client.controller.consumerDemo监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
			// TODO 进行业务处理
			logger.info("=================================");
			logger.info("=======  i have a message  ======");
			logger.info("=================================");
		} catch (Exception e) {
			logger.error("error", e);
		}
	}
}

