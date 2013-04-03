package models;

import java.util.List;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;

public class User implements Subject {
	
	private String username;
	private String password;

	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Permission> getPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
