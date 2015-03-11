package com.suj1th.lettuceleaf;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author suj1th
 *
 */
public class LettuceLeaf {
	/*
	 * TODO: Configure Log file for Logger.
	 * 
	 */
	private final static Logger LOGGER = Logger.getLogger(LettuceLeaf.class);
	private static List<HashMap<String, Object>> queueProperties = null;
	
	private LettuceLeaf(){
		
	}
	
	public static void main(String[] args){
		taskManager();
	}
	
	
	public static void taskManager(){
		
		LOGGER.info("LettuceLeaf Version 1.0.0");
		queueProperties = getQueues();
		for(HashMap<String, Object> queueProperty:queueProperties){
			
			Queue queueInstance = new Queue(queueProperty);
			Consumer consumer = new Consumer(queueInstance);
			
			String taskName = queueInstance.getName();
			int taskPriority = queueInstance.getPriority();
			
			Thread task = new Thread(consumer);
			task.setName(taskName);
			task.setPriority(taskPriority);
			task.start();
			LOGGER.info("Consumer Thread for Queue "+taskName+" started.");
		}
		
	}

	private static List<HashMap<String, Object>> getQueues() {
		return LettuceLeafConfiguration.getQueues();	
	}

}
