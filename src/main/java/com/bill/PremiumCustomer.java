package com.bill;

public class PremiumCustomer extends Customer {
	int billNo;
	float billAmount;
	
	public PremiumCustomer(int custId, String custName, long mobileNumber) {
		super(custId, custName, mobileNumber);
		// TODO Auto-generated constructor stub
	}

	
	public int getBillNo() {
		return billNo;
	}


	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}


	public float getBillAmount() {
		return billAmount;
	}


	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}


	@Override
	public double calculateBill(int minutes) {
		// TODO Auto-generated method stub
		double amt = 0;
		for(int i = 0; i < minutes; i++) {
			if(i <= 30) {
				amt = amt + 1;
			} else {
				amt = amt + 0.50;
			}
		}
		return amt;
	}

}
