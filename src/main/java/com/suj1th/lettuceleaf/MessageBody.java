package com.suj1th.lettuceleaf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/** This class defines the Job to be executed.
 * 
 * @author suj1th
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
	"taskName",
	"args",
	"vars"
	})
public class MessageBody implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1952616596290656060L;
	
	@JsonProperty("taskName")
	private String taskName;
	
	@JsonProperty("args")
	private Object[] args;
	
	@JsonProperty("vars")
	private Map<String,Object> vars;
	
	@JsonIgnore
	private Map<String,Object> unknowns;
	
	/**
	 * no-arg constructor
	 */
	public MessageBody(){
		unknowns = new HashMap<>();
	}
	
	/**
	 *clone constructor
	 *@param messageBody
	 *	the MessageBody to clone
	 */
	public MessageBody(final MessageBody messageBody){
		if(messageBody==null){
			throw new IllegalArgumentException("null MessageBody cannot be cloned!");
		}
		this.taskName = messageBody.getTaskName();	
		this.args = messageBody.getArgs()==null?null:messageBody.getArgs().clone();
		this.vars = messageBody.getVars()==null?null:new LinkedHashMap<String, Object>(messageBody.getVars());
	}

	public MessageBody(final String taskName, Object... args){
		
		if(taskName==null || "".equals(taskName)){
			throw new IllegalArgumentException("Invalid classname passed as taskName !");
		}
		this.taskName=taskName;
		this.args=args;
	}
	
	@JsonCreator
	public MessageBody(@JsonProperty("taskName") final String taskName, @JsonProperty("args") final Object[] args,
			@JsonProperty("vars") final Map<String, ? extends Object> vars){
		
		if(taskName==null || "".equals(taskName)){
			throw new IllegalArgumentException("Invalid classname passed as taskName !");
		}
		this.taskName=taskName;
		this.args=args;
		this.vars=new LinkedHashMap<String, Object>(vars);
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Map<String, Object> getVars() {
		return vars;
	}

	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}

	@JsonAnyGetter
	public Map<String, Object> getUnknowns() {
		return unknowns;
	}

	@JsonAnySetter
	public void setUnknowns(Map<String, Object> unknowns) {
		this.unknowns = unknowns;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageBody [taskName=").append(taskName)
				.append(", args=").append(Arrays.toString(args))
				.append(", vars=").append(vars).append(", unknowns=")
				.append(unknowns).append("]");
		return builder.toString();
	}


	
	
	

}
