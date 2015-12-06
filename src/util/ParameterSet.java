package util;

import java.util.HashMap;

public class ParameterSet {
	
	protected HashMap<String, Object> parameters;
	
	public ParameterSet(){
		this.parameters = new HashMap<String, Object>();
	}
	
	public void add(String key, Object value) {
		this.parameters.put(key, value);
	}
	
	public boolean exists(String key) {
		return parameters.containsKey(key);
	}
	
	public String getString(String key) {
		return String.valueOf(parameters.get(key));
	}
	
	public int getInt(String key){
		return Integer.parseInt(getString(key));
	}
	
	public double getDouble(String key) {
		return Double.parseDouble(getString(key));
	}
}
