package com.suj1th.lettuceleaf;

import java.io.IOException;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Message{
	
	private final Envelope envelope;
    private final AMQP.BasicProperties properties;
    private final  MessageBody body;
    private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
    private final static Logger LOGGER = Logger.getLogger(Message.class);
    
	
	public Message(Delivery deliveredMessage) {
		MessageBody temp = null;
		try {
			temp = mapper.readValue(deliveredMessage.getBody(), MessageBody.class);
		} catch (IOException e) {
			LOGGER.error("Jackson Parsing Error for Message "+ deliveredMessage, e);
		}
		if(temp!=null){
			body = temp;
		}else{
			body =  new MessageBody();
		}
		
		properties = deliveredMessage.getProperties();
		envelope = deliveredMessage.getEnvelope();
		
	}

	public Envelope getEnvelope() {
		return envelope;
	}

	public AMQP.BasicProperties getProperties() {
		return properties;
	}

	public MessageBody getBody() {
		return body;
	}

	public String getWorkerName() {
		return this.getBody().getTaskName();
	}

}
