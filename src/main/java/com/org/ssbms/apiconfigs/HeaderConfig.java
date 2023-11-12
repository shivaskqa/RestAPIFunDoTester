package com.org.ssbms.apiconfigs;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfig {
	
	public Map<String,String> defaultHeader()
	{
		Map<String,String> defaultHeaders=new HashMap<>();
		defaultHeaders.put("Content-Type", "application/json");
		
		return defaultHeaders;
	}

	public Map<String,String> headersWithToken()
	{
		Map<String,String> defaultHeaders=new HashMap<>();
		defaultHeaders.put("Content-Type", "application/json");
		
		return defaultHeaders;
	}
	
}
