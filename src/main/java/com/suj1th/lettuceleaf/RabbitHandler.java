package com.suj1th.lettuceleaf;

import com.rabbitmq.client.Channel;

/*
 * TODO: Each RabbitHandler instance should represent an independent Connection to RabbitMQ.
 */
public class RabbitHandler {

	public RabbitHandler(Queue queueInstance) {
		/*
		 * TODO: Use fields in Queue to initialize a RabbitMQ Connection.
		 */
	}

	public Channel getChannel() {
		/*
		 * TODO: Return a Channel over the existing Connection.
		 */
		return null;
	}

}
