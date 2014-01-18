package com.nemesis.api.filter;

public class SuiteFilter {

	private int pageSize;

	private int pageNumber;

	private String sortedBy;

	private String sortDir;

	private String suiteName;

	private int minusDays;
	
	private String status;

	public SuiteFilter(int pageSize, int pageNumber, String sortedBy,
			String sortDir, String suiteName, int minusDays, String status) {
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.sortedBy = sortedBy;
		this.sortDir = sortDir;
		this.suiteName = suiteName;
		this.status = status;
		setMinusDays(minusDays);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
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

}
