package com.suj1th.lettuceleaf;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {
	
	/**
	 * private constructor
	 */
	private ObjectMapperFactory(){}
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
