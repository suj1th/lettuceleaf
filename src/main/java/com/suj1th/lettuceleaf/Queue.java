package com.suj1th.lettuceleaf;

import java.util.HashMap;

/**
 * @author suj1th
 *
 */
public class Queue {
	
	private String name;
	private String protocol;
	private Integer priority;
	private String host;
	private Integer port;
	private String vhost;
	private Integer	workers;
	private String username;
	private String password;
	

	public Queue(HashMap<String, Object> queueProperty) {
		name = (String) queueProperty.get("name");
		protocol = (String) queueProperty.get("protocol");
		priority = (Integer) queueProperty.get("priority");
		host = (String) queueProperty.get("host");
		port = (Integer) queueProperty.get("port");
		vhost = (String) queueProperty.get("vhost");
		workers = (Integer) queueProperty.get("workers");
		username = (String) queueProperty.get("username");
		password = (String) queueProperty.get("password");
	}


	public String getName() {
		return name;
	}


	public String getProtocol() {
		return protocol;
	}


	public Integer getPriority() {
		return priority;
	}


	public String getHost() {
		return host;
	}


	public Integer getPort() {
		return port;
	}


	public String getVhost() {
		return vhost;
	}


	public Integer getWorkers() {
		return workers;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	@Override
	public String toString() {
		return "Queue [name=" + name + ", protocol=" + protocol + ", priority="
				+ priority + ", host=" + host + ", port=" + port + ", vhost="
				+ vhost + ", workers=" + workers + ", username=" + username
				+ ", password=" + password + "]";
	}

	

}
