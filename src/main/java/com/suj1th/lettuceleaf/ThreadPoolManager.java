package com.suj1th.lettuceleaf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import org.apache.log4j.Logger;

/**
 * Provides a pool of {@link Consumer} threads.
 * @author suj1th
 *
 */
public class ThreadPoolManager implements IThreadPoolManager {

	private ExecutorService threadPoolExecutor;
	private static final Logger LOGGER = Logger.getLogger(ThreadPoolManager.class);
	
	public ThreadPoolManager(){
		super();
		this.setThreadPoolExecutor(Executors.newCachedThreadPool());
	}
	
	
	private ExecutorService getThreadPoolExecutor() {
		return threadPoolExecutor;
	}


	private void setThreadPoolExecutor(ExecutorService threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
	}


	/* (non-Javadoc)
	 * @see com.suj1th.lettuceleaf.IThreadPoolManager#execute(java.lang.Runnable)
	 */
	@Override
	public void execute(Runnable runnable){
		try {
			 this.getThreadPoolExecutor().execute(runnable);
		} catch (RejectedExecutionException e) {
			LOGGER.error("Consumer " + runnable + " could not be executed!!", e);
		} catch (NullPointerException e) {
			LOGGER.error("Consumer " + runnable + " is null !", e);
		}
	}
}
