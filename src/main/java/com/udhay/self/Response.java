/**
 * 
 */
package com.udhay.self;

import lombok.Data;

/**
 * @author udhay
 *
 */

public class Response {

	private String field;
	private Value value;
	
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
	
	
	
	
}
