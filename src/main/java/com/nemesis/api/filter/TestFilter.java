package com.nemesis.api.filter;

public class TestFilter {

	private String suiteId;

	private int pageSize;

	private int pageNumber;

	private String sortedBy;

	private String sortDir;

	private String method;

	private int minusDays;

	private String startDate;

	private String status;

	public TestFilter(int pageSize, int pageNumber, String sortedBy,
			String sortDir, String method, String status) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.sortedBy = sortedBy;
		this.sortDir = sortDir;
		this.method = method;
		this.status = status;
	}

	public TestFilter(int pageSize, int pageNumber, String sortedBy,
			String sortDir, String method, String status, String suiteId) {
		this(pageSize, pageNumber, sortedBy, sortDir, method, status);
		this.suiteId = suiteId;
		setMinusDays(0);
	}

	public TestFilter(int pageSize, int pageNumber, String sortedBy,
			String sortDir, String method, String status, int minusDays,
			String startDate) {
		this(pageSize, pageNumber, sortedBy, sortDir, method, status);
		setMinusDays(minusDays);
		setStartDate(startDate);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSortedBy() {
		return sortedBy;
	}

	public void setSortedBy(String sortedBy) {
		this.sortedBy = sortedBy;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public int getMinusDays() {
		return minusDays;
	}

	public void setMinusDays(int minusDays) {
		if (minusDays == 0) {
			this.minusDays = Integer.MAX_VALUE;
		} else {
			this.minusDays = minusDays;
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
