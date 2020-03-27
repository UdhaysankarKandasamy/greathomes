/**
 * 
 */
package com.udhay.self;

import java.util.List;

/**
 * @author udhay
 *
 */

public class Request {

	private String field;
	private Value value;
	private String object;
	private List<Entry> entry;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public List<Entry> getEntry() {
		return entry;
	}
	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}
	
	
	
	
	
}
