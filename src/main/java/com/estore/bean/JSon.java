package com.estore.bean;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSon {
	static ObjectMapper mapper = new ObjectMapper();
	static public String fromObject(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	static public String fromPairs(Object...pairs) {
		Map<Object, Object> map = new HashMap<>();
		for(int i=0;i<pairs.length;i+=2) {
			map.put(pairs[i], pairs[i+1]);
		}
		return fromObject(map);
	}
}
