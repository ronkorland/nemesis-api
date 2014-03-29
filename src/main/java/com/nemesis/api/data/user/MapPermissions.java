package com.nemesis.api.data.user;

import java.util.HashMap;

public class MapPermissions {

	private String id;

	private HashMap<String, Boolean> mapPermissions;

	public MapPermissions() {

	}

	public MapPermissions(String id, HashMap<String, Boolean> mapPermissions) {
		super();
		this.id = id;
		this.mapPermissions = mapPermissions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, Boolean> getMapPermissions() {
		return mapPermissions;
	}

	public void setMapPermissions(HashMap<String, Boolean> mapPermissions) {
		this.mapPermissions = mapPermissions;
	}

}
