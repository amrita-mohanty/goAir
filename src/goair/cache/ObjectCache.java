package goair.cache;

import java.util.HashMap;

public class ObjectCache {

	public static HashMap<String,Object> cacheObj = new HashMap<String, Object>();
	
	public static Object get(String id) {
		
		return cacheObj.get(id);
	}
	
	public static void put(String id, Object obj) {
		
		cacheObj.put(id, obj);
	}
	
}
