package com.example.demo.model;

import java.util.List;

public class Cluster {
	
	private String name;
	
	private List<String> pods;

	public List<String> getPods() {
		return pods;
	}

	public void setPods(List<String> pods) {
		this.pods = pods;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
