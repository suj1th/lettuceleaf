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
	private static IThreadPoolManager threadPoolManager;
	
	/**
	 *  noargs constructor
	 */
	private LettuceLeaf(){
		super();
		setThreadPoolManager(new ThreadPoolManager());
	}
	
	/**
	 * @return threadPoolManager
	 */
	private static IThreadPoolManager getThreadPoolManager() {
		return threadPoolManager;
	}

	/**
	 * @param threadPoolManager
	 */
	private static void setThreadPoolManager(IThreadPoolManager threadPoolManager) {
		LettuceLeaf.threadPoolManager = threadPoolManager;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args){
		LettuceLeaf.taskManager();
	}
	
	
	/**
	 * initiates LettuceLeaf master.
	 * @author suj1th
	 */
	private static void taskManager(){
		
		LOGGER.info("LettuceLeaf Version 0.1.0");
		queueProperties = getQueues();
		for(HashMap<String, Object> queueProperty:queueProperties){
			
			Queue queueInstance = new Queue(queueProperty);
			Consumer consumer = new Consumer(queueInstance);
			
			getThreadPoolManager().execute(consumer);
			LOGGER.info("Consumer Thread for Queue "+queueInstance.getName()+" started.");
		}
		
	}

	private static List<HashMap<String, Object>> getQueues() {
		return LettuceLeafConfiguration.getQueues();	
	}

}
