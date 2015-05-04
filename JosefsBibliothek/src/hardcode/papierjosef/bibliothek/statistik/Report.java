package hardcode.papierjosef.bibliothek.statistik;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Report {

	Map<String, Object> values;
	
	public Report() {
		values = new HashMap<>();
	}
	
	public void put(String key, Object value) {
		values.put(key, value);
	}
	
	public Object get(String key){
		return values.get(key);
	}

	public Set<String> keySet() {
		return values.keySet();
	}

	public int size() {
		return values.size();
	}
	
	

	
	
}