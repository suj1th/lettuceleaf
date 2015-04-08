package com.suj1th.lettuceleaf;

public interface IThreadPoolManager {

	/**
	 * executes the Consumer in a managed thread at some time in the future.
	 * @param runnable 
	 * 		the Consumer to execute. 
	 */
	public abstract void execute(Runnable runnable);

}