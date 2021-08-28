package com.learnStream;

import java.util.ArrayList;
import java.util.List;

public class City {
	private String name;
	private List<User> users = new ArrayList<User>();
	
	public City(String name, User ...users) {
		super();
		this.name = name;
		this.users = List.of(users);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}