package com.udhay.self;

import java.util.List;

public class Entry {
	
	private String time;
	private String id;
	private String uid;
	private List<Changes> changes;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<Changes> getChanges() {
		return changes;
	}
	public void setChanges(List<Changes> changes) {
		this.changes = changes;
	}
	
	

}
