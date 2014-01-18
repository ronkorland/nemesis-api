package com.nemesis.api.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.nemesis.api.model.Suite;

public class SuiteByEndTime implements Comparator<Suite>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Suite arg0, Suite arg1) {
		return arg0.getEndTime().isBefore(arg1.getEndTime()) ? 1 : (arg0
				.getEndTime().isAfter(arg1.getEndTime()) ? -1 : 0);
	}

}
