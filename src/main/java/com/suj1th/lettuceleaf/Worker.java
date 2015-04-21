package com.suj1th.lettuceleaf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Worker {

	private static Map<String, Method> methodCache = new HashMap<>();
	private static final Logger LOGGER = Logger.getLogger(Worker.class);
	
	
	/**
	 * noargs constructor
	 */
	public Worker() {
		
	}

	@SuppressWarnings("unchecked")
	public void execute(MessageBody message) {	
		String workerName = message.getTaskName();
		Method method = null;
		if(methodCache.containsKey(workerName)){
			method = methodCache.get(workerName);
			
		}else{
			
			@SuppressWarnings("rawtypes")
			Class worker = null;
			
			try {
				worker = Class.forName(workerName);
			} catch (ClassNotFoundException e) {
				LOGGER.error("Class Not Found with name "+workerName, e);
				return;
			}
			
			try {
				method = worker.getMethod("work", Message.class);
			} catch (NoSuchMethodException e) {
				LOGGER.error("'work()' method not defined in Worker class "+workerName, e);
				return;
			} catch (SecurityException e) {
				LOGGER.error("Security Exception!!!",e);
				return;
			}
		}
		
		try {
			method.invoke(null, message);
		} catch (IllegalAccessException e) {
			LOGGER.error("IllegalAccessException !!!",e);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Argument passed to 'Worker.work()' should be of type Message",e);
		} catch (InvocationTargetException e) {
			LOGGER.error("'Worker.work()' throws an unhandled exception",e);
		}
		
		return;
	}

}
