package com.suj1th.lettuceleaf;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

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
    
    @SuppressWarnings("rawtypes")
	private Class type ;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Message(Delivery deliveredMessage) {
		type=(Class) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		MessageBody temp = null;
		try {
			temp = mapper.readValue(deliveredMessage.getBody(), type);
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
