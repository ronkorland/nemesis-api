package com.nemesis.api.data.chart;

public class LastActivity {

	private int amountOfTests;
	private int amountOfFailed;
	private int amountOfSuccess;

	public LastActivity() {
		amountOfFailed = 0;
		amountOfSuccess = 0;
		amountOfTests = 0;
	}

	public int getAmountOfTests() {
		return amountOfTests;
	}

	public void setAmountOfTests(int amountOfTests) {
		this.amountOfTests = amountOfTests;
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

	public void addToAmountOfFailed(int addAmount) {
		this.amountOfFailed = this.amountOfFailed + addAmount;
	}

	public void addToAmountOfTests(int addAmount) {
		this.amountOfTests = this.amountOfTests + addAmount;
	}

	public void addToAmountOfSuccess(int addAmount) {
		this.amountOfSuccess = this.amountOfSuccess + addAmount;
	}
}
