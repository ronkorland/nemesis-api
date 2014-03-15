package com.nemesis.api.data.test;

import com.nemesis.api.model.Test;

public class TestHistoryData extends TestData {

	private boolean me;

	public TestHistoryData(Test test) {
		super(test);
	}

	public boolean isMe() {
		return me;
	}

	public void setMe(boolean me) {
		this.me = me;
	}

}