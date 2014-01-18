package com.nemesis.api.data;

public class SummaryData {

	private int total;

	private int amountOfSuccess;

	private int amountOfFailed;

	public SummaryData() {
		super();
	}

	public SummaryData(int total, int amountOfSuccess, int amountOfFailed) {
		super();
		this.total = total;
		this.amountOfSuccess = amountOfSuccess;
		this.amountOfFailed = amountOfFailed;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAmountOfSuccess() {
		return amountOfSuccess;
	}

	public void setAmountOfSuccess(int amountOfSuccess) {
		this.amountOfSuccess = amountOfSuccess;
	}

	public int getAmountOfFailed() {
		return amountOfFailed;
	}

	public void setAmountOfFailed(int amountOfFailed) {
		this.amountOfFailed = amountOfFailed;
	}

}
