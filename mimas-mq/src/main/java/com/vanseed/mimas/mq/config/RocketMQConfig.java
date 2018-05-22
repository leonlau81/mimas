package com.vanseed.mimas.mq.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vanseed.mimas.mq.event.RocketmqEvent;

/**
 * @author leon
 * 
 */

@Configuration 
@ConfigurationProperties(RocketMQConfig.PREFIX)
public class RocketMQConfig {
	public static final String PREFIX = "spring.rocketmq";
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value(value = "${spring.rocketmq.namesrvAddr}")
	private String namesrvAddr;
	@Value(value = "${spring.rocketmq.producerGroupName}")
	private String producerGroupName; 
	@Value(value = "${spring.rocketmq.transactionProducerGroupName}")
	private String transactionProducerGroupName; 
	@Value(value = "${spring.rocketmq.consumerGroupName}")
	private String consumerGroupName; 
	@Value(value = "${spring.rocketmq.producerInstanceName}")
	private String producerInstanceName;
	@Value(value = "${spring.rocketmq.consumerInstanceName}")
	private String consumerInstanceName;
	@Value(value = "${spring.rocketmq.producerTranInstanceName}")
	private String producerTranInstanceName;
	@Value(value = "${spring.rocketmq.consumerBatchMaxSize}")
	private int consumerBatchMaxSize;
	@Value(value = "${spring.rocketmq.consumerBroadcasting}")
	private boolean consumerBroadcasting;
	@Value(value = "${spring.rocketmq.enableHisConsumer}")
	private boolean enableHisConsumer;
	@Value(value = "${spring.rocketmq.enableOrderConsumer}")
	private boolean enableOrderConsumer;
	//@Value(value = "${spring.rocketmq.subscribe}")
	@Value("#{'${spring.rocketmq.subscribe}'.split(',')}")
	private List<String> subscribe = new ArrayList<String>();
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	private static boolean isFirstSub = true;
	private static long startTime = System.currentTimeMillis();
	
	/**
	 * 初始化向rocketmq发送普通消息的生产者
	 */
	@Bean
	@ConditionalOnProperty(prefix = RocketMQConfig.PREFIX, value = "enable")
	//@ConditionalOnBean(EtcdClient.class)
	public DefaultMQProducer defaultProducer() throws MQClientException {
		/**
		 * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ProducerGroupName需要由应用来保证唯一<br>
		 * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
		 * 因为服务器会回查这个Group下的任意一个Producer
		 */
		DefaultMQProducer producer = new DefaultMQProducer(producerGroupName);
		producer.setNamesrvAddr(namesrvAddr);
		producer.setInstanceName(producerInstanceName);
		producer.setVipChannelEnabled(false);
		producer.setRetryTimesWhenSendAsyncFailed(10);

		/**
		 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 * 注意：切记不可以在每次发送消息时，都调用start方法
		 */
		producer.start();
		logger.info("RocketMq defaultProducer Started.");
		return producer;
	}
	
	
	/**
	 * 初始化向rocketmq发送事务消息的生产者
	 */
	@Bean
	@ConditionalOnProperty(prefix = RocketMQConfig.PREFIX, value = "enable")
	//@ConditionalOnBean(EtcdClient.class)
	public TransactionMQProducer transactionProducer() throws MQClientException {
		/**
		 * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ProducerGroupName需要由应用来保证唯一<br>
		 * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
		 * 因为服务器会回查这个Group下的任意一个Producer
		 */
		TransactionMQProducer producer = new TransactionMQProducer(transactionProducerGroupName);
		producer.setNamesrvAddr(namesrvAddr);
		producer.setInstanceName(producerTranInstanceName);
		producer.setRetryTimesWhenSendAsyncFailed(10);

		// 事务回查最小并发数
		producer.setCheckThreadPoolMinSize(2);
		// 事务回查最大并发数
		producer.setCheckThreadPoolMaxSize(2);
		// 队列数
		producer.setCheckRequestHoldMax(2000);

		//TransactionCheckListener transactionCheckListener = new
		//TransactionCheckListenerImpl();
		// producer.setTransactionCheckListener(transactionCheckListener);
		/**
		 * 事务消息，broker会对没有提交的消息向producer确认，在producer上必须设置listener来相应broker的确认<br>
		 * CheckListener的主要逻辑就是确认之前发送的消息是应该commit还是rollback。
		 * 所以在消息里必须包含能检验本地事务是否提交的关键信息，比如订单id等等，可使用message的key来存储，或者放在message的body里
		 */
		producer.setTransactionCheckListener((MessageExt msg) -> {
			if(msg.getKeys()!=null && msg.getKeys().length()>0) {
				return LocalTransactionState.COMMIT_MESSAGE;
			}else {
				return LocalTransactionState.ROLLBACK_MESSAGE;
			}
		});

		/**
		 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 * 注意：切记不可以在每次发送消息时，都调用start方法
		 */
		producer.start();

		logger.info("RocketMq TransactionMQProducer Started.");
		return producer;
	}


