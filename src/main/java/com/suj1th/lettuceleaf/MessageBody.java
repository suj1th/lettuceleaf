package com.suj1th.lettuceleaf;

/** This Class defines the RabbitMQ message structure. Add your custom Message fields here
 * 
 * @author suj1th
 *
 */
public class MessageBody {
	
	private String taskName;

	
	public MessageBody(){
		this.taskName = "";
	}
	
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public String toString() {
		return "MessageBody [taskName=" + taskName + "]";
	}
	
	

}
