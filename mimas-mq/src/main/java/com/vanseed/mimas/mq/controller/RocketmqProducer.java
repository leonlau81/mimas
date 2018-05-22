package com.vanseed.mimas.mq.controller;

import java.util.List;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocket")
public class RocketmqProducer {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DefaultMQProducer defaultProducer;

	@Autowired
	private TransactionMQProducer transactionProducer;

	private int i = 0;

	@RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
	public void sendMsg() {
		Message msg = new Message("TopicTest1", // topic
				"TagA", // tag
				"OrderID00" + i, // key
				("Hello rocket mq" + i).getBytes());// body
		try {
			defaultProducer.send(msg, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					logger.info(sendResult.toString());
					// TODO 发送成功处理
				}

				@Override
				public void onException(Throwable e) {
					System.out.println(e);
					// TODO 发送失败处理
				}
			});
			i++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/sendTransactionMsg", method = RequestMethod.GET)
	public String sendTransactionMsg() {
		SendResult sendResult = null;
		try {
			// 构造消息
			Message msg = new Message("TopicTest1", // topic
					"TagA", // tag
					"OrderID001", // key
					("Hello rocket mq").getBytes());// body

			// 发送事务消息，LocalTransactionExecute的executeLocalTransactionBranch方法中执行本地逻辑
			sendResult = transactionProducer.sendMessageInTransaction(msg, (Message msg1, Object arg) -> {
				int value = 1;
				// TODO 执行本地事务，改变value的值
				/**
				 * 可以调用本地的service来执行事务，根据是否抛出异常来判断是提交消息还是回滚消息。<br>
				 * 对service方法检查异常，捕获到任何一场都需要处理。
				 */
				value = 5;
				// ===================================================
				logger.info("执行本地事务。。。完成");
				if (arg instanceof Integer) {
					value = (Integer) arg;
				}
				// ===================================================

				if (value == 0) {
					throw new RuntimeException("Could not find db");
				} else if ((value % 5) == 0) {
					return LocalTransactionState.ROLLBACK_MESSAGE;
				} else if ((value % 4) == 0) {
					return LocalTransactionState.COMMIT_MESSAGE;
				}
				return LocalTransactionState.ROLLBACK_MESSAGE;
			}, 4);
			System.out.println(sendResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendResult.toString();
	}

	@RequestMapping(value = "/sendMsgOrder", method = RequestMethod.GET)
	public void sendMsgOrder() {
		Message msg = new Message("TopicTest1", // topic
				"TagA", // tag
				"OrderID00" + i, // key
				("Hello rocket mq" + i).getBytes());// body
		try {
			defaultProducer.send(msg, new MessageQueueSelector() {
				@Override
				public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
					logger.info("MessageQueue" + arg);
					int index = ((Integer) arg) % mqs.size();
					return mqs.get(index);
				}
			}, i);// i==arg
			i++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