	/**
	 * 初始化rocketmq消息监听方式的消费者
	 */
	@Bean
	@ConditionalOnProperty(prefix = RocketMQConfig.PREFIX, value = "enable")
	//@ConditionalOnBean(EtcdClient.class)
	public DefaultMQPushConsumer pushConsumer() throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroupName);
		consumer.setNamesrvAddr(namesrvAddr);
		consumer.setInstanceName(consumerInstanceName);
		if (consumerBroadcasting) {
			consumer.setMessageModel(MessageModel.BROADCASTING);
		}
		consumer.setConsumeMessageBatchMaxSize(
		consumerBatchMaxSize == 0 ? 1 : consumerBatchMaxSize);// 设置批量消费，以提升消费吞吐量，默认是1
		
		/**
		 * 订阅指定topic下tags
		 */
		List<String> subscribeList = subscribe;
		for (String sunscribe : subscribeList) {
			consumer.subscribe(sunscribe.split(":")[0], sunscribe.split(":")[1]);
		}
		if (enableOrderConsumer) {
			consumer.registerMessageListener((List<MessageExt> msgs, ConsumeOrderlyContext context) -> {
				try {
					context.setAutoCommit(true);
					msgs =filter(msgs);
					if(msgs.size()==0) return ConsumeOrderlyStatus.SUCCESS;
					this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
				} catch (Exception e) {
					e.printStackTrace();
					return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
				}
				// 如果没有return success，consumer会重复消费此信息，直到success。
				return ConsumeOrderlyStatus.SUCCESS;
			});
		} else {
			consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
				try {
					msgs=filter(msgs);
					if(msgs.size()==0) return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
				} catch (Exception e) {
					e.printStackTrace();
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;  
				}
				// 如果没有return success，consumer会重复消费此信息，直到success。
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			});
		}
	       
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);// 延迟5秒再启动，主要是等待spring事件监听相关程序初始化完成，否则，回出现对RocketMQ的消息进行消费后立即发布消息到达的事件，然而此事件的监听程序还未初始化，从而造成消息的丢失
					/**
					 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
					 */
					try {
						consumer.start();
					} catch (Exception e) {
						logger.info("RocketMq pushConsumer Start failure!!!.");
						logger.error(e.getMessage(), e);
					}
					logger.info("RocketMq pushConsumer Started.");
				} catch (InterruptedException e) {
					logger.error("error",e);
					//e.printStackTrace();
				}
			}

		}).start();

		return consumer;
	}
	   
	private List<MessageExt> filter(List<MessageExt> msgs){
		if(isFirstSub&&!enableHisConsumer){
			msgs =msgs.stream().filter(item ->startTime - item.getBornTimestamp() < 0).collect(Collectors.toList());
		}
		if(isFirstSub && msgs.size()>0){
			isFirstSub = false;
		}
		return msgs;
	}

}
