package com.suj1th.lettuceleaf;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * @author suj1th
 *
 */
public class Consumer implements Runnable {
	
	
	/*
	 * TODO
	 * Implement a custom implementation of com.rabbitmq.client.Consumer, instead of 
	 * using QueueingConsumer.
	 * */
	private QueueingConsumer queueingConsumer;
	private RabbitHandler rabbitHandler;
	private IThreadPoolManager threadPoolManager;

	private static final Logger LOGGER = Logger.getLogger(Consumer.class);
	
	
	/**
	 * @param queueInstance
	 */
	public Consumer(Queue queueInstance) {
		
		super();
		
		/*
		 * TODO
		 * Each Consumer now initiates a new RabbitHandler, which is an abstraction
		 * for a RabbitMq Connection. Thus, a new Connection is created for each 
		 * Queue in Rabbit.This model needs to be fixed to take advantage of Channels
		 * 
		 */
		rabbitHandler = new RabbitHandler(queueInstance);
		Channel channel = rabbitHandler.getChannel();
		queueingConsumer = new QueueingConsumer(channel);
		threadPoolManager = new ThreadPoolManager();
		
	}

	public void run() {
		
		QueueingConsumer.Delivery deliveredMessage =null;
		while (true) {
			try {
				deliveredMessage = this.getQueueingConsumer().nextDelivery();
			} catch (ShutdownSignalException e) {
				LOGGER.error("Connection/Channel " + e.getReference()
						+ " Shutdown to RabbitMQ", e);
			} catch (ConsumerCancelledException e) {
				LOGGER.error(e);
			} catch (InterruptedException e) {
				LOGGER.error("Connection Thread to RabbitMQ interrupted", e);
			}
			Message message = new Message(deliveredMessage);
			this.callWorker(message);
		}
		
	}

	private void callWorker(Message message) {
		Worker worker = new Worker(message.getBody());
		this.getThreadPoolManager().execute(worker);
		
	}

	public QueueingConsumer getQueueingConsumer() {
		return queueingConsumer;
	}

	public void setQueueingConsumer(QueueingConsumer queueingConsumer) {
		this.queueingConsumer = queueingConsumer;
	}

	public IThreadPoolManager getThreadPoolManager() {
		return threadPoolManager;
	}

	public void setThreadPoolManager(IThreadPoolManager threadPoolManager) {
		this.threadPoolManager = threadPoolManager;
	}

}
