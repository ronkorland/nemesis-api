package com.nemesis.api.data.user;

import java.util.ArrayList;
import java.util.List;

import com.nemesis.api.model.User;

public class UsersData {

	private List<UserData> users;

	private long total;

	private long totalPages;

	public UsersData(List<User> usersModel, long total, long totalPages) {
		super();
		if (usersModel != null && usersModel.size() > 0) {
			List<UserData> datas = new ArrayList<UserData>();
			for (User user : usersModel) {
				UserData userData = new UserData(user);
				userData.setPassword("");
				datas.add(userData);
			}
			this.users = datas;
		}
		this.total = total;
		this.totalPages = totalPages;
	}

	public List<UserData> getUsers() {
		return users;
	}

	public void setUsers(List<UserData> users) {
		this.users = users;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

}
